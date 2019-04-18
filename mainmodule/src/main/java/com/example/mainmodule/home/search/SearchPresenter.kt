package com.example.mainmodule.home.search

import com.example.baselib.base.base.BasePresenter

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
class SearchPresenter:BasePresenter<SearchContract.View>(),
    SearchContract.Presenter {
    //获取相关列表
    override fun getCorrelationList(keywords:String) {
        checkViewAttached()
        mView?.apply {
        }
        addSubscription(disposable = searchModel.getCorrelationList(keywords)
            .subscribe({
                mView?.apply {
                    mView?.setCorrelationList(it)
                }
            },{
                mView?.apply {
                }
            }))
    }

    private val searchModel by lazy { SearchModel() }
}