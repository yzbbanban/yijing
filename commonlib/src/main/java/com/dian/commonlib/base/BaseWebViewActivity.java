package com.dian.commonlib.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dian.commonlib.R;
import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;

import java.net.URISyntaxException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/29.
 */

public abstract class BaseWebViewActivity extends BaseLoadActivity {
    ProgressBar mProgressBar;
    WebView mWebView;
    private String url = "";
    private String titleStr;

    protected String token;
    private AudioManager.OnAudioFocusChangeListener listener;
    private AudioManager audioManager;

    public abstract void spread();

    protected abstract void h5Pay();

    public abstract ProgressBar getProgressBar();

    public abstract WebView getWebView();

    protected abstract void upDateTitle(String titleStr);

    protected void close() {
        finish();
    }

    protected void back() {
        if (mWebView.canGoBack()) {
            mWebView.canGoBack();
        } else {
            finish();
        }
    }


    @Override
    public void initViewAndData() {
        super.initViewAndData();
        token = MmkvUtil.decodeString(Constants.TOKEN, "");
        mProgressBar = getProgressBar();
        mWebView = getWebView();
        url = getIntent().getStringExtra(Constants.WEBURL);
        titleStr = getIntent().getStringExtra(Constants.WEBTITLE);

        if (!TextUtils.isEmpty(url)) {
            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "http://" + url;
            }
        }

        upDateTitle(titleStr);
        initWeb();
    }

    private void initWeb() {
        WebSettings webSettings = mWebView.getSettings();

        mWebView.addJavascriptInterface(new JStoAndroid(), "btw");

        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage
        webSettings.setPluginState(WebSettings.PluginState.ON);//播放视频添加
        webSettings.getMediaPlaybackRequiresUserGesture();//播放游戏声音
        webSettings.setMediaPlaybackRequiresUserGesture(true);//播放游戏声音
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        String userAgentString = webSettings.getUserAgentString();
        LogUtil.d("userAgentString==" + userAgentString);
        webSettings.setUserAgentString(userAgentString + " BTW_Wallet/ANDROID");
        String userAgentString2 = webSettings.getUserAgentString();
        LogUtil.d("userAgentString2==" + userAgentString2);


        // 设置Web视图
        LogUtil.d("url===" + url);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new mWebViewClient());

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    // 加载中
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);//设置进度值
                }
            }

//            @Override
//            public boolean onConsoleMessage(ConsoleMessage cm) {
//                L.e("noticeDetails===onConsoleMessage===" + cm.message() + " -- From line "
//                        + cm.lineNumber() + " of "
//                        + cm.sourceId());
//                return true;
//            }
//
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                showToast("onJsAlert\n" + message);
//                return true;
//            }
        });

    }


    // Web视图
    private class mWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                //处理intent协议
                if (url.startsWith("intent://")) {
                    Intent intent;
                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        intent.addCategory("android.intent.category.BROWSABLE");
                        intent.setComponent(null);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                            intent.setSelector(null);
                        }
                        List<ResolveInfo> resolves = getPackageManager().queryIntentActivities(intent, 0);
                        if (resolves.size() > 0) {
                            startActivity(intent);
                        }
                        return true;
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else if (!url.startsWith("http")) {
                    // 处理自定义scheme协议
                    LogUtil.d("处理自定义scheme-->" + url);
                    Uri parse = Uri.parse(url);
                    try {
                        // 以下固定写法
                        final Intent intent = new Intent(Intent.ACTION_VIEW, parse);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    } catch (Exception e) {
                        // 防止没有安装的情况
                        e.printStackTrace();
//                        showToast("你所打开的第三方App未安装!");
                        String scheme = parse.getScheme();
                        if ("yideng".equals(scheme)) {//如果是一灯社区，则打开官网链接
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pc.yideng.com/static/download/index.html"));
                            startActivity(intent);
                        }
                    }
                    return true;
                } else {
                    view.loadUrl(url);
                    return true;
                }
            }
            return false;
        }

        //访问ssl证书网页的问题
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
            // super.onReceivedSslError(view, handler, error);

            // 接受所有网站的证书，忽略SSL错误，执行访问网页
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            LogUtil.d("onReceivedError");
        }

        // 网页开始加载
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候
            super.onPageStarted(view, url, favicon);
        }


        // 网页加载结束
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LogUtil.d("onPageFinished" + view.getTitle());
            if (TextUtils.isEmpty(titleStr)) {
                if (!TextUtils.isEmpty(view.getTitle())) {
                    upDateTitle(view.getTitle());
                } else {
                    upDateTitle("");
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        }
        super.onKeyDown(keyCode, event);
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.clearHistory();
        mWebView.clearCache(true);
        mWebView.destroy();
    }

    @Override
    protected void onResume() {
        if (audioManager != null) {
            audioManager.abandonAudioFocus(listener);
            audioManager = null;
        }
        super.onResume();
        mWebView.onResume();
    }


    @Override
    protected void onPause() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        listener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
            }
        };
        int result = audioManager.requestAudioFocus(listener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
        }

        super.onPause();
        mWebView.onPause();

    }

    public class JStoAndroid {
        // 定义JS需要调用的方法
        @JavascriptInterface
        public String getToken() {
            token = MmkvUtil.decodeString(Constants.TOKEN, "");
            return token;
        }

        @JavascriptInterface
        public void shareImg() {
            spread();
        }

        @JavascriptInterface
        public void goPayment() {
            h5Pay();
        }
    }


}
