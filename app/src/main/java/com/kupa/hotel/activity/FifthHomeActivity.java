package com.kupa.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.MainEnterRvAdapter;
import com.kupa.hotel.entity.Recommend;
import com.kupa.hotel.utils.AnimateUtils;
import com.kupa.hotel.utils.Contacts;
import com.kupa.hotel.utils.JsonParse;
import com.kupa.hotel.utils.RecommendUtil;
import com.kupa.hotel.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 第五版首页之一
 * Created by Mita on 2017/5/25.
 */
@ContentView(R.layout.activity_fifth_home)
public class FifthHomeActivity extends BaseActivity implements MainEnterRvAdapter.MainEnterClickListener {

    @ViewInject(R.id.main_rv_enter)
    private RecyclerView mRvEnter;
    @ViewInject(R.id.iv_main_banner)
    private ImageView mIvBanner;
    @ViewInject(R.id.iv_main_play)
    private ImageView mIvPlay;
    @ViewInject(R.id.main_banner_layout)
    private FrameLayout mFlBanner;
    @ViewInject(R.id.main_play_layout)
    private FrameLayout mFlPlay;
    @ViewInject(R.id.main_recommend_bg)
    private ImageView mIvBg;
    @ViewInject(R.id.main_recommend_type)
    private TextView mTvType;
    @ViewInject(R.id.main_recommend_title)
    private TextView mTvTitle;
    @ViewInject(R.id.main_recommend_content)
    private TextView mTvContent;

    private Class[] clazz = {MovieHomeActivity.class, OnlineShopActivity.class, TripActivity.class, EntertainmentActivity.class, HotelServerActivity.class,};

    private List<Recommend> recommendList = new ArrayList<>();
    private int position;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate(this,R.id.date);
        initRecyclerView();
        getData();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvEnter.setLayoutManager(llm);
        MainEnterRvAdapter adapter = new MainEnterRvAdapter(this);
        adapter.setEnterClickListener(this);
        mRvEnter.setAdapter(adapter);

    }

    @Event(value = {R.id.main_rv_enter, R.id.iv_main_play, R.id.iv_main_banner}, type = View.OnFocusChangeListener.class)
    private void OnFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.main_rv_enter:
                if (hasFocus) {
                    if (((RecyclerView) v).getChildCount() > 0)
                        ((RecyclerView) v).getChildAt(0).requestFocus();
                }
                break;

            case R.id.iv_main_banner:
                showHide(mFlBanner, hasFocus);
                break;

            case R.id.iv_main_play:
                showHide(mFlPlay, hasFocus);
                break;
        }
    }

    @Event(value = {R.id.iv_main_play, R.id.iv_main_banner})
    private void clickMethod(View v) {
        switch (v.getId()) {
            case R.id.iv_main_banner:
                initRecommend();
                handler.removeMessages(0);
                break;

            case R.id.iv_main_play:
                Utils.toast(FifthHomeActivity.this, "查看详情");
                break;
        }
    }

    /**
     * 显示or隐藏提示文字
     *
     * @param layout
     * @param hasFocus
     */
    private void showHide(FrameLayout layout, boolean hasFocus) {
        if (hasFocus) {
            layout.setVisibility(View.VISIBLE);
            handler.removeMessages(0);
        } else {
            layout.setVisibility(View.GONE);
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    }

    /**
     * 获取推荐信息
     */
    private void getData() {
        recommendList.clear();
        recommendList = RecommendUtil.queryRecommend();
    }

    /**
     * 初始化推荐信息
     */
    private void initRecommend() {
        int size = recommendList.size();
        if (size > 0) {
            if (position == size - 1)
                position = 0;
            else
                position += 1;
            Recommend recommend = recommendList.get(position);
            mTvType.setText(recommend.getTitle());
            mTvTitle.setText(recommend.getName());
            mTvContent.setText(recommend.getContent());
            AnimateUtils.startAlphaAnimation(mIvBg, Contacts.API_RECOMMEND_V3, recommend.getRes());
            handler.sendEmptyMessageDelayed(0, 6000);
        } else {
            requestRecommend();
        }
    }

    /**
     * 查询首页推荐信息
     */
    private void requestRecommend() {
        RequestParams params = new RequestParams(Contacts.API_RECOMMEND);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initRecommend();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        getWeather(this);
        initRecommend();
        if (isFirst) {
            isFirst = false;
            mRvEnter.requestFocus();
        }
    }

    @Override
    protected void onPause() {
        handler.removeMessages(0);
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        int childPosition = mRvEnter.getChildAdapterPosition(v);
        Intent intent = new Intent(this, clazz[childPosition]);
        startActivity(intent);
    }
}
