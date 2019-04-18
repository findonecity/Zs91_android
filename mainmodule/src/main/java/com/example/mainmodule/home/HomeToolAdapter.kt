package com.example.mainmodule.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.bean.HomeFunctionBean
import com.example.baselib.base.utils.glide.GlideImageUtil
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/4.
 * is use for:
 */
class HomeToolAdapter (data: List<HomeFunctionBean>) : BaseQuickAdapter<HomeFunctionBean, BaseViewHolder>(R.layout.item_rv_home_tool, data) {
    override fun convert(helper: BaseViewHolder, bean: HomeFunctionBean) {
        helper.setText(R.id.tvTitle,bean.title)
        GlideImageUtil.loadResourceImage(mContext,bean.mipmap,helper.getView(R.id.ivTitle))
    }
}