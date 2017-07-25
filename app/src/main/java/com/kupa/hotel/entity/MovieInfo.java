package com.kupa.hotel.entity;

import java.io.Serializable;

/**
 * Created by Mita on 2017/6/16.
 */
public class MovieInfo implements Serializable{

    private int id;//ID
    private int movieId;
    private String name;
    private int typeId;
    private String areaName;//地区
    private String profile;//简介
    private String director;//导演
    private String performer;//演员
    private int duration;//时长
    private String keyWord;//关键字
    private Double grade;//影片评分
    private long showTime;
    private int photoName;
    private String previewUri;
    private String nativeUri;
    private String cloudUri;
    private String onlineUri;

    public MovieInfo() {
    }

    public MovieInfo(String name, int photoName) {
        this.name = name;
        this.photoName = photoName;
    }

    public MovieInfo(String name, String areaName, String profile, String director, String performer, int duration, String keyWord, Double grade, long showTime, int photoName, String previewUri, String nativeUri) {
        this.name = name;
        this.areaName = areaName;
        this.profile = profile;
        this.director = director;
        this.performer = performer;
        this.duration = duration;
        this.keyWord = keyWord;
        this.grade = grade;
        this.showTime = showTime;
        this.photoName = photoName;
        this.previewUri = previewUri;
        this.nativeUri = nativeUri;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getPhotoName() {
        return photoName;
    }

    public void setPhotoName(int photoName) {
        this.photoName = photoName;
    }

    public String getPreviewUri() {
        return previewUri;
    }

    public void setPreviewUri(String previewUri) {
        this.previewUri = previewUri;
    }

    public String getNativeUri() {
        return nativeUri;
    }

    public void setNativeUri(String nativeUri) {
        this.nativeUri = nativeUri;
    }

    public String getCloudUri() {
        return cloudUri;
    }

    public void setCloudUri(String cloudUri) {
        this.cloudUri = cloudUri;
    }

    public String getOnlineUri() {
        return onlineUri;
    }

    public void setOnlineUri(String onlineUri) {
        this.onlineUri = onlineUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getShowTime() {
        return showTime;
    }

    public void setShowTime(long showTime) {
        this.showTime = showTime;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
