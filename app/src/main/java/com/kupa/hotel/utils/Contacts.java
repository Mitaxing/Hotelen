package com.kupa.hotel.utils;

/**
 * Created by admin on 2017/6/5.
 */

public class Contacts {

    //    public static final String API_BASE = "http://192.168.0.103:8088/KupaMovieSystem/";
    //测试地址
    public static final String API_BASE = "http://192.168.0.111/KupaMovieSystem/";

    //电影推荐
    public static final String API_RECOMMEND_MOVIE = "/queryRecommendInfoList";
    //电影分类
    public static final String API_MOVIE_TYPE = API_BASE + "/client/movie/queryAllPrimaryClassifyList";
    //电影分类下的电影
    public static final String API_TYPE_MOVIE = API_BASE + "/client/movie/queryDetailedMovieInfoListByClassifyId";

    //校验值
    public static final String API_CHECK_CODE = API_BASE +"/client/hotel/getHotelRoomCheckCode";
    //二维码更新回复
    public static final String API_REPLY_QRCODE = API_BASE +"/client/hotel/replayATVHotelRoomCheckCode";
    //电影点播回复
    public static final String API_REPLY_MOVIE_PUSH = API_BASE +"/client/movie/playMovieResultNotice";
    //电影播放结束
    public static final String API_PLAY_MOVIE_COMPLETE = API_BASE + "/client/movie/atvPlayCompleteNotices";
    //同步电影播放时间
    public static final String API_SYNCHRONICE_PLAY_TIME = API_BASE + "/client/movie/synchronizationPlayingTime";

    //首页推荐
    public static final String API_RECOMMEND = API_BASE + "/client/recommend/recommendInfo";

    //推荐图片地址
//    public static final String API_RECOMMEND_RES = "http://192.168.0.103:8088/KupaMovieResources/";
    //资源根路径
    public static final String API_RECOMMEND_RES = "http://192.168.0.111/KupaMovie";
    //首页版本2
    public static final String API_RECOMMEND_V2 = "/Recommend/version_2";
    //首页版本3
    public static final String API_RECOMMEND_V3 = "/Recommend/version_3";

    //美食
    public static final int RECOMMEND_CATE = 1;
    //景点
    public static final int RECOMMEND_TOUR = 2;
    //电影
    public static final int RECOMMEND_MOVIE = 3;
    //商品
    public static final int RECOMMEND_SHOP = 4;

    public static final int ROOMID = 1;
    //成功
    public static final int SUCCESS = 1;
    //失败
    public static final int FAUIL = 0;

    public static final int PUSH_MOVIE = 1;

    //播放状态
    public static final int PLAY_STATUS_START = 0;
    public static final int PLAY_STATUS_PAUSE = 1;
    public static final int PLAY_STATUS_RESTART = 2;
    public static final int PLAY_STATUS_COMPLETE = 3;
    public static final int PLAY_STATUS_ERROR = 4;
    public static final int PLAY_STATUS_BACK = 5;
}
