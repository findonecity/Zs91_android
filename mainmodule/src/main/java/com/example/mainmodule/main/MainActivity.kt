package com.example.mainmodule.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.baselib.base.base.BaseActivity
import com.example.baselib.base.utils.SharedPreferencesManager
import com.example.mainmodule.R
import com.example.mainmodule.bean.TabEntityBean
import com.example.mainmodule.find.FindFragment
import com.example.mainmodule.home.HomeFragment
import com.example.mainmodule.message.MessageFragment
import com.example.mainmodule.steward.StewardFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.vondear.rxtool.RxDeviceTool
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(){
    private val mTitles = arrayOf("首页", "发现", "消息", "管家")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_launcher, R.mipmap.tab_find_n, R.mipmap.tab_message_n, R.mipmap.tab_steward_n)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_home_y, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mFindFragment: FindFragment? = null
    private var mMessageFragment: MessageFragment? = null
    private var mStewardFragment: StewardFragment? = null

    //默认为0
    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tabLayout.currentTab = mIndex
        switchFragment(mIndex)

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if (tabLayout != null) {
            outState?.putInt("currTabIndex", mIndex)
        }
    }

    override fun layoutId(): Int = R.layout.activity_main

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) {
                TabEntityBean(
                    mTitles[it],
                    mIconSelectIds[it],
                    mIconUnSelectIds[it]
                )
            }
        //为Tab赋值
        tabLayout.setTabData(mTabEntities)
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // tab_hoem_y
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.flContainer, it, "home")
            }
            1  //tab_find_n
            -> mFindFragment?.let {
                transaction.show(it)
            } ?: FindFragment.getInstance(mTitles[position]).let {
                mFindFragment = it
                transaction.add(R.id.flContainer, it, "find") }
            2  //热门
            -> mMessageFragment?.let {
                transaction.show(it)
            } ?: MessageFragment.getInstance(mTitles[position]).let {
                mMessageFragment = it
                transaction.add(R.id.flContainer, it, "message") }
            3 //我的
            -> mStewardFragment?.let {
                transaction.show(it)
            } ?: StewardFragment.getInstance(mTitles[position]).let {
                mStewardFragment = it
                transaction.add(R.id.flContainer, it, "steward") }

            else -> {

            }
        }

        mIndex = position
        tabLayout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mFindFragment?.let { transaction.hide(it) }
        mMessageFragment?.let { transaction.hide(it) }
        mStewardFragment?.let { transaction.hide(it) }
    }


    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
//        showToast("onSaveInstanceState->"+mIndex)
//        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tabLayout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initData() {
    }

    private var clientId:String = ""
    override fun initView() {
        clientId = RxDeviceTool.getIMEI(this)
        SharedPreferencesManager.getInstance().putData(this,"deviceInfo","clientId",clientId)
    }


    override fun startRequest() {
    }

    //双击退出登录
    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) run {
                Toast.makeText(this, "再按一次退出程序！",
                    Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
