package com.kupa.hotel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.FifthSecondServerRVAdapter;
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
 * 第五版首页之二
 * Created by Mita on 2017/6/7.
 */
@ContentView(R.layout.activity_fifth_second)
public class FifthSecondActivity extends BaseActivity implements FifthSecondServerRVAdapter.SecondServerClickListener {

    @ViewInject(R.id.rv_second_home)
    private RecyclerView mRecycler;
    @ViewInject(R.id.iv_second_main_banner)
    private ImageView mIvBanner;
    @ViewInject(R.id.iv_second_main_play)
    private ImageView mIvPlay;
    @ViewInject(R.id.main_second_banner_layout)
    private FrameLayout mFlBanner;
    @ViewInject(R.id.main_second_play_layout)
    private FrameLayout mFlPlay;
    @ViewInject(R.id.second_main_recommend_title)
    private TextView mTvTitle;
    @ViewInject(R.id.second_main_recommend_name)
    private TextView mTvName;
    @ViewInject(R.id.second_main_recommend_content)
    private TextView mTvContent;
    @ViewInject(R.id.second_main_recommend_bg)
    private ImageView mIvBg;

    private Class[] clazz = {MovieHomeActivity.class, OnlineShopActivity.class, TripActivity.class, EntertainmentActivity.class, HotelServerActivity.class,};

    private int[] recommends = {R.mipmap.low_recommend_movie_fennudexiaoniao, R.mipmap.low_recommend_movie_shenghuaweiji, R.mipmap.low_recommend_movie_chaonengluzhandui,
            R.mipmap.low_recommend_view_lanyuegu, R.mipmap.low_recommend_view_yulongxueshan, R.mipmap.low_recommend_view_luguhu, R.mipmap.low_food_nuomifan};
    private List<Recommend> recommendList = new ArrayList<>();
    private int position;
    private boolean isFirst = true;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate(this, R.id.date);
        initRecyclerView();
//        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取天气信息
        getWeather(this);
        initRecommend();
        if (isFirst) {
            isFirst = false;
            mRecycler.requestFocus();
        }
//        MoviesUtils.requestMovieTypes();
    }

    @Override
    protected void onPause() {
        handler.removeMessages(0);
        super.onPause();
    }

    @Event(value = {R.id.rv_second_home, R.id.iv_second_main_play, R.id.iv_second_main_banner}, type = View.OnFocusChangeListener.class)
    private void focusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.main_rv_enter:
                if (hasFocus) {
                    if (((RecyclerView) v).getChildCount() > 0)
                        ((RecyclerView) v).getChildAt(0).requestFocus();
                }
                break;

            case R.id.iv_second_main_banner://轮播
                showHide(mIvBanner, mFlBanner, hasFocus);
                break;

            case R.id.iv_second_main_play://详情
                showHide(mIvPlay, mFlPlay, hasFocus);
                break;
        }
    }

    /**
     * 显示or隐藏提示文字
     *
     * @param layout
     * @param hasFocus
     */
    private void showHide(ImageView imageView, FrameLayout layout, boolean hasFocus) {
        if (hasFocus) {
            layout.setVisibility(View.VISIBLE);
            imageView.setAlpha(0f);
            handler.removeMessages(0);
        } else {
            layout.setVisibility(View.GONE);
            imageView.setAlpha(1.0f);
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecycler.setLayoutManager(llm);
        FifthSecondServerRVAdapter adapter = new FifthSecondServerRVAdapter(this);
        adapter.setSecondClickListener(this);
        mRecycler.setAdapter(adapter);
    }

    @Event(value = {R.id.iv_second_main_play, R.id.iv_second_main_banner})
    private void clickMethod(View v) {
        switch (v.getId()) {
            case R.id.iv_second_main_banner:
                initRecommend();
                handler.removeMessages(0);
                break;

            case R.id.iv_second_main_play:
                String name = recommendList.get(position).getName();
                if (name.equals("Angry Birds")) {
                    Utils.openVideo(this, "/storage/sdcard/Movies/愤怒的小鸟正片.mp4");
                    showBanner();

//                    intent = new Intent(this, PlayMovieActivity.class);
//                    intent.putExtra("movieUrl", "/storage/sdcard/Movies/愤怒的小鸟正片.mp4");
//                    intent.putExtra("movieName", "愤怒的小鸟");
//                    startActivity(intent);
                } else if (name.equals("Resident Evil")) {
                    Utils.openVideo(this, "/storage/sdcard/Movies/生化危机正片.mp4");
                    showBanner();

//                    intent = new Intent(this, PlayMovieActivity.class);
//                    intent.putExtra("movieUrl", "/storage/sdcard/Movies/生化危机正片.mp4");
//                    intent.putExtra("movieName", "生化危机");
//                    startActivity(intent);
                } else if (name.equals("Big Hero 6")) {
                    Utils.openVideo(this, "/storage/sdcard/Movies/超能陆战队正片.mp4");
                    showBanner();

//                    intent = new Intent(this, PlayMovieActivity.class);
//                    intent.putExtra("movieUrl", "/storage/sdcard/Movies/超能陆战队正片.mp4");
//                    intent.putExtra("movieName", "超能陆战队");
//                    startActivity(intent);
                }

                int type = recommendList.get(position).getType();
                if (type == Contacts.RECOMMEND_CATE) {
                    intent = new Intent(this, EntertainmentActivity.class);
                    startActivity(intent);
                } else if (type == Contacts.RECOMMEND_TOUR) {
                    intent = new Intent(this, TripActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private WindowManager wManager;
    private View mBanner;

    /**
     * 展示广告页
     */
    private void showBanner() {
        wManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        // 悬浮所有页面之上
        lp.type = WindowManager.LayoutParams.TYPE_PHONE;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 失去焦点
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        lp.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        mBanner = LayoutInflater.from(this).inflate(R.layout.layout_banner, null, false);
        wManager.addView(mBanner, lp);

        handler.sendEmptyMessageDelayed(1,4000);
    }

    @Override
    public void onClick(View v) {
        int childPosition = mRecycler.getChildAdapterPosition(v);
        Intent intent = new Intent(this, clazz[childPosition]);
        startActivity(intent);
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
        recommendList = RecommendUtil.getRecommend();
        int size = (null == recommendList) ? 0 : recommendList.size();
        if (size > 0) {
            if (position == size - 1) {
                position = 0;
            } else {
                position += 1;
            }
            Recommend recommend = recommendList.get(position);
            mTvTitle.setText(recommend.getTitle());
            mTvName.setText(recommend.getName());
            mTvContent.setText(recommend.getContent());
            AnimateUtils.startAlphaAnimation(mIvBg, Contacts.API_RECOMMEND_V2, recommend.getRes());
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

                case 1:
                    if (mBanner != null && wManager != null)
                        wManager.removeView(mBanner);
                    break;
            }
        }
    };
}
