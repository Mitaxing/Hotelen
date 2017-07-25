package com.kupa.hotel.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.RvRecommendAdapter;
import com.kupa.hotel.entity.MovieInfo;
import com.kupa.hotel.utils.DateUtils;
import com.kupa.hotel.utils.MoviesUtils;
import com.kupa.hotel.utils.URLencode;
import com.kupa.hotel.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.moviehome_videoitem_detail)
public class MovieDetailActivity extends BaseActivity implements View.OnFocusChangeListener, View.OnClickListener, RvRecommendAdapter.OnRecyclerViewItemClickListener {

    @ViewInject(R.id.icon_play)
    private ImageView mIvPlay;
    @ViewInject(R.id.tv_play)
    private TextView mBtnMoviePlay;
    private TextView mBtnMovieKeep;
    @ViewInject(R.id.movie_name)
    private TextView mMovieName;
    @ViewInject(R.id.movie_grade)
    private TextView mMovieGrade;
    @ViewInject(R.id.movie_dateandarea)
    private TextView mMovieDateAndArea;
    @ViewInject(R.id.movie_type)
    private TextView mMovieType;
    @ViewInject(R.id.movie_duration)
    private TextView mMovieDuration;
    @ViewInject(R.id.movie_director)
    private TextView mMovieDirector;
    @ViewInject(R.id.movie_protagonist)
    private TextView mMovieProtagonist;
    @ViewInject(R.id.movie_abstract)
    private TextView mMovieAbstract;
    private ImageView mIvKeepFocus;
    @ViewInject(R.id.iv_play_focus)
    private ImageView mIvPlayFocus;
    private LinearLayout mLPlay;
    @ViewInject(R.id.rv_recommend)
    private RecyclerView mRvCommend;
    private RvRecommendAdapter mRvRecommendAdapter;
    private MovieInfo movieInfo;
    private Boolean isCollect = false;
    private List<MovieInfo> recommendList = new ArrayList<>();
    private String previewUri;
    @ViewInject(R.id.preview_video)
    private VideoView mPreview;

    private Intent intent;
    private int typeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initBtnPlay();
        init();
        initRecommend();
        initPreviewVideo();
    }

    private void initBtnPlay() {
        mIvPlayFocus.setVisibility(View.GONE);
        mBtnMoviePlay.setTextColor(Color.parseColor("#569bd5"));
        Drawable drawable = getResources().getDrawable(R.mipmap.play_no);
        //setBounds(int left, int top, int right, int bottom)
        drawable.setBounds(5, 0, drawable.getMinimumWidth() + 20, drawable.getMinimumHeight());
        mBtnMoviePlay.setCompoundDrawables(drawable, null, null, null);
    }

    private void init() {
        intent = getIntent();
//        int movieId = intent.getIntExtra("movieId", 0);
//        typeId = intent.getIntExtra("typeId", 0);
        movieInfo = (MovieInfo) intent.getSerializableExtra("movie");
        Utils.log("查询的电影：" + movieInfo);
        if (null != movieInfo) {
            initMovieInfo();
            previewUri = movieInfo.getPreviewUri();
//            previewUri = getLocalPath(movieInfo.getPreviewUri());

//            previewUri = "/storage/sdcard/Movies/变形金刚3.预告片.mp4";
            Utils.log("previewUri：" + previewUri);
//            if (TextUtils.isEmpty(previewUri)) {
//                previewUri = "android.resource://" + getPackageName() + "/" + R.raw.zn;
//            }
//            previewUri = Contacts.API_RECOMMEND_RES + previewUri;
//            Utils.log("视频路径：" + previewUri);
        }
//        mBtnMovieKeep.setOnFocusChangeListener(this);
        mBtnMoviePlay.setOnFocusChangeListener(this);
//        mBtnMovieKeep.setOnClickListener(this);
        mBtnMoviePlay.setOnClickListener(this);
    }

    private String getLocalPath(String name) {
        String path = Environment.getExternalStorageDirectory().getPath() + "/preview/变形金刚3.预告片.mp4";
        File file = new File(path);

        Utils.log("外部存储：" + file.exists());
        return path;
    }

    private void initMovieInfo() {
        mMovieName.setText(isEmpty(movieInfo.getName()));
        mMovieGrade.setText(isEmpty(String.valueOf(movieInfo.getGrade())) + "");
        String area = movieInfo.getAreaName();
        String date = DateUtils.formatYear(movieInfo.getShowTime());
        mMovieDateAndArea.setText(isEmpty(date) + " | " + isEmpty(area));
        mMovieType.setText(isEmpty(movieInfo.getKeyWord()));
        mMovieDuration.setText("Duration：" + isEmpty(String.valueOf(movieInfo.getDuration())) + "min");
        mMovieDirector.setText(isEmpty(movieInfo.getDirector()));
        mMovieProtagonist.setText(isEmpty(movieInfo.getPerformer()));
        mMovieAbstract.setText(isEmpty(movieInfo.getProfile()));
    }

    /**
     * 判断信息是否为空
     *
     * @param param
     * @return
     */
    private String isEmpty(String param) {
        if (param != null && !param.equals("") && !param.equals("null")) {
            return param;
        }
        return "unknown";
    }

    private void initPreviewVideo() {
        if (!TextUtils.isEmpty(previewUri)) {
            Utils.log("预告片：" + Uri.parse(previewUri));
            previewUri = URLencode.encodeUrl(previewUri);
            Utils.log("预告片：" + previewUri);

            mPreview.setVideoURI(Uri.parse(previewUri));
            mPreview.start();
            mPreview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    mp.setLooping(true);
                }
            });
            mPreview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Utils.toast(MovieDetailActivity.this, "Preview TAB failed to load");
                    return true;
                }
            });
        }
    }

    private void initRecommend() {
        recommendList = MoviesUtils.getMovieInfo();
//        recommendList = MoviesUtils.queryMovieByTypeId(typeId);
        mRvRecommendAdapter = new RvRecommendAdapter(this, recommendList);
        mRvCommend.setAdapter(mRvRecommendAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvCommend.setLayoutManager(llm);
        mRvCommend.setOnFocusChangeListener(this);
        mRvRecommendAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
//            case R.id.tv_keep:
//                if (hasFocus) {
//                    mIvKeepFocus.setVisibility(View.VISIBLE);
//                    mBtnMovieKeep.setTextColor(Color.WHITE);
//                    Drawable drawable = getResources().getDrawable(R.mipmap.keep_onfocus);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    mBtnMovieKeep.setCompoundDrawables(drawable, null, null, null);
//                } else {
//                    mIvKeepFocus.setVisibility(View.GONE);
//                    mBtnMovieKeep.setTextColor(Color.parseColor("#569bd5"));
//                    Drawable drawable = getResources().getDrawable(R.mipmap.keep_no);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    mBtnMovieKeep.setCompoundDrawables(drawable, null, null, null);
//                }
//                break;

            case R.id.tv_play:
                if (hasFocus) {
                    mIvPlayFocus.setVisibility(View.VISIBLE);
                    mBtnMoviePlay.setTextColor(Color.WHITE);
                    mIvPlay.setImageResource(R.mipmap.play_start);
                } else {
                    mIvPlayFocus.setVisibility(View.GONE);
                    mBtnMoviePlay.setTextColor(Color.parseColor("#569bd5"));
                    mIvPlay.setImageResource(R.mipmap.play_no);
                }
                break;

            case R.id.rv_recommend:
                if (hasFocus) {
                    if (((RecyclerView) v).getChildCount() > 0) {
                        ((RecyclerView) v).getChildAt(0).requestFocus();
                    }
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
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mBanner != null && wManager != null)
                        wManager.removeView(mBanner);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_play:
                if (null != movieInfo) {
//                    intent = new Intent(MovieDetailActivity.this, PlayMovieActivity.class);
//                    intent.putExtra("movieUrl", movieInfo.getNativeUri());
//                    intent.putExtra("movieName", movieInfo.getName());
//                    startActivity(intent);

                    showBanner();
                    Utils.openVideo(this, movieInfo.getNativeUri());
                    handler.sendEmptyMessageDelayed(0, 4000);
                } else {
                    Utils.toast(this, "The movie lost information and failed to play");
                }
                break;

//            case R.id.tv_keep:
            //没有收藏时
//                if (!isCollect) {
//                    Toast.toast(getApplicationContext(), "您已成功收藏该电影");
//                    mBtnMovieKeep.setText("已收藏");
//                    mBtnMovieKeep.setBackground(getResources().getDrawable(R.drawable.bg_keep_on));
//                    isCollect = true;
//                } else {
//                    Toast.toast(getApplicationContext(), "您已取消收藏该电影");
//                    mBtnMovieKeep.setText("收藏");
//                    mBtnMovieKeep.setBackground(getResources().getDrawable(R.drawable.style_play));
//                    isCollect = false;
//                }
//                break;
        }
    }

    @Override
    public void onItemClick(View view, MovieInfo data) {
//        movieInfo = data;
//        initMovieInfo();
        Utils.toast(this, "Offline version, unconnected server resources");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBtnMoviePlay.requestFocus();
    }
}

