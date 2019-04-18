package com.example.loginlib.register

import com.example.baselib.base.base.IBaseView
import com.example.loginlib.lifeBean.RegisterBean

/**
 * Created by zj on 2019/4/11.
 * is use for:
 */
class RegisterContract {
    interface View: IBaseView {
        fun setRegisterResult(bean: RegisterBean)
    }

    interface Presenter{
        fun register(userName:String,password:String,contact:String,industryCode:String,companyName:String,clientId:String)
    }
}