package com.example.mainmodule.home

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.baselib.base.base.BaseFragment
import com.example.baselib.base.net.Constant.BASE_WEB_URl
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_1
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_10
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_2
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_3
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_4
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_5
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_6
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_7
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_8
import com.example.baselib.base.net.Constant.FUNCTION_TITLE_9
import com.example.baselib.base.net.Constant.FUNCTION_URl_1
import com.example.baselib.base.net.Constant.FUNCTION_URl_10
import com.example.baselib.base.net.Constant.FUNCTION_URl_2
import com.example.baselib.base.net.Constant.FUNCTION_URl_3
import com.example.baselib.base.net.Constant.FUNCTION_URl_5
import com.example.baselib.base.net.Constant.FUNCTION_URl_6
import com.example.baselib.base.net.Constant.FUNCTION_URl_7
import com.example.baselib.base.net.Constant.FUNCTION_URl_8
import com.example.baselib.base.net.Constant.FUNCTION_URl_9
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_1
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_10
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_2
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_3
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_5
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_6
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_7
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_8
import com.example.baselib.base.net.Constant.FUNCTION_URl_TITLE_9
import com.example.baselib.base.net.Constant.HELP_TITLE_1
import com.example.baselib.base.net.Constant.HELP_URL_1
import com.example.baselib.base.net.Constant.TOOL_BOTTOM_TITLE_2
import com.example.baselib.base.net.Constant.TOOL_BOTTOM_TITLE_3
import com.example.baselib.base.net.Constant.TOOL_BOTTOM_URL_2
import com.example.baselib.base.net.Constant.TOOL_BOTTOM_URL_3
import com.example.baselib.base.net.exception.ErrorStatus
import com.example.mainmodule.R
import com.example.mainmodule.bean.HomeFunctionBean
import com.example.mainmodule.bean.HomeMessageBean
import com.example.mainmodule.home.search.SearchActivity
import com.example.mainmodule.web.JavaWebActivity
import com.vondear.rxtool.RxBarTool
import com.vondear.rxtool.RxTextTool
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by zj on 2019/4/1.
 * is use for: 首页
 */
class HomeFragment:BaseFragment(), HomeContract.View {
    override fun setHomeMessage(homeMessageBean: HomeMessageBean) {
        mNonStopList.clear()
        mNonStopList.addAll(homeMessageBean.yjzdList)

        for (i in 0 until mNonStopList.size){
            when {
                i%3 == 0 -> mNonStopList[i].itemType = 1
                i%3 == 1 -> mNonStopList[i].itemType = 2
                else -> mNonStopList[i].itemType = 3
            }
        }

        mNonStopAdapter?.notifyDataSetChanged()

        mAdvertisingList.clear()
        mAdvertisingList.addAll(homeMessageBean.midadlist)

        mAdvertisingAdapter?.notifyDataSetChanged()

        mBannerList.clear()
        mBannerList.addAll(homeMessageBean.topadlist)

        banner?.setBannerData(mBannerList)
        banner?.setAutoPlayAble(mBannerList.size > 1)

        //加载广告图片
        banner?.loadImage { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            Glide.with(this.context!!).load(mBannerList[position].picaddress).into(view as ImageView)
        }

        RxTextTool.getBuilder("").setAlign(Layout.Alignment.ALIGN_CENTER)
            .append("这里有 ").append(homeMessageBean.pcount).setProportion(1.3F).setBold().append(" 条商机").into(tvBusiness)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    private val mPresenter by lazy { HomePresenter() }

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun startLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun finishLoading() {
        mLayoutStatusView?.showContent()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    private var mFunctionList:MutableList<HomeFunctionBean> = ArrayList()
    private var mFunctionAdapter: HomeFunctionAdapter? = null
    private var mSortList:MutableList<HomeFunctionBean> = ArrayList()
    private var mSortAdapter: HomeSortAdapter? = null
    private var mAdvertisingAdapter: HomeAdvertisingAdapter? = null
    private var mAdvertisingList:MutableList<HomeMessageBean.MidadlistBean> = ArrayList()
    private var mNonStopList:MutableList<HomeMessageBean.YjzdListBean> = ArrayList()
    private var mNonStopAdapter: HomeNonStopAdapter? = null
    private var mToolList:MutableList<HomeFunctionBean> = ArrayList()
    private var mToolAdapter: HomeToolAdapter? = null
    private var mBannerList:MutableList<HomeMessageBean.TopadlistBean> = ArrayList()
    override fun initView() {
        mPresenter.attachView(this)

        mLayoutStatusView = multipleStatusView

        RxBarTool.setTransparentStatusBar(activity)//状态栏透明化
        RxBarTool.StatusBarLightMode(activity)

        mFunctionList.clear()
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_1, R.mipmap.function_icon_1))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_2, R.mipmap.function_icon_2))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_3, R.mipmap.function_icon_3))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_4, R.mipmap.function_icon_4))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_5, R.mipmap.function_icon_5))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_6, R.mipmap.function_icon_6))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_7, R.mipmap.function_icon_7))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_8, R.mipmap.function_icon_8))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_9, R.mipmap.function_icon_9))
        mFunctionList.add(HomeFunctionBean(FUNCTION_TITLE_10, R.mipmap.function_icon_10))

        mFunctionAdapter = HomeFunctionAdapter(mFunctionList)
        rvFunction?.layoutManager = GridLayoutManager(context, 5)
        mFunctionAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvFunction?.adapter = mFunctionAdapter

        mFunctionAdapter?.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            when(mFunctionList[position].title){
                FUNCTION_TITLE_1 ->{
                    bundle.putString("webPath",FUNCTION_URl_1)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_1)
                }
                FUNCTION_TITLE_2 ->{
                    bundle.putString("webPath",FUNCTION_URl_2)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_2)
                }
                FUNCTION_TITLE_3 ->{
                    bundle.putString("webPath",FUNCTION_URl_3)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_3)
                }
                FUNCTION_TITLE_5 ->{
                    bundle.putString("webPath",FUNCTION_URl_5)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_5)
                }
                FUNCTION_TITLE_6 ->{
                    bundle.putString("webPath",FUNCTION_URl_6)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_6)
                }
                FUNCTION_TITLE_7 ->{
                    bundle.putString("webPath",FUNCTION_URl_7)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_7)
                }
                FUNCTION_TITLE_8 ->{
                    bundle.putString("webPath",FUNCTION_URl_8)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_8)
                }
                FUNCTION_TITLE_9 ->{
                    bundle.putString("webPath",FUNCTION_URl_9)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_9)
                }
                FUNCTION_TITLE_10 ->{
                    bundle.putString("webPath",FUNCTION_URl_10)
                    bundle.putString("webTitle",FUNCTION_URl_TITLE_10)
                }

            }
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        mSortList.clear()
        mSortList.add(HomeFunctionBean("废金属", R.mipmap.sort_icon_1))
        mSortList.add(HomeFunctionBean("废塑料", R.mipmap.sort_icon_2))
        mSortList.add(HomeFunctionBean("废纸", R.mipmap.sort_icon_3))
        mSortList.add(HomeFunctionBean("废纺织", R.mipmap.sort_icon_4))
        mSortList.add(HomeFunctionBean("二手设备", R.mipmap.sort_icon_5))
        mSortList.add(HomeFunctionBean("废电子电器", R.mipmap.sort_icon_6))
        mSortList.add(HomeFunctionBean("橡胶轮胎", R.mipmap.sort_icon_7))
        mSortList.add(HomeFunctionBean("全部分类", R.mipmap.sort_icon_8))

        mSortAdapter = HomeSortAdapter(mSortList)
        val ms1 = LinearLayoutManager(context)
        ms1.orientation = LinearLayoutManager.HORIZONTAL
        rvSort.layoutManager = ms1
        mSortAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvSort?.adapter = mSortAdapter

        /**
         * 中间广告内容
         */
        mAdvertisingAdapter = HomeAdvertisingAdapter(mAdvertisingList)
        rvAdvertising?.layoutManager = LinearLayoutManager(context)
        mAdvertisingAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvAdvertising?.adapter = mAdvertisingAdapter

        mAdvertisingAdapter?.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            bundle.putString("webPath",mAdvertisingList[position].ad_target_url)
            bundle.putString("webTitle",mAdvertisingList[position].ad_title)
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        mNonStopAdapter = HomeNonStopAdapter(mNonStopList)
        rvNonstop?.layoutManager = GridLayoutManager(context, 5)
        mNonStopAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvNonstop?.adapter = mNonStopAdapter

        mNonStopAdapter?.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            bundle.putString("webPath", BASE_WEB_URl + mNonStopList[position].companyId + ".html")
            bundle.putString("webTitle","公司详情")
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        mToolList.clear()
        mToolList.add(HomeFunctionBean("诚信档案", R.mipmap.tool_icon_1))
        mToolList.add(HomeFunctionBean("我的收藏", R.mipmap.tool_icon_2))
        mToolList.add(HomeFunctionBean("我的名片", R.mipmap.tool_icon_3))
        mToolList.add(HomeFunctionBean("再生商城", R.mipmap.tool_icon_4))
        mToolList.add(HomeFunctionBean("我的访客", R.mipmap.tool_icon_5))
        mToolList.add(HomeFunctionBean("免费发布", R.mipmap.tool_icon_6))
        mToolList.add(HomeFunctionBean("通讯录", R.mipmap.tool_icon_7))
        mToolList.add(HomeFunctionBean("商机订阅", R.mipmap.tool_icon_8))
        mToolList.add(HomeFunctionBean("我的留言", R.mipmap.tool_icon_9))
        mToolList.add(HomeFunctionBean("再生钱包", R.mipmap.tool_icon_10))

        mToolAdapter = HomeToolAdapter(mToolList)
        val ms = LinearLayoutManager(context)
        ms.orientation = LinearLayoutManager.HORIZONTAL
        rvTool.layoutManager = ms
        mToolAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvTool?.adapter = mToolAdapter

        //顶部轮播
        banner?.setOnItemClickListener { banner, model, view, position ->
            val bundle = Bundle()
            bundle.putString("webPath",mBannerList[position].ad_target_url)
            bundle.putString("webTitle",mBannerList[position].ad_title)
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        //搜索
        tvSearch.setOnClickListener {
            openSearchActivity()
        }

        /*
         * 推广营销
         */
//        tvMembershipService.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("webPath",GENERALIZE_URL_1)
//            bundle.putString("webTitle",GENERALIZE_TITLE_1)
//            beginActivity(JavaWebActivity::class.java,bundle,-1000)
//        }
//
//        tvBrandAdvertising.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("webPath",GENERALIZE_URL_2)
//            bundle.putString("webTitle",GENERALIZE_TITLE_2)
//            beginActivity(JavaWebActivity::class.java,bundle,-1000)
//        }
//
//        tvBaiDuSeo.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("webPath",GENERALIZE_URL_3)
//            bundle.putString("webTitle",GENERALIZE_TITLE_3)
//            beginActivity(JavaWebActivity::class.java,bundle,-1000)
//        }
//
//        tvEnterpriseSite.setOnClickListener {
//            Toast.makeText(activity,"企业建站",Toast.LENGTH_LONG).show()
//        }
//
//        llExcellent.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("webPath",GENERALIZE_URL_5)
//            bundle.putString("webTitle",GENERALIZE_TITLE_5)
//            beginActivity(JavaWebActivity::class.java,bundle,-1000)
//        }
//
//        llStick.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("webPath",GENERALIZE_URL_6)
//            bundle.putString("webTitle",GENERALIZE_TITLE_6)
//            beginActivity(JavaWebActivity::class.java,bundle,-1000)
//        }
//
//        llRefresh.setOnClickListener {
//            Toast.makeText(activity,"自动刷新",Toast.LENGTH_LONG).show()
//        }
//
//        tvContact.setOnClickListener {
//            Toast.makeText(activity,"显示联系",Toast.LENGTH_LONG).show()
//        }

        /**
         * 幸运抽奖
         */
        rlGoodLuck.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("webPath",TOOL_BOTTOM_URL_2)
            bundle.putString("webTitle",TOOL_BOTTOM_TITLE_2)
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        /**
         * 超值套餐
         */
        rlCombo.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("webPath",TOOL_BOTTOM_URL_3)
            bundle.putString("webTitle",TOOL_BOTTOM_TITLE_3)
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        /**
         * 服务中心
         */
        tvServiceCenter.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("webPath",HELP_URL_1)
            bundle.putString("webTitle",HELP_TITLE_1)
            beginActivity(JavaWebActivity::class.java,bundle,-1000)
        }

        tvRegister.setOnClickListener {
//            val bundle = Bundle()
//            beginActivity(com.example.loginlib.register.RegisterActivity::class.java,bundle,-1000)
        }
    }

    private fun openSearchActivity() {
        startActivity(Intent(activity, SearchActivity::class.java))
    }

    override fun lazyLoad() {
        mPresenter.getHomeMessage()
    }

    override fun onResume() {
        super.onResume()
        banner?.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner?.stopAutoPlay()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}