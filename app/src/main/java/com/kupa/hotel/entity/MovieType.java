package com.kupa.hotel.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Mita on 2017/6/15.
 */
@Table(name = "MovieType")
public class MovieType {

    @Column(name = "classifyId", isId = true, autoGen = false)
    private int classifyId;
    @Column(name = "name")
    private String name;

    public MovieType() {
    }

    public MovieType(int classifyId, String name) {
        this.classifyId = classifyId;
        this.name = name;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
