package com.kupa.hotel.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.kupa.hotel.R;
import com.kupa.hotel.utils.URLencode;
import com.kupa.hotel.utils.Utils;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_play_movie)
public class PlayMovieActivity extends BaseActivity {

    //    @ViewInject(R.id.nice_video_player)
//    private NiceVideoPlayer mNiceVideoPlayer;
//    private StandardGSYVideoPlayer mVideo;
//    private NiceVideoPlayerController controller;
    private AudioManager mAudioManager;
    private WindowManager wManager;

    private View mBanner;

    private int maxVoice;
    private String url;
    private String movieUrl;
    private String movieName;
    private int movieId;
    //    private MovieInfo movie;
    private boolean isPush;

    //播放进度增加(减少)15s
    private static final int PROGRESS = 15000;
    private static int CURRENT_POSITION;

    private PlayStatusReceiver playStatusReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mVideo = (StandardGSYVideoPlayer) findViewById(R.id.video_view);
        Intent intent = getIntent();
        isPush = intent.getBooleanExtra("isPush", false);
        if (isPush) {
            movieId = intent.getIntExtra("movieId", 0);
            url = intent.getStringExtra("movieUrl");
            registerReceiver();
        } else {
            url = intent.getStringExtra("movieUrl");
//            url = "/storage/sdcard/Movies/变形金刚3.预告片.mp4";
//            Utils.toast(PlayMovieActivity.this, "播放的URL：" + url);
            movieName = intent.getStringExtra("movieName");
//            movie = MoviesUtils.queryMovieById(movieId);
//            url = movie.getNativeUri();
        }
        Utils.log("播放的URL：" + url);
        initPlayer();
        initVoice();
    }

    /**
     * 初始化音量控制
     */
    private void initVoice() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVoice = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        controller.setMaxVoice(maxVoice);
    }

    private void initPlayer() {
        if (!TextUtils.isEmpty(url)) {
//            mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_NATIVE);
////            url = URLencode.encodeUrl(Contacts.API_RECOMMEND_RES + movie.getNativeUri());
//            url = URLencode.encodeUrl(url);
//            mNiceVideoPlayer.setUp(url, null);
////        mNiceVideoPlayer.setUp("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4", null);
//            controller = new NiceVideoPlayerController(this);
//            controller.setTitle(movieName);
////            controller.setImage(movie.getPhotoName());
//            mNiceVideoPlayer.setController(controller);
//            mNiceVideoPlayer.enterFullScreen();
//            mNiceVideoPlayer.start();
            url = URLencode.encodeUrl(url);
//            mVideo.setUp(url,true,"");
//            mVideo.startPlayLogic();
//            mVideo.setVideoURI(Uri.parse(url));
//            mVideo.canSeekForward();
//            mVideo.canSeekBackward();
//            mVideo.canPause();
//
//            MediaController controller = new MediaController(this);
//            mVideo.setMediaController(controller);
//            mVideo.start();
//            mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    PlayMovieActivity.this.finish();
//                }
//            });
        } else {
            Utils.toast(this, "播放链接无效");
        }

    }

    /**
     * 展示广告页
     */
    private void showBanner() {
        wManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        // 悬浮所有页面之上
        lp.type = WindowManager.LayoutParams.TYPE_PHONE;
        lp.width = 400;
        lp.height = 400;
        // 失去焦点
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        lp.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        mBanner = LayoutInflater.from(this).inflate(R.layout.layout_banner, null, false);
        wManager.addView(mBanner, lp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
//                CURRENT_POSITION = mVideo.getCurrentPosition();
//                mVideo.seekTo(CURRENT_POSITION + PROGRESS);
//                goOrBack(true);
//                if (isPush)
//                    MoviesUtils.dealPlayStatus(this, Contacts.PLAY_STATUS_RESTART, mNiceVideoPlayer.getCurrentPosition());
                return true;

            case KeyEvent.KEYCODE_DPAD_LEFT:
//                CURRENT_POSITION = mVideo.getCurrentPosition();
//                mVideo.seekTo(CURRENT_POSITION - PROGRESS);
//                goOrBack(false);
//                if (isPush)
//                    MoviesUtils.dealPlayStatus(this, Contacts.PLAY_STATUS_RESTART, mNiceVideoPlayer.getCurrentPosition());
                return true;

            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
//                if (mNiceVideoPlayer.isPlaying()) {
//                    mNiceVideoPlayer.pause();
//                    showBanner();
//                    controller.setTopBottomVisible(true);
//                } else if (mNiceVideoPlayer.isPaused()) {
//                    mNiceVideoPlayer.restart();
//                    wManager.removeView(mBanner);
//                }
//                if (isPush)
//                    MoviesUtils.dealPlayStatus(this, Contacts.PLAY_STATUS_PAUSE, mNiceVideoPlayer.getCurrentPosition());
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
//                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//                controller.setVoice(current, maxVoice);
                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
//                int currentDown = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//                controller.setVoice(currentDown, maxVoice);
                return true;

            case KeyEvent.KEYCODE_BACK:
//                if (isPush)
//                    MoviesUtils.dealPlayStatus(this, Contacts.PLAY_STATUS_BACK, mNiceVideoPlayer.getCurrentPosition());
                finish();
                return true;
        }
        return false;
    }

    /**
     * 快进或者快退
     *
     * @param isGo
     */
    private void goOrBack(boolean isGo) {
//        controller.setTopBottomVisible(true);
//        int position;
//        if (isGo) {
//            position = (int) (mNiceVideoPlayer.getCurrentPosition() + PROGRESS);
//        } else {
//            position = (int) (mNiceVideoPlayer.getCurrentPosition() - PROGRESS);
//        }
//        mNiceVideoPlayer.seekTo(position);
    }

    @Override
    protected void onStop() {
        if (mBanner != null && wManager != null)
            wManager.removeView(mBanner);
//        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
//        mVideo.release();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (null != playStatusReceiver)
            unregisterReceiver(playStatusReceiver);
        super.onDestroy();
    }

    private void registerReceiver() {
        playStatusReceiver = new PlayStatusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kupa.MOVIE_STATUS");
        registerReceiver(playStatusReceiver, filter);
    }

    private class PlayStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra("status", -1);
//            MoviesUtils.dealPlayStatus(PlayMovieActivity.this, status, mNiceVideoPlayer.getCurrentPosition());
        }
    }

}
