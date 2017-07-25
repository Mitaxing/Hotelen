package com.kupa.hotel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.FoodInfoAdapter;
import com.kupa.hotel.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HM on 2017/4/15 15:27
 */

public class NearbyStoresActivity extends BaseActivity implements View.OnFocusChangeListener {

    private RecyclerView mRvAround;
    private FoodInfoAdapter adapter;

    private List<Restaurant> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_around_food);
        getViews();
        getDatas();
        initViews();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            if (((RecyclerView) view).getChildCount() > 0) {
                ((RecyclerView) view).getChildAt(0).requestFocus();
            }
        }
    }

    private void getDatas() {
        Restaurant restaurant = new Restaurant("Wild mushroom park (general store)", "3.1", "0871-67167476", "08:30 - 21:00", "185 guanxing road, guandu district, kunming (gate of yinhai garden)");
        list.add(restaurant);
        restaurant = new Restaurant("Dai xiangchun (branch street shop)", "4.0", "0871-63352552", "10:00 - 22:00", "1 branch of guandu district (next to star KTV)");
        list.add(restaurant);
        restaurant = new Restaurant("Puning's first powder", "4.4", "0871-65362096", "10:00 - 22:30", "No. 204, no. 204, no. 4, no. 2, no");
        list.add(restaurant);
    }

    private void initViews() {
        adapter = new FoodInfoAdapter(this, list);
        mRvAround.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvAround.setLayoutManager(llm);
        mRvAround.setOnFocusChangeListener(this);
    }

    private void getViews() {
        mRvAround = (RecyclerView) findViewById(R.id.rv_food_around);
    }
}
