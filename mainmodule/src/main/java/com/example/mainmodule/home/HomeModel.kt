package com.example.mainmodule.home

import com.example.common_android_base.net.seheduler.SchedulerUtils
import com.example.mainmodule.bean.HomeMessageBean
import com.example.mainmodule.net.RetrofitManagerMain
import io.reactivex.Observable

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
class HomeModel {
    fun getHomeMessage(): Observable<HomeMessageBean> {
        return RetrofitManagerMain.apiService.getHomeMessage()
            .compose(SchedulerUtils.ioToMain())
    }
}