package com.kupa.hotel.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.FoodAroundAdapter;


/**
 * Created by HM on 2017/4/14 15:26
 */

public class EntertainmentActivity extends BaseActivity implements View.OnFocusChangeListener, View.OnClickListener {

    private ImageView mIvFood;
    private TextView mTvName;
    private TextView mTvIntroduce, mTvAround;
    private RecyclerView mRvFood;
    private int currentPosition;

    private FoodAroundAdapter adapter;

    private int[] imgs = {R.mipmap.food_suanjiaogao, R.mipmap.food_xiangzhufan, R.mipmap.food_szrp, R.mipmap.food_qiguoji,
            R.mipmap.food_zimifan, R.mipmap.food_nuonichang, R.mipmap.food_guoqiaomixian, R.mipmap.food_wandoufen};
    private int[] foods = {R.mipmap.food_info_suanjiaogao, R.mipmap.food_info_xinagzhufan, R.mipmap.food_info_shuizrp, R.mipmap.food_info_qiguoji,
            R.mipmap.food_info_zimifan, R.mipmap.food_info_nuomichang, R.mipmap.food_info_guoqiaomixian, R.mipmap.food_info_wandoufen};
    private String[] names = {"Tamarind cake", "Dai ethnic bamboo rice", "Boiled meat", "The economic chicken", "Pineapple purple rice", "Glutinous rice bowel",
            "Yunnan Rice Noodles","Pea meal"};
    private int[] introduces = {R.string.food_1, R.string.food_2, R.string.food_3, R.string.food_4,
            R.string.food_5, R.string.food_6, R.string.food_7, R.string.food_8,
            R.string.food_9, R.string.food_10, R.string.food_11, R.string.food_12, R.string.food_13};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment_activity);
        getViews();
        initViews();
        registerReceiver();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int id = view.getId();
        switch (id) {
            case R.id.rv_food:
                if (hasFocus) {
                    if (((RecyclerView) view).getChildCount() > 0) {
                        ((RecyclerView) view).getChildAt(0).requestFocus();
                    }
                }
                break;

            case R.id.food_around:
                if (hasFocus) {
                    mTvAround.setTextColor(Color.WHITE);
                } else {
                    mTvAround.setTextColor(Color.parseColor("#0068b7"));
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.food_around:
                Intent intent = new Intent(EntertainmentActivity.this, NearbyStoresActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.foodactivity_zoomin, R.anim.foodactivity_zoomout);
                break;
        }
    }

    private void initViews() {
        adapter = new FoodAroundAdapter(this, imgs, names);
        mRvFood.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvFood.setLayoutManager(llm);
        mRvFood.setOnFocusChangeListener(this);
        mTvAround.setOnFocusChangeListener(this);
        mTvAround.setOnClickListener(this);

        mTvName.setText(names[0]);
        mTvIntroduce.setText(introduces[0]);
        mIvFood.setBackgroundResource(foods[0]);

        mRvFood.requestFocus();
    }

    @Override
    protected void onDestroy() {
        if (positionReceiver != null) {
            unregisterReceiver(positionReceiver);
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (mTvAround.hasFocus() && mRvFood.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mRvFoodRequestFocus();
                }
                break;
//            case KeyEvent.KEYCODE_DPAD_LEFT:
//                if (mRvFood.hasFocus() && mRvFood.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && currentPosition > 0) {
//                    mRvFood.setNextFocusLeftId(currentPosition - 1);
//                }
//                break;
//            case KeyEvent.KEYCODE_DPAD_RIGHT:
//                if (mRvFood.hasFocus() && mRvFood.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && currentPosition < foods.length) {
//                    mRvFood.setNextFocusRightId(currentPosition + 1);
//                }
//                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void mRvFoodRequestFocus() {
        RecyclerView.LayoutManager layoutManager = mRvFood.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager llm = (LinearLayoutManager) layoutManager;
            int firstPosition = llm.findFirstVisibleItemPosition();
//            currentPosition = currentPosition - firstPosition;
            currentPosition -= firstPosition;
            mRvFood.getChildAt(currentPosition).requestFocus();
        }
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kupa.food.position");
        registerReceiver(positionReceiver, filter);
    }

    private BroadcastReceiver positionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            currentPosition = intent.getIntExtra("pos", 0);

            Log.i("hm", "position=" + currentPosition);

            mTvName.setText(names[currentPosition]);
            mTvIntroduce.setText(introduces[currentPosition]);
            mIvFood.setBackgroundResource(foods[currentPosition]);
        }
    };

    private void getViews() {
        mIvFood = (ImageView) findViewById(R.id.food_img);
        mTvName = (TextView) findViewById(R.id.food_name);
        mTvIntroduce = (TextView) findViewById(R.id.food_introduce);
        mRvFood = (RecyclerView) findViewById(R.id.rv_food);
        mTvAround = (TextView) findViewById(R.id.food_around);
    }
}
