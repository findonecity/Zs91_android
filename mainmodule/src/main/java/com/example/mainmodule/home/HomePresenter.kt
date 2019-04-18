package com.example.mainmodule.home

import com.example.baselib.base.base.BasePresenter
import com.example.baselib.base.net.exception.ExceptionHandle

/**
 * Created by zj on 2019/4/3.
 * is use for:
 */
class HomePresenter : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    override fun getHomeMessage() {
        checkViewAttached()
        mView?.apply {
            startLoading()
        }
        addSubscription(disposable = homeModel.getHomeMessage()
            .subscribe({
                mView?.apply {
                    finishLoading()
                    mView?.setHomeMessage(it)
                }
            },{
                mView?.apply {
                    finishLoading()
                    showError(
                        ExceptionHandle.handleException(it),
                        ExceptionHandle.errorCode)
                }
            }))
    }

    private val homeModel by lazy { HomeModel() }
}