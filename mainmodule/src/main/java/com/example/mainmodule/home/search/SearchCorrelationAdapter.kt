package com.example.mainmodule.home.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.R
import com.example.mainmodule.bean.CorrelationBean

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
class SearchCorrelationAdapter (data: List<CorrelationBean>) : BaseQuickAdapter<CorrelationBean, BaseViewHolder>(
    R.layout.item_rv_search_correlation, data) {
    override fun convert(helper: BaseViewHolder, bean: CorrelationBean) {
        helper.setText(R.id.tvText,bean.keywords)
    }
}