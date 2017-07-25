package com.kupa.hotel.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Hashtable;

/**
 * Created by HM on 2017/5/4 14:49
 */

public class QrCodeUtil {

    /**
     * 获取Mac地址
     *
     * @return
     */
    private static String getMacAddress() {
        String mac = "";
        String str = "";
        try {
            Process process = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            for (; null != str; ) {
                str = lineNumberReader.readLine();
                if (str != null) {
                    mac = str.trim();
                    Utils.log("Mac：" + mac);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mac;
    }


    /**
     * 创建带有“Mac-酒店ID-房间号-checkCode”的二维码
     *
     * @param hotelId 酒店ID
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
    public static Bitmap createQRCodeBitmap(int roomId, int hotelId, int checkCode, int width, int height) {
        Bitmap qrCode = null;
        String mac = getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            String id = mac + "_" + roomId + "_" + hotelId + "_" + checkCode;
            Utils.log("转换前的二维码内容：" + id);
            id = MD5Utils.encrypt(id);
            Utils.log("转换后的二维码内容：" + id);
            String content = "http://www.kupaworld.cn?param=123-" + id + "-" + hotelId + "-" + roomId + "-" + checkCode;
            Utils.log("二维码链接：" + content);
            MyQRCodeWriter qrCodeWriter = new MyQRCodeWriter();
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 0);
            try {
                BitMatrix qr = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                qr = deleteWhite(qr);//删除白边
                width = qr.getWidth();
                height = qr.getHeight();
                int[] pixels = new int[width * height];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (qr.get(j, i)) {
                            pixels[i * width + j] = 0x00000000;
                        } else {
                            pixels[i * width + j] = 0xffffffff;
                        }
                    }
                }
                qrCode = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return qrCode;
    }

    private static BitMatrix deleteWhite(BitMatrix qr) {
        //left, top, width, height
        int[] rec = qr.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (qr.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

}
