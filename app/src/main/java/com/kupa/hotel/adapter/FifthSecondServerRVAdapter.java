package com.kupa.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kupa.hotel.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by admin on 2017/6/7.
 */

public class FifthSecondServerRVAdapter extends RecyclerView.Adapter<FifthSecondServerRVAdapter.FifthSecondViewHolder> {

    private Context context;
    private static SecondServerClickListener secondClickListener;

    private String[] names = {"Movies", "Online Store", "Travel", "Delicious", "Service"};
    private int[] bgs = {R.drawable.item_server_movie, R.drawable.item_server_shopping, R.drawable.item_server_view,
            R.drawable.item_server_food, R.drawable.item_second_server};
    private int[] enterFocusIcons = {R.mipmap.focus_icon_movie, R.mipmap.focus_icon_buy, R.mipmap.focus_icon_trip,
            R.mipmap.focus_icon_eat, R.mipmap.focus_icon_hotel};

    public FifthSecondServerRVAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FifthSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fifth_second, parent, false);
        FifthSecondViewHolder holder = new FifthSecondViewHolder(view);
        x.view().inject(holder, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FifthSecondViewHolder holder, int position) {
        holder.mTvName.setText(names[position]);
        holder.mLlBg.setBackgroundResource(bgs[position]);
        holder.mIvIcon.setBackgroundResource(enterFocusIcons[position]);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.mBorder.setVisibility(View.VISIBLE);
                } else {
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.mBorder.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class FifthSecondViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public FifthSecondViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @ViewInject(R.id.item_second_name)
        TextView mTvName;
        @ViewInject(R.id.item_second_icon)
        ImageView mIvIcon;
        @ViewInject(R.id.item_second_focus)
        ImageView mIvFocus;
        @ViewInject(R.id.item_second_layout)
        LinearLayout mLlBg;
        @ViewInject(R.id.item_second_focus_border)
        ImageView mBorder;

        @Override
        public void onClick(View view) {
            if (secondClickListener != null) {
                secondClickListener.onClick(view);
            }
        }
    }

    public void setSecondClickListener(SecondServerClickListener secondClickListener) {
        this.secondClickListener = secondClickListener;
    }

    public interface SecondServerClickListener {
        void onClick(View v);
    }
}
