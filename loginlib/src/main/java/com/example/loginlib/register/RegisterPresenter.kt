package com.example.loginlib.register

import com.example.baselib.base.base.BasePresenter
import com.example.loginlib.lifeModel

/**
 * Created by zj on 2019/4/11.
 * is use for:
 */
class RegisterPresenter : BasePresenter<RegisterContract.View>(),
    RegisterContract.Presenter {
    override fun register(userName: String, password: String, contact: String, industryCode: String,
        companyName: String, clientId: String
    ) {
        checkViewAttached()
        mView?.apply {
            showLoading()
        }
        addSubscription(disposable = loginModel.register(userName,password,contact,industryCode,companyName,clientId)
            .subscribe({
                mView?.apply {
                    closeLoading()

                    mView?.setRegisterResult(it)
                }
            },{
                mView?.apply {
                    closeLoading()
                }
            }))
    }

    private val loginModel by lazy { com.example.loginlib.lifeModel() }
}