package com.example.loginlib.login

import com.example.baselib.base.base.BasePresenter
import com.example.baselib.base.net.Constant.RESULT_TRUE
import com.example.loginlib.lifeModel

/**
 * Created by zj on 2019/4/11.
 * is use for:
 */
class LoginPresenter: BasePresenter<LoginContract.View>(),
    LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        checkViewAttached()
        mView?.apply {
            showLoading()
        }
        addSubscription(disposable = loginModel.login(userName,password)
            .subscribe({
                mView?.apply {
                    closeLoading()
                    if (it.err == RESULT_TRUE){
                        mView?.setLoginFalseResult(it.errkey)
                    }else{
                        mView?.setLoginTrueResult(it.token)
                    }
                }
            },{
                mView?.apply {
                    closeLoading()
                }
            }))
    }

    private val loginModel by lazy { lifeModel() }
}