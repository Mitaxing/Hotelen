package com.kupa.hotel.utils;

import android.net.Uri;

/**
 * Created by Mita on 2017/6/28.
 */

public class URLencode {

    /**
     * 转换URL中的中文
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        return Uri.encode(url, "-![.:/,%?&=]");
    }
}
