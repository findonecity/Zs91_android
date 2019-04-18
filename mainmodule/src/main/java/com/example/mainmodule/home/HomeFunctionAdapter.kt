package com.example.mainmodule.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.bean.HomeFunctionBean
import com.example.baselib.base.utils.glide.GlideImageUtil
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/3.
 * is use for: 首页功能按钮适配器
 */
class HomeFunctionAdapter(data: List<HomeFunctionBean>) : BaseQuickAdapter<HomeFunctionBean, BaseViewHolder>(R.layout.item_rv_home_function, data) {
    override fun convert(helper: BaseViewHolder, bean: HomeFunctionBean) {
        helper.setText(R.id.tvTitle,bean.title)
        GlideImageUtil.loadResourceImage(mContext,bean.mipmap,helper.getView(R.id.ivTitle))
    }
}