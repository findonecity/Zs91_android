package com.example.mainmodule.home.search

import com.example.common_android_base.net.seheduler.SchedulerUtils
import com.example.mainmodule.bean.CorrelationBean
import com.example.mainmodule.net.RetrofitManagerMain
import io.reactivex.Observable

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
class SearchModel {
    fun getCorrelationList(keywords:String): Observable<ArrayList<CorrelationBean>>{
        return RetrofitManagerMain.apiService.getCorrelationList(keywords)
            .compose(SchedulerUtils.ioToMain())
    }
}