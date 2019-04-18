package com.example.mainmodule.home.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.mainmodule.R

/**
 * Created by zj on 2019/4/8.
 * is use for:
 */
class SearchHistoryAdapter (data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(
    R.layout.item_rv_search_history, data) {
    override fun convert(helper: BaseViewHolder, text: String) {
        helper.setText(R.id.tvTitle,text)
    }
}