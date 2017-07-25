package com.kupa.hotel.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.kupa.hotel.utils.MD5Utils;
import com.kupa.hotel.utils.Utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Mita on 2017/6/15.
 */

public class MQTTService extends Service {

    public static final String BROKER_URL = "tcp://192.168.0.111:1883";

    /* In a real application, you should get an Unique Client ID of the device and use this, see
    http://android-developers.blogspot.de/2011/03/identifying-app-installations.html */
//    public static final String clientId = "android-client-1234567";
    public static String clientId = "08:00:27:a3:e8:ed_305_1_123456789";

    public static final String TOPIC = "kupaMoviePush_2/PTP";

    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";

    private MqttClient mqttClient;

//    private final static String CONNECTION_STRING = "tcp://192.168.0.103:1883";
//    private final static boolean CLEAN_START = true;
//    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
//    private final static String CLIENT_ID = "main-machine-11";
//    public  static Topic[] topics = {
//            new Topic("kupaMoviePush_2/PTP", QoS.EXACTLY_ONCE)};
//    public final  static long RECONNECTION_ATTEMPT_MAX=6;
//    public final  static long RECONNECTION_DELAY=2000;
//
//    public final static int SEND_BUFFER_SIZE=2*1024*1024;//发送最大缓冲为2M

    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //采用多线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("测试进入后台开始服务", "onStart: " + System.currentTimeMillis());
                try {
                    MqttConnectOptions options = new MqttConnectOptions();
                    //options.setCleanSession(true);
                    options.setUserName(USERNAME);
                    options.setPassword(PASSWORD.toCharArray());
                    options.setConnectionTimeout(10000);
                    options.setKeepAliveInterval(5 * 60);

                    Utils.log("MD5前-clientId：" + clientId);
                    clientId = MD5Utils.encrypt(clientId);
                    Utils.log("MD5后-clientId：" + clientId);
                    mqttClient = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());
                    mqttClient.setCallback(new PushCallback(MQTTService.this));
                    mqttClient.connect(options);
//            mqttClient.connect();
                    //Subscribe to all subtopics of homeautomation
                    mqttClient.subscribe(TOPIC);
                } catch (MqttException e) {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "Something went wrong!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }).start();


        //创建MQTT对象
//        MQTT mqtt = new MQTT();
//        try {
//            //设置mqtt broker的ip和端口
//            mqtt.setHost(CONNECTION_STRING);
//            //连接前清空会话信息
//            mqtt.setCleanSession(CLEAN_START);
//            //设置重新连接的次数
//            mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
//            //设置重连的间隔时间
//            mqtt.setReconnectDelay(RECONNECTION_DELAY);
//            //设置心跳时间
//            mqtt.setKeepAlive(KEEP_ALIVE);
//            //设置缓冲的大小
//            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);
//
//            //获取mqtt的连接对象BlockingConnection
//            final FutureConnection connection= mqtt.futureConnection();
//            connection.connect();
//            connection.subscribe(topics);
//            while(true){
//                Future<Message> futrueMessage=connection.receive();
//                Message message =futrueMessage.await();
//
//                System.out.println("MQTTFutureClient.Receive Message "+ "Topic Title :"+message.getTopic()
//                        +" context :"+String.valueOf(message.getPayload()));
//            }
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally{
//
//        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("测试进入后台停止服务", "onDestroy: " + System.currentTimeMillis());

        try {
            mqttClient.disconnect(0);
        } catch (MqttException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong!" + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
