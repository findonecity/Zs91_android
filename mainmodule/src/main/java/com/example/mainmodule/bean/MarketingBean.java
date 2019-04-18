package com.example.mainmodule.bean;

import java.io.Serializable;

/**
 * Created by zj on 2019/4/4.
 * is use for:
 */
public class MarketingBean implements Serializable {
    private String title;
    private int mipmap;
    private String message;

    public MarketingBean(String title, int mipmap, String message) {
        this.title = title;
        this.mipmap = mipmap;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
