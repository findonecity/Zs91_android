package com.example.loginlib.changepassword

import com.example.baselib.base.base.IBaseView
import com.example.loginlib.lifeBean.ChangePasswordBean
import com.example.loginlib.lifeBean.GetCodeBean

/**
 * Created by zj on 2019/4/11.
 * is use for: 忘记密码
 */
class ChangePasswordContract {
    interface View: IBaseView {
        fun showCodeProgress(bean: GetCodeBean)
        fun showChangePassword(bean: ChangePasswordBean)
    }
    interface Presenter{
        fun getCode(mobile:String,companyId:String)
        fun changePassword(code:String,newPassword:String,newPassword2: String,companyId:String)
    }
}