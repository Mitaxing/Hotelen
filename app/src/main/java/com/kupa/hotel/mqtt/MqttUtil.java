package com.kupa.hotel.mqtt;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import com.kupa.hotel.utils.Utils;

/**
 * Created by Mita on 2017/6/15.
 */

public class MqttUtil {

    private static final String SERVICE_CLASSNAME = "com.kupa.hotel.mqtt.MQTTService";

    /**
     * 启动后台服务
     * @param context 当前Context
     */
    public static void startBlackIceService(Context context) {
        final Intent intent = new Intent(context, MQTTService.class);
        context.startService(intent);
        Utils.log("启动mqtt服务");
    }

    /**
     * 停止后台服务
     * @param context 当前Context
     */
    public static void stopBlackIceService(Context context) {
        final Intent intent = new Intent(context, MQTTService.class);
        context.stopService(intent);
    }

    /**
     * 判断后台服务是否启动
     * @return 是否启动
     */
    public static boolean serviceIsRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (SERVICE_CLASSNAME.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
