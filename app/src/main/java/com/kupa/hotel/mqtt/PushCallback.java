package com.kupa.hotel.mqtt;

import android.content.ContextWrapper;
import android.content.Intent;

import com.kupa.hotel.utils.Utils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by Mita on 2017/6/15.
 */

public class PushCallback implements MqttCallback {

    private ContextWrapper context;

    public PushCallback(ContextWrapper context) {
        this.context = context;
    }

    /**
     * 连接丢失（失败）
     *
     * @param cause
     */
    @Override
    public void connectionLost(Throwable cause) {
        //We should reconnect here
        Utils.log("mqtt中断");
        MqttUtil.stopBlackIceService(context);
        MqttUtil.startBlackIceService(context);
    }

    /**
     * 信息到达
     *
     * @param var1
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String var1, MqttMessage message) throws Exception {
        Utils.log("信息到达！message: " + new String(message.getPayload()));

        Intent intent = new Intent();
        intent.setAction("com.kupa.hotel.WXPUSH");
        intent.putExtra("message", new String(message.getPayload()));
        context.sendBroadcast(intent);
    }

    /**
     * 信息转送
     *
     * @param var1
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken var1) {
        //We do not need this because we do not publish
    }
}
