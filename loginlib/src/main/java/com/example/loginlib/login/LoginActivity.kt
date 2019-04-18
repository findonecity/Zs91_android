package com.example.loginlib.login

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import com.example.baselib.base.base.BaseActivity
import com.example.baselib.base.base.Constant
import com.example.loginlib.R
import com.example.loginlib.register.RegisterActivity
import com.vondear.rxtool.RxAnimationTool
import com.vondear.rxtool.RxBarTool
import com.vondear.rxui.activity.AndroidBug5497Workaround
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by zj on 2019/4/10.
 * is use for: 登录
 */
class LoginActivity:BaseActivity(), LoginContract.View {
    override fun setLoginTrueResult(token: String) {
        showHintDialog("登录成功", Constant.HINT_SUCCESS)
    }

    override fun setLoginFalseResult(errorKey: String) {
        showHintDialog(errorKey, Constant.HINT_FAIL)
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }

    private val mPresenter by lazy { LoginPresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun layoutId(): Int = R.layout.activity_login

    private var screenHeight = 0//屏幕高度
    private var keyHeight = 0 //软件盘弹起后所占高度
    private val scale = 0.6f //logo缩放比例
    private val height = 0
    override fun initData() {
    }

    override fun initView() {
        RxBarTool.setTransparentStatusBar(this)//状态栏透明化
        RxBarTool.StatusBarLightMode(this)

        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this)
        }

        screenHeight = this.resources.displayMetrics.heightPixels //获取屏幕高度
        keyHeight = screenHeight / 3//弹起高度为屏幕高度的1/3

        scrollView.setOnTouchListener { v, event -> true }
        scrollView.addOnLayoutChangeListener { v, left, top, rigth, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if (oldBottom != 0 && bottom != 0 && oldBottom - bottom > keyHeight) {
                val dist:Float = (mContent.bottom - bottom).toFloat()
                if (dist > 0) {
                    val mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist)
                    mAnimatorTranslateY.duration = 300
                    mAnimatorTranslateY.interpolator = LinearInterpolator()
                    mAnimatorTranslateY.start()
                    RxAnimationTool.zoomIn(logo, 0.6f, dist)
                }
            } else if (oldBottom != 0 && bottom != 0 && bottom - oldBottom > keyHeight) {
                if (mContent.bottom - oldBottom > 0) {
                    val mAnimatorTranslateY =
                        ObjectAnimator.ofFloat(mContent, "translationY", mContent.translationY, 0f)
                    mAnimatorTranslateY.duration = 300
                    mAnimatorTranslateY.interpolator = LinearInterpolator()
                    mAnimatorTranslateY.start()
                    //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                    RxAnimationTool.zoomOut(logo, 0.6f)
                }
            }
        }

        isShowPassword.setOnClickListener {
            if (etPassword.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                isShowPassword.setImageResource(R.drawable.pass_visuable)
            } else {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                isShowPassword.setImageResource(R.drawable.pass_gone)
            }
            val pwd = etPassword.text.toString()
            if (!TextUtils.isEmpty(pwd))
                etPassword.setSelection(pwd.length)
        }

        /**
         * 新用户注册
         */
        tvRegister.setOnClickListener {
            val bundle = Bundle()
            beginActivity(RegisterActivity::class.java,bundle,-1000)
        }

        /**
         * 忘记密码
         */
        tvForgetPassword.setOnClickListener {
            val bundle = Bundle()
            beginActivity(com.example.loginlib.changepassword.ChangePasswordActivity::class.java,bundle,-1000)
        }
    }

    private fun isFullScreen(activity: Activity): Boolean {
        return activity.window.attributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == WindowManager.LayoutParams.FLAG_FULLSCREEN
    }

    override fun startRequest() {
        /**
         * 登录
         */
        btnLogin.setOnClickListener {
            val userName = etUserName.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (userName.isEmpty() or password.isEmpty()){
                showHintDialog("账号和密码不能为空", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            mPresenter.login(userName,password)
        }
    }
}