package com.kupa.hotel.entity;

/**
 * 第五版首页推荐实体类
 * Created by admin on 2017/5/27.
 */
public class Recommend {

    private int id;
    private int type;
    private String title;
    private String name;
    private String content;
    private String bgUrl;
    private int res;

    public Recommend() {
    }

    public Recommend(int id, int type, String title, String name, String content, String bgUrl) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.name = name;
        this.content = content;
        this.bgUrl = bgUrl;
    }

    public Recommend(int id, int type, String title, String name, String content, int res) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.name = name;
        this.content = content;
        this.res = res;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }
}
