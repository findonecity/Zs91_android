package com.example.mainmodule.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.bean.MarketingBean
import com.example.baselib.base.utils.glide.GlideImageUtil
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/4.
 * is use for:
 */
class HomeMarketing2Adapter (data: List<MarketingBean>) : BaseQuickAdapter<MarketingBean, BaseViewHolder>(R.layout.item_rv_home_marketing2, data) {
    override fun convert(helper: BaseViewHolder, bean: MarketingBean) {
        helper.setText(R.id.tvTitle,bean.title)
            .setText(R.id.tvMessage,bean.message)
        GlideImageUtil.loadResourceImage(mContext,bean.mipmap,helper.getView(R.id.ivTitle))
    }
}