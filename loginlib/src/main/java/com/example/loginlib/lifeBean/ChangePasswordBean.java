package com.example.loginlib.lifeBean;

import java.io.Serializable;

/**
 * Created by zj on 2019/4/17.
 * is use for: 忘记密码
 */
public class ChangePasswordBean implements Serializable {
    private String errkey;
    private String err;

    public String getErrkey() {
        return errkey;
    }

    public void setErrkey(String errkey) {
        this.errkey = errkey;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
