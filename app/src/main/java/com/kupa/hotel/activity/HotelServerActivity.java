package com.kupa.hotel.activity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.HotelServerAdapter;
import com.kupa.hotel.utils.Utils;
import com.kupa.hotel.utils.WifiUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by HM on 2017/3/28 15:27
 */

@ContentView(R.layout.activity_hotel)
public class HotelServerActivity extends BaseActivity implements View.OnFocusChangeListener,HotelServerAdapter.OnItemClickListener{

    @ViewInject(R.id.rv_hotel_server)
    private RecyclerView mRvServer;
    @ViewInject(R.id.tv_wifiSSID)
    private TextView mWifiSSID;

    private HotelServerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWifiSSID();
        initRecyclerView();
    }

    /**
     * 获取当前连接wifi的名称
     */
    private void getWifiSSID() {
        if (WifiUtils.isWifiConnected(HotelServerActivity.this)) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            mWifiSSID.setText("当前Wifi：" + ssid);
        } else {
            mWifiSSID.setText(null);
        }
    }

    private void initRecyclerView() {
        adapter = new HotelServerAdapter(this);
        adapter.setOnItemClickListener(this);
        mRvServer.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        mRvServer.setLayoutManager(glm);
        mRvServer.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) v).getChildCount() > 0) {
                ((RecyclerView) v).getChildAt(0).requestFocus();
            }
        }
    }

    @Override
    public void onItemClick(View view) {
        Utils.toast(this, "Offline version, unconnected server resources");
    }
}
