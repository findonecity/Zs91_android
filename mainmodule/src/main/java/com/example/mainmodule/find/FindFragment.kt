package com.example.mainmodule.find

import android.os.Bundle
import com.example.baselib.base.base.BaseFragment
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/10.
 * is use for: 发现
 */
class FindFragment :BaseFragment(), FindContract.View {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): FindFragment {
            val fragment = FindFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_find

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }
}