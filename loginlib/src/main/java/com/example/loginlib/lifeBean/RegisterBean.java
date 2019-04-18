package com.example.loginlib.lifeBean;

import java.io.Serializable;

/**
 * Created by zj on 2019/4/11.
 * is use for: 注册
 */
public class RegisterBean implements Serializable {
    private String type;
    private String errkey;
    private Integer company_id;
    private String err;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
