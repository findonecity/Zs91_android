package com.example.baselib.base.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.classic.common.MultipleStatusView
import com.example.baselib.R
import com.example.baselib.base.base.Constant.HINT_FAIL
import com.example.baselib.base.base.Constant.HINT_SUCCESS
import com.example.baselib.base.progress.ProgressDialogHandler
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import io.reactivex.annotations.NonNull
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by zj on 2019/3/22.
 * is use for: activity基类
 */
abstract class BaseActivity :AppCompatActivity(),EasyPermissions.PermissionCallbacks{
    protected var mLayoutStatusView: MultipleStatusView? = null
    var loadDialog: ProgressDialogHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        loadDialog = ProgressDialogHandler(this, null, true)

        initData()
        initView()

        startRequest()
        initRetryListener()

        val window = window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            //设置状态栏颜色
            window.statusBarColor = resources.getColor(R.color.white)
        }
    }

    //错误布局重新加载数据
    private fun initRetryListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        startRequest()
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int
    /**
     * 初始化数据
     */
    abstract fun initData()
    /**
     * 初始化 View
     */
    abstract fun initView()
    /**
     * 请求数据
     */
    abstract fun startRequest()

    /**
     * 导航栏 自定义是否添加
     */

    /**
     * 单事件提交时，展示dialog，无空白页等界面
     */
    fun showLoading() {
        if (loadDialog == null){
            loadDialog = ProgressDialogHandler(this, null, true)
        }
        loadDialog?.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)?.sendToTarget()
    }

    /**
     * 关闭loading提示框
     */
    fun closeLoading() {
        loadDialog?.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)?.sendToTarget()
        loadDialog = null
    }

    var tipDialog: QMUITipDialog? = null
    fun showHintDialog(message:String, messageType:Int){
        try {
            when(messageType){
                HINT_SUCCESS ->{
                    tipDialog = QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord(message)
                        .create()

                    tipDialog?.setCanceledOnTouchOutside(true)
                    tipDialog?.show()

                    Handler().postDelayed({
                        tipDialog?.cancel()
                    }, 1000)
                }

                HINT_FAIL ->{
                    tipDialog = QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                        .setTipWord(message)
                        .create()

                    tipDialog?.setCanceledOnTouchOutside(true)
                    tipDialog?.show()
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun beginActivity(goToClass: Class<*>, bundle: Bundle, requestCode: Int) {
        val intent = Intent(this, goToClass)
        intent.putExtras(bundle)
        if (requestCode == -1000) {
            startActivity(intent)
        } else {
            startActivityForResult(intent, requestCode)
        }
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.i("EasyPermissions", "获取成功的权限$perms")
    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }
    }
}