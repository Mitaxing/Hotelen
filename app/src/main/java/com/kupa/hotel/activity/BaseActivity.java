package com.kupa.hotel.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.kupa.hotel.R;
import com.kupa.hotel.application.SysApplication;
import com.kupa.hotel.contacts.Contacts;
import com.kupa.hotel.entity.Weather;
import com.kupa.hotel.mqtt.MqttUtil;
import com.kupa.hotel.utils.BaiDuMapUtils;
import com.kupa.hotel.utils.CheckCodeUtils;
import com.kupa.hotel.utils.CombinationKeyUtil;
import com.kupa.hotel.utils.DateUtils;
import com.kupa.hotel.utils.JsonParse;
import com.kupa.hotel.utils.MessageUtil;
import com.kupa.hotel.utils.Utils;
import com.kupa.hotel.utils.WeatherUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.kupa.hotel.contacts.Contacts.className;
import static com.kupa.hotel.contacts.Contacts.packageName;


/**
 * 基础activity，解决公共方法调用
 * Created by Mita on 2017/6/7.
 */
public class BaseActivity extends AppCompatActivity {

    private CombinationKeyUtil keyUtil = null;
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    private static SimpleDateFormat sdfTime;
    private long mLastKeyDownTime = 0;

    private TextView mTvDate, mTvTime, mTvWeather;

    private PushMessageReceiver pushMessageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initKey();
//        if (!SysApplication.REPLY_CHECK_CODE || !SysApplication.REQUEST_CHECK_CODE) {
//            requestCheckCode();
//        }
//        registerPushReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        boolean isRun = MqttUtil.serviceIsRunning(this);
//        Utils.log("mqtt是否运行：" + isRun);
//        if (!isRun) {
//            MqttUtil.startBlackIceService(this);
//        }
    }

//    @Override
//    protected void onDestroy() {
//        if (null != pushMessageReceiver)
//            unregisterReceiver(pushMessageReceiver);
//        super.onDestroy();
//    }

    private void requestCheckCode() {
        RequestParams params = new RequestParams(com.kupa.hotel.utils.Contacts.API_CHECK_CODE);
        params.addParameter("roomId", String.valueOf(com.kupa.hotel.utils.Contacts.ROOMID));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("校验值：" + result);
                SysApplication.REQUEST_CHECK_CODE = true;
                String checkCode = CheckCodeUtils.resolveCode(result);
                if (!TextUtils.isEmpty(checkCode)) {
                    CheckCodeUtils.saveCheckCode(BaseActivity.this, checkCode);
                }
                requestQrReply(com.kupa.hotel.utils.Contacts.ROOMID, com.kupa.hotel.utils.Contacts.SUCCESS);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                SysApplication.REQUEST_CHECK_CODE = false;
                requestQrReply(com.kupa.hotel.utils.Contacts.ROOMID, com.kupa.hotel.utils.Contacts.FAUIL);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void requestQrReply(int roomId, int status) {
        RequestParams params = new RequestParams(com.kupa.hotel.utils.Contacts.API_REPLY_QRCODE);
        params.addParameter("roomId", roomId);
        params.addParameter("status", status);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("回复结果：" + result);
                if (result.contains("ok"))
                    SysApplication.REPLY_CHECK_CODE = true;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 初始化日期和时间
     *
     * @param activity
     */
    public void initDate(Activity activity, int dateRes) {
        mTvTime = (TextView) activity.findViewById(R.id.time);
        mTvDate = (TextView) activity.findViewById(dateRes);

        sdfTime = new SimpleDateFormat("HH:mm", new Locale("zh", "CN"));
        mTvDate.setText(DateUtils.getDate());
        updateTime();
    }

    /**
     * 更新时间
     */
    private void updateTime() {
        mTvTime.setText(sdfTime.format(new Date()));
        handler.sendEmptyMessageDelayed(10, 1500);
    }

    private void initKey() {
        keyUtil = new CombinationKeyUtil(Contacts.keyStrings);
    }

    /**
     * 获取天气信息
     *
     * @param activity
     */
    public void getWeather(Activity activity) {
        mTvWeather = (TextView) activity.findViewById(R.id.weather);
        initWeather();
    }

    /**
     * 按键匹配，正确就跳转到系统设置界面
     *
     * @param keyCode 键值
     */
    private void matchCombinationKey(int keyCode) {
        if (keyUtil.isMatch(keyCode)) {//如果按键为：左右左右，则组合快捷键匹配为true
            try {
                startActivity(new Intent().setClassName(packageName, className));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10:
                    updateTime();
                    break;

                case 11:
                    initWeather();
                    break;
            }
        }
    };

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        matchCombinationKey(keyCode);
//        long current = System.currentTimeMillis();
//        boolean res;
//        if (current - mLastKeyDownTime < 300) {
//            res = true;
//        } else {
//            res = super.onKeyDown(keyCode, event);
//            mLastKeyDownTime = current;
//        }
//        return res;
//    }

    /**
     * 初始化天气信息
     */
    private void initWeather() {
        Weather weather = WeatherUtil.getWeather();
        if (null == weather) {//没有天气信息
            String city = getSaveCity();
            if (TextUtils.isEmpty(city)) {//城市为空，定位
                startLocation();
            } else {//城市不为空，查询天气
                requestWeather(city);
            }
        } else {//显示天气
            String info = WeatherUtil.montageWeather(weather);
            mTvWeather.setText(info);
        }
    }

    /**
     * 启动定位
     */
    private void startLocation() {
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.setLocOption(BaiDuMapUtils.getOption());
        mLocationClient.start();
        Utils.log("启动定位");
    }

    /**
     * 百度地图定位监听
     */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            boolean localSuccess = BaiDuMapUtils.isLocatSuccess(location);
            if (localSuccess) {
                Utils.log("定位成功");
                String city = location.getCity();
                if (null != city) {
                    mLocationClient.stop();
                    if (city.contains("市"))
                        city = city.substring(0, city.indexOf("市"));
                    Utils.log("定位的城市：" + city);
                    saveCity(city);
                    requestWeather(city);
                }
            } else {
                Utils.log("定位失败");
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }

    /**
     * 保存城市名称
     *
     * @param city 城市名称
     */
    private void saveCity(String city) {
        SharedPreferences sp = getSharedPreferences("kupatv", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("city", city).apply();
    }

    /**
     * 获取天气并保存到数据库
     *
     * @param city 城市名称
     */
    private void requestWeather(String city) {
        try {
            HttpGet httpRequest = new HttpGet("http://api.map.baidu.com/telematics/v3/weather?location=" + city
                    + "&output=json&ak=XshP6eeg4Rc6CRGqmDuGo23c&mcode=E5:5A:F6:32:80:C2:2F:87:07:04:19:9F:51:E8:D1:E5:6B:12:95:8D;com.yimi.merun");
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity());
                List<Weather> weathers = JsonParse.parseWeather(result);
                WeatherUtil.saveWeather(weathers);
                handler.sendEmptyMessage(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取本地城市名
     *
     * @return city
     */
    public String getSaveCity() {
        SharedPreferences sp = getSharedPreferences("kupatv", 0);
        return sp.getString("city", "");
    }

    private void registerPushReceiver() {
        pushMessageReceiver = new PushMessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kupa.hotel.WXPUSH");
        registerReceiver(pushMessageReceiver, filter);
    }

    private class PushMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            MessageUtil.dealMessage(BaseActivity.this, intent.getStringExtra("message"));
        }
    }


}