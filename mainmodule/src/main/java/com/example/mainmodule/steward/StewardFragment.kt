package com.example.mainmodule.steward

import android.content.Intent
import android.os.Bundle
import com.example.baselib.base.base.BaseFragment
import com.example.mainmodule.R
import kotlinx.android.synthetic.main.fragment_steward.*

/**
 * Created by zj on 2019/4/9.
 * is use for:  管家
 */
class StewardFragment : BaseFragment(), StewardContract.View {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): StewardFragment {
            val fragment = StewardFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_steward

    override fun initView() {
        tvLogin.setOnClickListener {
            val intent = Intent()
            intent.setClassName(activity, "com.example.loginlib.login.LoginActivity")
            activity?.startActivity(intent)
        }
    }

    override fun lazyLoad() {
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }
}