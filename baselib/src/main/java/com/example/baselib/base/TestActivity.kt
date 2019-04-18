package com.example.baselib.base

import com.example.baselib.R
import com.example.baselib.base.base.BaseActivity

/**
 * Created by zj on 2019/4/17.
 * is use for:
 */
class TestActivity:BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_test
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun startRequest() {
    }
}