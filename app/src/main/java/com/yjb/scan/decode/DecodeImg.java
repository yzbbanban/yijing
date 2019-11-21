package com.yjb.scan.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dian.commonlib.utils.LogUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by kennysun on 2019/9/4.
 */

public class DecodeImg {

    /**
     * 解析二维码（使用解析RGB编码数据的方式）     ----- 部分解析不出来
     *
     * @param path
     * @return
     */
    public static Result decodeBarcodeRGB(String path, boolean recycleBitmap) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;
        Bitmap barcode = BitmapFactory.decodeFile(path, opts);
        Result result = decodeBarcodeRGB(barcode, recycleBitmap);
        if (recycleBitmap) {
            barcode.recycle();
            barcode = null;
        }
        return result;
    }

    public static Result decodeBarcodeRGB(Bitmap barcode, boolean recycleBitmap) {
        LogUtil.d("barcode===" + barcode);
        if (barcode == null) {
            return null;
        }
        if (barcode.isRecycled()) {
            return null;
        }
        int width = barcode.getWidth();
        int height = barcode.getHeight();
        int[] data = new int[width * height];
        barcode.getPixels(data, 0, width, 0, 0, width, height);

        // 配置参数
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap1, hints);
        } catch (NotFoundException e) {
            LogUtil.d("NotFoundException");
        } catch (ChecksumException e) {
            LogUtil.d("ChecksumException");
        } catch (FormatException e) {
            LogUtil.d("FormatException");
        }
        if (recycleBitmap) {
            barcode.recycle();
            barcode = null;
        }
        return result;
    }


    /**
     * 增强版
     * 解析一维码/二维码图片
     *
     * @param bitmapPath
     * @return
     */
    public static Result parseCode(String bitmapPath) {
        Map<DecodeHintType, Object> hints = new HashMap<>();
        //添加可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<>();
        decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
        decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        decodeFormats.addAll(DecodeFormatManager.AZTEC_FORMATS);
        decodeFormats.addAll(DecodeFormatManager.PDF417_FORMATS);

        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        return parseCode(bitmapPath, hints);
    }

    /**
     * 解析一维码/二维码图片
     *
     * @param bitmapPath
     * @param hints      解析编码类型
     * @return
     */
    public static Result parseCode(String bitmapPath, Map<DecodeHintType, Object> hints) {
        try {
            MultiFormatReader reader = new MultiFormatReader();
            reader.setHints(hints);
            Result result = reader.decodeWithState(getBinaryBitmap(compressBitmap(bitmapPath)));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 压缩图片
     *
     * @param path
     * @return
     */
    private static Bitmap compressBitmap(String path) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;//获取原始图片大小
        BitmapFactory.decodeFile(path, newOpts);// 此时返回bm为空
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float width = 800f;
        float height = 480f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > width) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / width);
        } else if (w < h && h > height) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / height);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        newOpts.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, newOpts);
    }


    /**
     * 获取二进制图片
     *
     * @param bitmap
     * @return
     */
    private static BinaryBitmap getBinaryBitmap(@NonNull Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        //得到二进制图片
        return new BinaryBitmap(new HybridBinarizer(source));
    }
}
