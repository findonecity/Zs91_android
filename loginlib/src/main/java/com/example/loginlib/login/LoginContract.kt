package com.example.loginlib.login

import com.example.baselib.base.base.IBaseView

/**
 * Created by zj on 2019/4/11.
 * is use for: 登录c
 */
class LoginContract {
    interface View: IBaseView {
        fun setLoginTrueResult(token:String)
        fun setLoginFalseResult(errorKey:String)
    }
    interface Presenter{
        fun login(userName:String,password:String)
    }
}