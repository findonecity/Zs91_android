package com.example.mainmodule.net

import com.example.mainmodule.bean.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by zj on 2019/3/23.
 * is use for: api请求管理器
 */
interface ApiServiceMain {
    /**
     * 获取首页信息
     */
    @GET("index_new.html")
    fun getHomeMessage(): Observable<HomeMessageBean>

    /**
     * 根据关键词获取相关列表
     */
    @GET("keywords/searchtis.html")
    fun getCorrelationList(@Query("keywords") keywords:String): Observable<ArrayList<CorrelationBean>>
}