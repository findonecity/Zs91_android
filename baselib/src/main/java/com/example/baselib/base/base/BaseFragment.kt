package com.example.baselib.base.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.example.baselib.base.progress.ProgressDialogHandler

/**
 * Created by zj on 2019/3/25.
 * is use for: fragment基类
 */
abstract class BaseFragment: Fragment() {
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null
    /**
     * 点击请求时的加载框
     */
    var loadDialog: ProgressDialogHandler? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }



    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView()

        loadDialog = ProgressDialogHandler(this.activity, null, true)

        lazyLoadDataIfPrepared()
        //多种状态切换的view 重试点击事件
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }


    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    /**
     * 单事件提交时，展示dialog，无空白页等界面
     */
    fun showLoading() {
        loadDialog?.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)?.sendToTarget()
    }

    /**
     * 关闭loading提示框
     */
    fun closeLoading() {
        loadDialog?.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)?.sendToTarget()
        loadDialog = null
    }

    fun beginActivity(goToClass: Class<*>, bundle: Bundle, requestCode: Int) {
        val intent = Intent(activity, goToClass)
        intent.putExtras(bundle)
        if (requestCode == -1000) {
            startActivity(intent)
        } else {
            startActivityForResult(intent, requestCode)
        }
    }
}