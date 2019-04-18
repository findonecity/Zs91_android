package com.example.mainmodule.bean;

import java.io.Serializable;

/**
 * Created by zj on 2019/3/12.
 * is use for:
 */
public class HomeFunctionBean implements Serializable{
    private String title;
    private int mipmap;

    public HomeFunctionBean(String title, int mipmap) {
        this.title = title;
        this.mipmap = mipmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMipmap() {
        return mipmap;
    }

    public void setMipmap(int mipmap) {
        this.mipmap = mipmap;
    }
}
