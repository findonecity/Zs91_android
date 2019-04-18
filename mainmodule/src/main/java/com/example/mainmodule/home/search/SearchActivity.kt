package com.example.mainmodule.home.search

import android.annotation.TargetApi
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.baselib.base.base.BaseActivity
import com.example.mainmodule.bean.CorrelationBean
import com.example.baselib.base.utils.ViewAnimUtils
import com.example.baselib.base.widget.CustomPopupWindow
import com.example.mainmodule.R
import com.google.android.flexbox.*
import com.vondear.rxtool.RxBarTool
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by zj on 2019/4/8.
 * is use for: 搜索功能
 */
class SearchActivity:BaseActivity(), SearchContract.View {
    override fun setCorrelationList(list: ArrayList<CorrelationBean>) {
        mCorrelationList.clear()
        mCorrelationList.addAll(list)
        if (mCorrelationList.size == 0){
            mPop?.dismiss()
        }

        mCorrelationAdapter?.notifyDataSetChanged()

        mPop?.showAsDropDown(etSearch, 0, 0) //显示搜索联想列表的pop
    }

    private val mPresenter by lazy { SearchPresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int = R.layout.activity_search

    override fun initData() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setUpEnterAnimation() // 入场动画
//            setUpExitAnimation() // 退场动画
//        } else {
//            setUpView()
//        }
        setUpEnterAnimation() // 入场动画
        setUpExitAnimation() // 退场动画
        setUpView()
    }

    /**
     * 退场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation() {
        val transition = TransitionInflater.from(this)
            .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    private fun setUpView() {
        val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        animation.duration = 300
        relContainer.startAnimation(animation)
        relContainer.visibility = View.VISIBLE
    }

    /**
     * 展示动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animateRevealShow() {
        ViewAnimUtils.animateRevealShow(
            this, relFrame,
            fabCircle.width / 2, R.color.default_bg,
            object : ViewAnimUtils.OnRevealAnimationListener {
                override fun onRevealHide() {

                }

                override fun onRevealShow() {
                    setUpView()
                }
            })
    }

    private var mHistoryAdapter: SearchHistoryAdapter? = null
    private var mHistoryList:MutableList<String> = ArrayList()
    private var mPop: CustomPopupWindow? = null //显示搜索联想的pop
    private var rvSearch: RecyclerView? = null
    private var mCorrelationAdapter: SearchCorrelationAdapter? = null
    private var mCorrelationList:MutableList<CorrelationBean> = ArrayList()
    override fun initView() {
        tvCancel.setOnClickListener {
            onBackPressed()
        }

        RxBarTool.setTransparentStatusBar(this)//状态栏透明化
        RxBarTool.StatusBarLightMode(this)

        mHistoryList.clear()
        mHistoryList.add("历史关键词1")
        mHistoryList.add("历史2")
        mHistoryList.add("关键词3")
        mHistoryList.add("历史搜索4")
        mHistoryList.add("历史关键词1")
        mHistoryList.add("历史2")
        mHistoryList.add("关键词3")
        mHistoryList.add("历史搜索4")
        mHistoryList.add("历史关键词1")
        mHistoryList.add("历史2")
        mHistoryList.add("关键词3")
        mHistoryList.add("历史搜索4")
        mHistoryAdapter = SearchHistoryAdapter(mHistoryList)

        val flexBoxLayoutManager = FlexboxLayoutManager(this)
        flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      //按正常方向换行
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW   //主轴为水平方向，起点在左端
        flexBoxLayoutManager.alignItems = AlignItems.CENTER    //定义项目在副轴轴上如何对齐
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START  //多个轴对齐方式

        rvHistory.layoutManager = flexBoxLayoutManager
        rvHistory.adapter = mHistoryAdapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    mPop?.dismiss()
                } else {
                    //输入内容非空的时候才开始搜索
                    mPresenter.getCorrelationList(s.toString())
                }
            }
        })

        mPop = CustomPopupWindow.Builder(this)
            .setContentView(R.layout.pop_search)
            .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
            .setheight(LinearLayout.LayoutParams.WRAP_CONTENT)
            .setBackgroundAlpha(1f)
            .build()
        rvSearch = mPop?.getItemView(R.id.rvSearch) as RecyclerView?

        mCorrelationAdapter = SearchCorrelationAdapter(mCorrelationList)
        rvSearch?.layoutManager = LinearLayoutManager(this)
        mCorrelationAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        rvSearch?.adapter = mCorrelationAdapter
    }

    override fun startRequest() {
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }

    // 返回事件
    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimUtils.animateRevealHide(
                this, relFrame,
                fabCircle.width / 2, R.color.default_bg,
                object : ViewAnimUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {
                        defaultBackPressed()
                    }

                    override fun onRevealShow() {

                    }
                })
        } else {
            defaultBackPressed()
        }
    }

    // 默认回退
    private fun defaultBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}