package com.example.loginlib.lifeBean;

import java.io.Serializable;

/**
 * Created by zj on 2019/4/11.
 * is use for: 主营业务
 */
public class BusinessBean implements Serializable {
    private String name;
    private String code;

    public BusinessBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
