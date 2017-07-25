package com.kupa.hotel.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Mita on 2017/6/26.
 */

public class CheckCodeUtils {

    /**
     * 解析校验值
     *
     * @param result
     * @return
     */
    public static String resolveCode(String result) {
        String code = "";
        JSONTokener parse = new JSONTokener(result);
        try {
            JSONObject object = (JSONObject) parse.nextValue();
            if (object.optString("res", "error").equals("ok")) {
                code = object.optString("roomCheckCode");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 保存校验值
     *
     * @param context
     * @param checkCode
     */
    public static void saveCheckCode(Context context, String checkCode) {
        String date = DateUtils.getDate();
        SharedPreferences sp = context.getSharedPreferences("kupaHotel", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("checkCode", checkCode).putString("date", date).apply();
    }

    /**
     * 获取保存的校验值
     *
     * @param context
     * @return
     */
    public static String getCheckCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences("kupaHotel", 0);
        return sp.getString("checkCode", "");
    }

    /**
     * 获取校验值的保存日期
     *
     * @param context
     * @return
     */
    public static String getCheckCodeDate(Context context) {
        SharedPreferences sp = context.getSharedPreferences("kupaHotel", 0);
        return sp.getString("date", "2016年7月7日");
    }
}
