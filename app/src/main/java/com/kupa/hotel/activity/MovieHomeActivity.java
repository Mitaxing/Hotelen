package com.kupa.hotel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kupa.hotel.R;
import com.kupa.hotel.adapter.MovieTypeAdapter;
import com.kupa.hotel.adapter.MoviesAdapter;
import com.kupa.hotel.entity.Movie;
import com.kupa.hotel.entity.MovieInfo;
import com.kupa.hotel.layout.TvGridLayoutManager;
import com.kupa.hotel.playfile.FileUtil;
import com.kupa.hotel.playfile.PlayFileService;
import com.kupa.hotel.utils.Contacts;
import com.kupa.hotel.utils.DiffCallBack;
import com.kupa.hotel.utils.MoviesUtils;
import com.kupa.hotel.utils.QrCodeUtil;
import com.kupa.hotel.utils.Toast;
import com.kupa.hotel.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static com.kupa.hotel.utils.LoadMovies.filterNetWorkMovies;
import static com.kupa.hotel.utils.LoadMovies.getNetWorkMovie;


@ContentView(R.layout.activity_all_movies)
public class MovieHomeActivity extends BaseActivity implements MovieTypeAdapter.OnRecyclerViewItemSelectListener, MoviesAdapter.OnRecyclerViewItemSelectListener,
        MoviesAdapter.OnRecyclerViewItemClickListener {

    @ViewInject(R.id.rv_movie_types)
    private RecyclerView mRvType;
    @ViewInject(R.id.rv_all_movies)
    private RecyclerView mRvMovie;
    private MoviesAdapter mMoviesAdapter;
    private MovieTypeAdapter mTypeAdapter;
    private ProgressDialog mLoadingAllMovieDialog = null;
    private LoadMoviesTask mLoadNetWorkMoviesTask = null;
    @ViewInject(R.id.movie_type)
    private TextView mMovieHomeTopMovieType;
    @ViewInject(R.id.movie_current_num)
    private TextView mTvMoviePosition;
    @ViewInject(R.id.movie_qr_code)
    private ImageView mQrCode;
    @ViewInject(R.id.movie_arrow_up)
    private ImageView mIvArrowUp;
    @ViewInject(R.id.movie_arrow_down)
    private ImageView mIvArrowDown;

    private int mCurrentMovieType, mCurrentMoviesSize, mCurrentMovieItemPosition;
    private int currentType = 0, lastItemPosition = 0, firstItemPosition = 0, visiblePosition;
    private String[] mListMovieType = {"HOT", "Science fiction", "Romance", "Martial Arts", "Action", "Horror",
            "Animation", "Musical", "Biography"};

    //    private List<MovieType> typeList = new ArrayList<>();
    private List<MovieInfo> movieList = new ArrayList<>();

    private boolean isFirst = true;
    private long mLastKeyDownTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieList = MoviesUtils.getMovieInfo();
        initRecycle();
//        startPlayFileService();
//        init();
//        requestMovieTypes();

//        getLocalTypes();
    }

    /**
     * 获取本地存储的电影类型
     */
    private void getLocalTypes() {
//        typeList = MoviesUtils.queryTypes();
//        if (typeList != null)
//            if (typeList.size() > 0) {
//                initRecycle();
//            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst) {
            isFirst = false;
            mRvType.requestFocus();
            //设置二维码
//            setQRCode();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int position = msg.arg1;
                    switch (position) {
                        case 0:
                            updateData(MoviesUtils.getMovieInfo());
                            break;

                        case 1:
                            updateData(MoviesUtils.getKeHuan());
                            break;

                        case 2:
                            updateData(MoviesUtils.getAiQing());
                            break;
                        case 3:
                            updateData(MoviesUtils.getGuZhuang());
                            break;

                        case 4:
                            updateData(MoviesUtils.getDongzuo());
                            break;

                        case 5:
                            updateData(MoviesUtils.getKongBu());
                            break;

                        case 6:
                            updateData(MoviesUtils.getDonghua());
                            break;

                        case 7:
                            updateData(MoviesUtils.getGeWu());
                            break;

                        case 8://历史
                            updateData(MoviesUtils.getLiShi());
                            break;
                    }
                    break;

                case 1:

                    break;
            }
        }
    };

    private void updateData(List<MovieInfo> newData) {
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new DiffCallBack(movieList, newData), true);
        diffResult.dispatchUpdatesTo(mMoviesAdapter);
        movieList = newData;
        mMoviesAdapter.setDatas(movieList);
    }

    /**
     * 查询当前类型下的电影
     *
     * @param id
     */
    private void requestCurrentTypeMovie(final int id) {
        RequestParams params = new RequestParams(Contacts.API_TYPE_MOVIE);
        params.addParameter("cid", id);
        params.addParameter("page", 0);
        params.addParameter("pageSize", 20);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("服务器返回的电影：" + result);
                List<MovieInfo> movies = MoviesUtils.resolveMovieInfo(result, id);
                Utils.log("服务器返回的电影个数：" + movies);
                updateData(movies);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Intent intent = new Intent(this, PlayFileService.class);
//        stopService(intent);
    }

    /*
    * 开启service
    * */
    private void startPlayFileService() {
        Intent intent = new Intent(this, PlayFileService.class);
        startService(intent);
    }

    private void getIpAddress() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        Log.i("hm", "ip=" + hostIp);
        Toast.toast(MovieHomeActivity.this, "IP=" + hostIp);
        FileUtil.ip = hostIp;
        FileUtil.port = 2222;
    }

    /**
     * 设置带mac地址的二维码
     */
    private void setQRCode() {
        Bitmap qrCode = QrCodeUtil.createQRCodeBitmap(305, Contacts.ROOMID, 123456789, 174, 174);
        mQrCode.setImageBitmap(qrCode);
    }

    /**
     * 加载网络存储服务器上的资源
     */
    private void loadNetWorkMovies() {
        if (mLoadNetWorkMoviesTask == null || mLoadNetWorkMoviesTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            mLoadNetWorkMoviesTask = new LoadMoviesTask();
            mLoadNetWorkMoviesTask.execute();
        }
    }

    /**
     * 初始化布局管理器
     */
    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvType.setLayoutManager(linearLayoutManager);
        mTypeAdapter = new MovieTypeAdapter(this, mListMovieType);
        mTypeAdapter.setOnItemSelectListener(this);
        mRvType.setAdapter(mTypeAdapter);

        RecyclerView.LayoutManager tvGridLayoutManager = new TvGridLayoutManager(this, 5);
        mRvMovie.setLayoutManager(tvGridLayoutManager);
        mMoviesAdapter = new MoviesAdapter(this, movieList);
        mMoviesAdapter.setOnItemClickListener(this);
        mMoviesAdapter.setOnItemSelectListener(this);
        mRvMovie.setAdapter(mMoviesAdapter);
    }

    @Event(value = {R.id.rv_movie_types, R.id.rv_all_movies}, type = View.OnFocusChangeListener.class)
    private void focusListener(View view, boolean hasFocus) {
        if (hasFocus) {
            if (((RecyclerView) view).getChildCount() > 0) {
                ((RecyclerView) view).getChildAt(0).requestFocus();
            }
        }
    }

    /**
     * 显示或隐藏向上向下箭头
     *
     * @param position
     */
    void ShowOrHideArrow(int position) {
        int size = mListMovieType.length;
        if (size > 0) {
            if (position == 0) {
                mIvArrowUp.setVisibility(View.INVISIBLE);
            } else {
                mIvArrowUp.setVisibility(View.VISIBLE);
            }

            if (position == size - 1) {
                mIvArrowDown.setVisibility(View.INVISIBLE);
            } else {
                mIvArrowDown.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onItemSelect(View view, int position) {
        ShowOrHideArrow(position);
        mMovieHomeTopMovieType.setText(mListMovieType[position]);
        Utils.log("传过来的类型位置：" + position);
        currentType = position;
        Utils.log("传过来的类型位置currentType：" + currentType);

        Message message = new Message();
        message.what = 0;
        message.arg1 = position;
        handler.sendMessage(message);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (currentType == 0 && position < 5) {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra("movie", movieList.get(position));
            Utils.log("电影位置：" + position);
//        intent.putExtra("typeId", typeList.get(currentType).getClassifyId());
            startActivity(intent);
        } else {
            Utils.toast(this, "离线版本，未连接服务器资源");
        }
    }

    @Override
    public void onMovieItemSelect(View view, int position) {
        mCurrentMovieItemPosition = position;
        mTvMoviePosition.setText((position + 1) + " / " + movieList.size());
    }

    /**
     * 加载电影
     * Params, Progress, Result
     */
    class LoadMoviesTask extends AsyncTask<String, Void, List<Movie>> {
        private List<Movie> movieInfos = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingAllMovieDialog.show();
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            movieInfos = getNetWorkMovie(movieInfos);
            movieInfos = filterNetWorkMovies(movieInfos);
//            mNetWorkMovies = movieInfos;
            return movieInfos;
        }

        @Override
        protected void onPostExecute(List<Movie> result) {
            super.onPostExecute(result);
            mLoadingAllMovieDialog.dismiss();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                if (mRvType.hasFocus() && currentType >= 0) {
                    if (visiblePosition > 0)
                        visiblePosition--;
                    if (currentType == 0) {
                        return true;
                    }

                    long current = System.currentTimeMillis();
                    if (current - mLastKeyDownTime < 300) {
                        return true;
                    } else {
                        mLastKeyDownTime = current;
                    }
                }
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN://向下
                if (mRvType.hasFocus() && currentType < mListMovieType.length) {
//                    mRvType.scrollBy(0,5);
                    if (visiblePosition < 5) {
                        visiblePosition++;
                    }
                    if (currentType == mListMovieType.length - 1)
                        return true;

                    long current = System.currentTimeMillis();
                    if (current - mLastKeyDownTime < 300) {
                        return true;
                    } else {
                        mLastKeyDownTime = current;
                    }
                }
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                if (mCurrentMovieItemPosition % 5 == 0 && mRvMovie.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && mRvMovie.hasFocus()) {
                    if (mRvMovie.getChildCount() > 0)
                        mRvMovie.getChildAt(0).requestFocus();
                    Utils.log("visiblePosition：" + visiblePosition);
                    mRvType.getChildAt(visiblePosition).requestFocus();
                    mTvMoviePosition.setVisibility(View.GONE);
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                if (mRvType.hasFocus() & mRvType.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && mRvMovie.getChildCount() != 0) {
//                    int i = ((GridLayoutManager) mRvMovie.getLayoutManager()).findFirstVisibleItemPosition();
                    mRvMovie.getChildAt(0).requestFocus();
                    mTvMoviePosition.setVisibility(View.VISIBLE);
                    return true;
                }
                break;

            case KeyEvent.KEYCODE_BACK://返回
                if (event.getRepeatCount() == 0) {
                    finish();
                }
                return true;
        }
        return false;
    }

    /**
     * 是否处理按键事件
     *
     * @return
     */
    private boolean receiveEvent() {
        long current = System.currentTimeMillis();
        boolean res;
        if (current - mLastKeyDownTime < 300) {
            res = true;
        } else {
            res = false;
            mLastKeyDownTime = current;
        }
        return res;
    }

    private int getMovieTypeCurrentType() {
        RecyclerView.LayoutManager layoutManager = mRvType.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            currentType = mCurrentMovieType - firstItemPosition;
            return currentType;
        } else {
            return 0;
        }
    }

    public static Boolean isEndLine(RecyclerView recyclerView, int currentItemPosition) {
        int countLine, moviesCount = recyclerView.getChildCount();
        if (moviesCount % 5 != 0) {
            countLine = (moviesCount / 5) + 1;
        } else {
            countLine = (moviesCount / 5);
        }
        if (currentItemPosition > (5 * (countLine - 1)) && recyclerView.getScrollState() == recyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }
}
