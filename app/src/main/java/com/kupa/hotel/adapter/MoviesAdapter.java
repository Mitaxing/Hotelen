package com.kupa.hotel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.entity.MovieInfo;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * Created by HM on 2017/3/18 15:14
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<MovieInfo> mMovies;

    private int currentPosition;

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private static OnRecyclerViewItemSelectListener mSelectListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int movieId);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemSelectListener {
        void onMovieItemSelect(View view, int position);
    }

    public void setOnItemSelectListener(OnRecyclerViewItemSelectListener listener) {
        this.mSelectListener = listener;
    }

    public MoviesAdapter(Context context, List<MovieInfo> movies) {
        this.inflater = LayoutInflater.from(context);
        this.mMovies = movies;
    }

    public void setDatas(List<MovieInfo> movies) {
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.moviehome_gridview_videoitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        x.view().inject(holder, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MovieInfo movie = mMovies.get(position);
        holder.movieThumb.setImageResource(movie.getPhotoName());
        holder.movieName.setText(movie.getName());
        holder.itemView.setId(position);

        holder.itemView.setTag(position);
        holder.itemView.setFocusable(true);

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.mIvFocus.setVisibility(View.VISIBLE);
                    holder.movieName.setTextColor(Color.parseColor("#046ab8"));
                    currentPosition = position;
                    mSelectListener.onMovieItemSelect(holder.itemView, position);

                } else {
                    holder.mIvFocus.setVisibility(View.GONE);
                    holder.movieName.setTextColor(Color.parseColor("#666666"));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @ViewInject(R.id.imageView_videoIcon)
        ImageView movieThumb;
        ImageView mIvFocus;
        @ViewInject(R.id.textView_videoName)
        TextView movieName;
        TextView movieGrade, movieDateAndArea, movieType, movieDuration, movieDirector, movieProtagonist, movieAbstract, moviePreviewPath, moviePlayPath;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvFocus = (ImageView) itemView.findViewById(R.id.movie_item_onfocus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(view, (int) view.getTag());
            }
        }
    }

}
