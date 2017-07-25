package com.kupa.hotel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by admin on 2017/5/26.
 */

public class MainEnterRvAdapter extends RecyclerView.Adapter<MainEnterRvAdapter.MainEnterHolder> {

    private Context context;
    private String[] enterNames = {"电影点播", "在线商城", "旅游景点", "周边美食", "酒店服务"};
    private int[] enterNormalIcons = {R.mipmap.normal_icon_allmovie, R.mipmap.normal_icon_buy, R.mipmap.normal_icon_trip,
            R.mipmap.normal_icon_entertainment, R.mipmap.normal_icon_myhotel};
    private int[] enterFocusIcons = {R.mipmap.focus_icon_movie, R.mipmap.focus_icon_buy, R.mipmap.focus_icon_trip,
            R.mipmap.focus_icon_eat, R.mipmap.focus_icon_hotel};

    private static MainEnterClickListener enterClickListener;

    public MainEnterRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MainEnterHolder onCreateViewHolder(ViewGroup convertView, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_enter, convertView, false);
        MainEnterHolder holder = new MainEnterHolder(view);
        x.view().inject(holder, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainEnterHolder holder, final int position) {
        holder.mTvName.setText(enterNames[position]);
        holder.mTvName.setTextScaleX(1.2f);
        holder.mIvIcon.setBackgroundResource(enterNormalIcons[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                startAnim(holder.mIvIcon, b);
                startAnim(holder.mTvName, b);
                if (b) {
                    holder.mIvIcon.setBackgroundResource(enterFocusIcons[position]);
                    holder.mTvName.setTextColor(Color.WHITE);
                    holder.mIvFocused.setVisibility(View.VISIBLE);
                } else {
                    holder.mIvIcon.setBackgroundResource(enterNormalIcons[position]);
                    holder.mTvName.setTextColor(context.getResources().getColor(R.color.black_1a));
                    holder.mIvFocused.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return enterNames.length;
    }

    public static class MainEnterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MainEnterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @ViewInject(R.id.item_enter_name)
        TextView mTvName;
        @ViewInject(R.id.item_enter_img)
        ImageView mIvIcon;
        @ViewInject(R.id.item_enter_focused)
        ImageView mIvFocused;

        @Override
        public void onClick(View view) {
            if (enterClickListener != null) {
                enterClickListener.onClick(view);
            }
        }
    }

    public interface MainEnterClickListener {
        void onClick(View v);
    }

    public void setEnterClickListener(MainEnterClickListener enterClickListener) {
        this.enterClickListener = enterClickListener;
    }

    private void startAnim(View view, boolean isUp) {
        int res = R.anim.translate_down;
        if (isUp)
            res = R.anim.translate_up;
        Animation animation = AnimationUtils.loadAnimation(context, res);
        view.startAnimation(animation);//开始动画
    }
}
