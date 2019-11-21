package com.yjb.ui.scan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MyMatisseUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.mvp.presenter.sys.ScanPresenter;
import com.yjb.scan.AmbientLightManager;
import com.yjb.scan.CaptureActivityHandler;
import com.yjb.scan.FinishListener;
import com.yjb.scan.InactivityTimer;
import com.yjb.scan.IntentSource;
import com.yjb.scan.Intents;
import com.yjb.scan.ViewfinderView;
import com.yjb.scan.camera.CameraManager;
import com.yjb.scan.decode.DecodeFormatManager;
import com.yjb.scan.decode.DecodeHintManager;
import com.zhihu.matisse.Matisse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/9/4.
 */

public class CaptureActivity extends BaseLoadActivity implements SurfaceHolder.Callback {

    @BindView(R.id.previewView)
    SurfaceView previewView;
    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;
    @BindView(R.id.tvShoudDianTong)
    TextView tvShoudDianTong;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ScanPresenter scanPresenter;
    private QRHandleUtil qrHandleUtil;

    private static final String[] ZXING_URLS = {"http://zxing.appspot.com/scan", "zxing://scan/"};

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private IntentSource source;
    private String sourceUrl;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private AmbientLightManager ambientLightManager;
    private boolean isOpenDeng = false;
    private final int OPEN_ALBUM = 0x0834;
    private String pagetype;
    private String coinId;


    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return R.layout.activity_capture;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, "", R.drawable.ic_action_back_light);
        tvRight.setText(R.string.album);
        toolbar.setBackgroundResource(R.color.transparent);

        pagetype = getIntent().getStringExtra(HuoHuoConstants.TYPE);
        coinId = getIntent().getStringExtra(HuoHuoConstants.COINID);//转币扫一扫进来

        scanPresenter = new ScanPresenter();
        qrHandleUtil = new QRHandleUtil(this, scanPresenter, pagetype, coinId);

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        ambientLightManager = new AmbientLightManager(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_ALBUM && resultCode == RESULT_OK) {
            List<String> list = Matisse.obtainPathResult(data);
            String albumImgPath = list.get(0);
            qrHandleUtil.shibieImgQr(albumImgPath);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraManager = new CameraManager(getApplication());
        viewfinderView.setCameraManager(cameraManager);
        handler = null;
        //自动横竖屏处理
        setRequestedOrientation(getCurrentOrientation());
        resetStatusView();
        ambientLightManager.start(cameraManager);
        inactivityTimer.onResume();
        Intent intent = getIntent();
        source = IntentSource.NONE;
        sourceUrl = null;
        decodeFormats = null;
        characterSet = null;
        if (intent != null) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            if (Intents.Scan.ACTION.equals(action)) {
                // Scan the formats the intent requested, and return the result to the calling activity.
                source = IntentSource.NATIVE_APP_INTENT;
                decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                decodeHints = DecodeHintManager.parseDecodeHints(intent);
                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int width = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int height = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (width > 0 && height > 0) {
                        cameraManager.setManualFramingRect(width, height);
                    }
                }
                if (intent.hasExtra(Intents.Scan.CAMERA_ID)) {
                    int cameraId = intent.getIntExtra(Intents.Scan.CAMERA_ID, -1);
                    if (cameraId >= 0) {
                        cameraManager.setManualCameraId(cameraId);
                    }
                }
                String customPromptMessage = intent.getStringExtra(Intents.Scan.PROMPT_MESSAGE);
                if (customPromptMessage != null) {
//                    statusView.setText(customPromptMessage);
                }
            }
//            else if (dataString != null &&
//                    dataString.contains("http://www.google") &&
//                    dataString.contains("/m/products/scan")) {
//                // Scan only products and send the result to mobile Product Search.
//                source = IntentSource.PRODUCT_SEARCH_LINK;
//                sourceUrl = dataString;
//                decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;
//            }
//            else if (isZXingURL(dataString)) {
//                // Scan formats requested in query string (all formats if none specified).
//                // If a return URL is specified, send the results there. Otherwise, handle it ourselves.
//                source = IntentSource.ZXING_LINK;
//                sourceUrl = dataString;
//                Uri inputUri = Uri.parse(dataString);
//                decodeFormats = DecodeFormatManager.parseDecodeFormats(inputUri);
//                // Allow a sub-set of the hints to be specified by the caller.
//                decodeHints = DecodeHintManager.parseDecodeHints(inputUri);
//            }
            characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);

        }

        SurfaceHolder surfaceHolder = previewView.getHolder();
        if (hasSurface) {
            // The activity was paused but not stopped, so the surface still exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(surfaceHolder);
        } else {
            // Install the callback and wait for surfaceCreated() to init the camera.
            surfaceHolder.addCallback(this);
        }
    }


    private static boolean isZXingURL(String dataString) {
        if (dataString == null) {
            return false;
        }
        for (String url : ZXING_URLS) {
            if (dataString.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        ambientLightManager.stop();
        cameraManager.closeDriver();
        //historyManager = null; // Keep for onActivityResult
        if (!hasSurface) {
            SurfaceHolder surfaceHolder = previewView.getHolder();
            surfaceHolder.removeCallback(this);
        }
//        myOrientationDetector.disable();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (source == IntentSource.NATIVE_APP_INTENT) {
                    setResult(RESULT_CANCELED);
                    finish();
                    return true;
                }
//                if ((source == IntentSource.NONE || source == IntentSource.ZXING_LINK)) {
//                    restartPreviewAfterDelay(0L);
//                    return true;
//                }
                break;
            case KeyEvent.KEYCODE_FOCUS:
            case KeyEvent.KEYCODE_CAMERA:
                // Handle these events so they don't launch the Camera app
                return true;
            // Use volume up/down to turn on light
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                cameraManager.setTorch(false);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                cameraManager.setTorch(true);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            LogUtil.d("*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // do nothing
    }

    /**
     * A valid barcode has been found, so give an indication of success and show the results.
     *
     * @param rawResult   The contents of the barcode.
     * @param scaleFactor amount by which thumbnail was scaled
     * @param barcode     A greyscale bitmap of the camera data which was decoded.
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        // 返回结果
        inactivityTimer.onActivity();
        qrHandleUtil.handQrResult(rawResult.getText());
    }


    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            LogUtil.d("initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats, decodeHints, characterSet, cameraManager);
            }
        } catch (IOException ioe) {
            LogUtil.d("zxingioe===" + ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service

            LogUtil.d("zxing Unexpected error initializing camera-==" + e);
            LogUtil.d("zxing Unexpected error initializing camera===" + e);
            if (e.toString().equals("java.lang.RuntimeException: Fail to connect to camera service")) {
                //权限未打开
                new AlertDialog.Builder(this)
                        .setMessage(R.string.camera_quanxian)
                        .setPositiveButton(R.string.to_set, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
            } else {
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
        resetStatusView();
    }

    private void resetStatusView() {
//        resultView.setVisibility(View.GONE);
//        statusView.setText(R.string.msg_default_status);
//        statusView.setVisibility(View.VISIBLE);
        viewfinderView.setVisibility(View.VISIBLE);
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    @OnClick({R.id.tvRight, R.id.tvShoudDianTong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRight:
                MyMatisseUtil.matisse(CaptureActivity.this, OPEN_ALBUM, 1, false);
                break;
            case R.id.tvShoudDianTong:
                isOpenDeng = !isOpenDeng;
                cameraManager.setTorch(isOpenDeng);
                if (isOpenDeng) {
                    tvShoudDianTong.setText(R.string.close_shoudiantong);
                } else {
                    tvShoudDianTong.setText(R.string.open_shoudiantong);
                }
                break;
        }
    }


    private int getCurrentOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_90:
                    return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                default:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
            }
        } else {
            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_270:
                    return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                default:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
            }
        }
    }
}
