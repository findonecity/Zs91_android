package com.example.mainmodule.home.search

import com.example.baselib.base.base.IBaseView
import com.example.mainmodule.bean.CorrelationBean

/**
 * Created by zj on 2019/4/8.
 * is use for: 搜索
 */
class SearchContract {
    interface View: IBaseView {
        fun setCorrelationList(list:ArrayList<CorrelationBean>)
    }
    interface Presenter{
        fun getCorrelationList(keywords:String)
    }
}