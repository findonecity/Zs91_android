package com.example.loginlib

import com.example.common_android_base.net.seheduler.SchedulerUtils
import com.example.baselib.base.utils.MD5helper
import com.example.loginlib.lifeBean.ChangePasswordBean
import com.example.loginlib.lifeBean.GetCodeBean
import com.example.loginlib.lifeBean.LoginBean
import com.example.loginlib.lifeBean.RegisterBean
import com.example.mainmodule.net.RetrofitManagerLogin
import io.reactivex.Observable

/**
 * Created by zj on 2019/4/11.
 * is use for:
 */
class lifeModel {
    /**
     * 登录
     */
    fun login(userName:String,password:String): Observable<LoginBean> {
        return RetrofitManagerLogin.apiService.login(
            userName, MD5helper.MD5(password),"appId","android","app.zz91.com",1)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 注册
     */
    fun register(userName:String,password:String,contact:String,industryCode:String,companyName:String,clientId:String): Observable<RegisterBean> {
        return RetrofitManagerLogin.apiService.register(
            userName, MD5helper.MD5(password),contact,industryCode,companyName,clientId)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取验证码
     */
    fun getCode(mobile:String,companyId:String): Observable<GetCodeBean> {
        return RetrofitManagerLogin.apiService.getCode(
            mobile, "forgetpasswd", companyId)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 修改密码 --忘记密码
     */
    fun changePassword(code:String,newPassword:String,newPassword2: String,companyId:String): Observable<ChangePasswordBean> {
        return RetrofitManagerLogin.apiService.changePassword(
            code, MD5helper.MD5(newPassword), MD5helper.MD5(newPassword2),companyId)
            .compose(SchedulerUtils.ioToMain())
    }
}