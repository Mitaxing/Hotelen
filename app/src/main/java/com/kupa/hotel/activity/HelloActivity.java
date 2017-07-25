package com.kupa.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.entity.Recommend;
import com.kupa.hotel.utils.Contacts;
import com.kupa.hotel.utils.DateUtils;
import com.kupa.hotel.utils.JsonParse;
import com.kupa.hotel.utils.RecommendUtil;
import com.kupa.hotel.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by admin on 2017/6/6.
 */
public class HelloActivity extends BaseActivity {

    private TextView mTvWeek;
    private TextView mTvLocation;
    private ImageView mIvHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        getViews();
        initDate(this, R.id.wel_date);
        mTvWeek.setText(DateUtils.getWeek());
//        requestRecommend();
    }

    public void enterClickMethod(View view) {
        startActivity(new Intent(this, FifthSecondActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWeather(this);
        mTvLocation.setText(getSaveCity());
        x.image().bind(mIvHotel, "assets://hotel_example.jpg");
    }

    /**
     * 查询首页推荐信息
     */
    private void requestRecommend() {
        RequestParams params = new RequestParams(Contacts.API_RECOMMEND);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("推荐信息：" + result);
                List<Recommend> recommendList = JsonParse.parseRecommend(result);
                RecommendUtil.saveRecommend(recommendList);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Utils.toast(HelloActivity.this, "网络不可用");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getViews() {
        mTvWeek = (TextView) findViewById(R.id.wel_week);
        mTvLocation = (TextView) findViewById(R.id.wel_location);
        mIvHotel = (ImageView) findViewById(R.id.wel_img);
    }
}
