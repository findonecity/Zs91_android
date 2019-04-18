package com.example.mainmodule.bean

import com.flyco.tablayout.listener.CustomTabEntity



/**
 * Created by xuhao on 2017/11/8.
 */
class TabEntityBean(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}