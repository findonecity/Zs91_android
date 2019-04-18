package com.example.mainmodule.home;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mainmodule.R;
import com.example.mainmodule.bean.HomeMessageBean;

import java.util.List;

/**
 * Created by zj on 2019/4/4.
 * is use for:
 */
public class HomeNonStopAdapter extends BaseMultiItemQuickAdapter<HomeMessageBean.YjzdListBean, BaseViewHolder> {
    public HomeNonStopAdapter(List<HomeMessageBean.YjzdListBean> data) {
        super(data);
        addItemType(HomeMessageBean.YjzdListBean.GREEN, R.layout.item_rv_home_nonstop1);
        addItemType(HomeMessageBean.YjzdListBean.RED, R.layout.item_rv_home_nonstop2);
        addItemType(HomeMessageBean.YjzdListBean.BLUE, R.layout.item_rv_home_nonstop3);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMessageBean.YjzdListBean bean) {
        switch (helper.getItemViewType()){
            case HomeMessageBean.YjzdListBean.GREEN:
                helper.setText(R.id.tvText,bean.getKeywords());
                break;
            case HomeMessageBean.YjzdListBean.RED:
                helper.setText(R.id.tvText,bean.getKeywords());
                break;
            case HomeMessageBean.YjzdListBean.BLUE:
                helper.setText(R.id.tvText,bean.getKeywords());
                break;
        }
    }
}
