package com.example.mainmodule.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.bean.HomeMessageBean
import com.example.baselib.base.utils.glide.GlideImageUtil
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/3.
 * is use for:
 */
class HomeAdvertisingAdapter (data: List<HomeMessageBean.MidadlistBean>) : BaseQuickAdapter<HomeMessageBean.MidadlistBean, BaseViewHolder>(R.layout.item_rv_home_advertising, data) {
    override fun convert(helper: BaseViewHolder, bean: HomeMessageBean.MidadlistBean) {
        GlideImageUtil.loadWebImage(mContext,bean.picaddress,helper.getView(R.id.ivTitle))
    }
}