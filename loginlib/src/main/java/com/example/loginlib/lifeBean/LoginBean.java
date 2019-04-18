package com.example.loginlib.lifeBean;

import java.io.Serializable;

/**
 * Created by zj on 2019/3/25.
 * is use for: 登录
 */
public class LoginBean implements Serializable {
    private String token;
    private String errkey;
    private Integer result;
    private String err;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrkey() {
        return errkey;
    }

    public void setErrkey(String errkey) {
        this.errkey = errkey;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
