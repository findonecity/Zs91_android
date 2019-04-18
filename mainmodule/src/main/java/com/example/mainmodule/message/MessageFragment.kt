package com.example.mainmodule.message

import android.os.Bundle
import com.example.baselib.base.base.BaseFragment
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/10.
 * is use for:
 */
class MessageFragment :BaseFragment(), MessageContract.View {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MessageFragment {
            val fragment = MessageFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_message

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }
}