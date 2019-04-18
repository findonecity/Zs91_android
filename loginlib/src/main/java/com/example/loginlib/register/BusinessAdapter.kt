package com.example.loginlib.register

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.loginlib.R
import com.example.loginlib.lifeBean.BusinessBean

/**
 * Created by zj on 2019/4/11.
 * is use for: 主营业务
 */
class BusinessAdapter (data: List<BusinessBean>) : BaseQuickAdapter<BusinessBean, BaseViewHolder>(
    R.layout.item_rv_bussiness, data) {
    override fun convert(helper: BaseViewHolder, bean: BusinessBean) {
        helper.setText(R.id.tvText,bean.name)
    }
}