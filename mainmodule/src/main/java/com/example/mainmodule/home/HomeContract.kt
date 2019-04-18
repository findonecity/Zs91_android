package com.example.mainmodule.home

import com.example.baselib.base.base.IBaseView
import com.example.mainmodule.bean.HomeMessageBean

/**
 * Created by zj on 2019/4/1.
 * is use for:
 */
class HomeContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun setHomeMessage(homeMessageBean: HomeMessageBean)
    }
    interface Presenter{
        fun getHomeMessage()
    }
}