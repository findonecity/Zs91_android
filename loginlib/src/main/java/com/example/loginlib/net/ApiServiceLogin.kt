package com.example.mainmodule.net

import com.example.loginlib.lifeBean.ChangePasswordBean
import com.example.loginlib.lifeBean.GetCodeBean
import com.example.loginlib.lifeBean.LoginBean
import com.example.loginlib.lifeBean.RegisterBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by zj on 2019/3/23.
 * is use for: api请求管理器
 */
interface ApiServiceLogin {
    /**
     * 登录
     */
    @POST("loginof.html")
    @FormUrlEncoded
    fun login(
        @Field("username") userName: String,
        @Field("passwdjm") password: String,
        @Field("appid") appId: String,
        @Field("appsystem") appSystem: String,
        @Field("open_type") openType: String,
        @Field("loginflag") loginFlag: Int): Observable<LoginBean>

    /**
     * 注册
     */
    @POST("regsave.html")
    @FormUrlEncoded
    fun register(
        @Field("userName") userName: String,
        @Field("passwd") password: String,
        @Field("contact") contact: String,
        @Field("industry_code") industry_code: String,
        @Field("companyname") companyName: String,
        @Field("clientid") clientId: String): Observable<RegisterBean>

    /**
     * 获取验证码
     */
    @POST("user/auth_yzmcode.html")
    @FormUrlEncoded
    fun getCode(
        @Field("mobile") mobile: String,
        @Field("forgettype") forgetType: String,
        @Field("company_id") companyId: String): Observable<GetCodeBean>

    /**
     * 修改密码 --忘记密码
     */
    @POST("user/resetpasswd.html")
    @FormUrlEncoded
    fun changePassword(
        @Field("yzcode") code: String,
        @Field("newcold") newPassword: String,
        @Field("sedcold") newPassword2: String,
        @Field("company_id") companyId: String): Observable<ChangePasswordBean>
}