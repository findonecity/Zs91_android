package com.example.loginlib.changepassword

import com.example.baselib.base.base.BasePresenter

/**
 * Created by zj on 2019/4/11.
 * is use for: 修改密码
 */
class ChangePasswordPresenter : BasePresenter<ChangePasswordContract.View>(),
    ChangePasswordContract.Presenter {
    override fun changePassword(code: String, newPassword: String, newPassword2: String,companyId:String) {
        checkViewAttached()
        mView?.apply {
            showLoading()
        }
        addSubscription(disposable = loginModel.changePassword(code,newPassword,newPassword2,companyId)
            .subscribe({
                mView?.apply {
                    closeLoading()
                    showChangePassword(it)
                }
            },{
                mView?.apply {
                    closeLoading()
                }
            }))
    }

    override fun getCode(mobile: String, companyId: String) {
        checkViewAttached()
        mView?.apply {
            showLoading()
        }
        addSubscription(disposable = loginModel.getCode(mobile,companyId)
            .subscribe({
                mView?.apply {
                    closeLoading()
                    showCodeProgress(it)
                }
            },{
                mView?.apply {
                    closeLoading()
                }
            }))
    }

    private val loginModel by lazy { com.example.loginlib.lifeModel() }
}