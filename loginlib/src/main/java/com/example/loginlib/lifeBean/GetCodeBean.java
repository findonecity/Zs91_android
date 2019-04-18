package com.example.loginlib.lifeBean;

import java.io.Serializable;

/**
 * Created by zj on 2019/4/12.
 * is use for: 获取验证码
 */
public class GetCodeBean implements Serializable {
    private String errkey;
    private Integer company_id;
    private String err;

    public String getErrkey() {
        return errkey;
    }

    public void setErrkey(String errkey) {
        this.errkey = errkey;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
