package com.example.loginlib.changepassword

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import androidx.core.content.ContextCompat
import com.example.baselib.base.base.BaseActivity
import com.example.baselib.base.base.Constant
import com.example.baselib.base.net.Constant.RESULT_TRUE
import com.example.baselib.base.utils.SharedPreferencesManager
import com.example.loginlib.R
import com.example.loginlib.lifeBean.ChangePasswordBean
import com.example.loginlib.lifeBean.GetCodeBean
import kotlinx.android.synthetic.main.activity_changepassword.*
import kotlinx.android.synthetic.main.top_toolbar.*
import java.util.*

/**
 * Created by zj on 2019/4/10.
 * is use for: 重置密码
 */
class ChangePasswordActivity:BaseActivity(), ChangePasswordContract.View {
    override fun showChangePassword(bean: ChangePasswordBean) {
        if (bean.err == RESULT_TRUE){
            showHintDialog("重置密码错误，原因为:  " + bean.errkey,Constant.HINT_FAIL)
        }else{
            showHintDialog("重置密码成功",Constant.HINT_SUCCESS)
        }
    }

    var jishi:Int = 0
    var timer : Timer? = null
    override fun showCodeProgress(bean: GetCodeBean) {
        if (bean.err == RESULT_TRUE){
            showHintDialog("验证码发送失败,原因为: " + bean.errkey,Constant.HINT_FAIL)
        }else{
            companyId = bean.company_id.toString()
            showHintDialog("验证码已发送",Constant.HINT_SUCCESS)
            jishi= 60
            tvGetCode.isEnabled=false
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handle.sendEmptyMessage(jishi--)
                }
            }, 0, 1000)
        }
    }

    val handle = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == 0) {
                tvGetCode.isEnabled = true
                tvGetCode.setTextColor(ContextCompat.getColor(this@ChangePasswordActivity, R.color.green1))
                tvGetCode.text = "重新获取"

                timer?.cancel()
            } else{
                tvGetCode.text = "（" + msg?.what + "秒）"
                tvGetCode.setTextColor(ContextCompat.getColor(this@ChangePasswordActivity, R.color.text_gray))
            }
        }
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }

    private val mPresenter by lazy { ChangePasswordPresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun layoutId(): Int = R.layout.activity_changepassword

    override fun initData() {
    }

    override fun initView() {
        tvToolbarTitle.text = "忘记密码"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        companyId = SharedPreferencesManager.getInstance().getData(this,"userInfo","companyId")
    }

    private var phone:String = ""
    private var companyId:String = ""
    override fun startRequest() {
        /**
         * 获取验证码
         */
        tvGetCode.setOnClickListener {
            phone = etPhone.text.toString().trim()
            if (phone.isEmpty()){
                showHintDialog("请输入手机号", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            if (!phone.matches("1[3|4|5|7|8][0-9]{9}$".toRegex())){
                showHintDialog("请输入正确的手机号", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            mPresenter.getCode(phone,companyId)
        }

        /**
         * 修改密码
         */
        btnChangePassword.setOnClickListener {
            val newPhone = etPhone.text.toString().trim()
            val code = etCode.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()
            val newPassword2 = etNewPassword2.text.toString().trim()

            if (newPassword != newPassword2){
                showHintDialog("输入的两次密码不一致", Constant.HINT_FAIL)
                return@setOnClickListener
            }

            if(code.isEmpty() or newPassword.isEmpty() or newPassword2.isEmpty() or newPhone.isEmpty()){
                showHintDialog("请输入必要信息", Constant.HINT_FAIL)
                return@setOnClickListener
            }

            if (newPhone != phone){
                showHintDialog("手机号不是获取验证码时填写的手机号", Constant.HINT_FAIL)
                return@setOnClickListener
            }

            mPresenter.changePassword(code,newPassword,newPassword2,companyId)
        }
    }
}