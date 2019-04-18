package com.example.zs91_android.application

import android.Manifest
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.baselib.base.base.BaseActivity
import com.example.mainmodule.main.MainActivity
import com.example.zs91_android.R
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by zj on 2019/4/17.
 * is use for:
 */
class SplashActivity:BaseActivity(){
    private var alphaAnimation: AlphaAnimation?=null
    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
    }

    override fun initView() {
        //渐变展示启动屏
        alphaAnimation= AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })

        checkUserPermission()
    }

    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkUserPermission(){
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "应用需要以下权限，否则可能影响正常使用，请允许", 0, *perms)

    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        ivSplash.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

    override fun startRequest() {
    }
}