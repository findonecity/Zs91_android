package com.example.loginlib.register

import android.app.AlertDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.baselib.base.base.BaseActivity
import com.example.baselib.base.base.Constant
import com.example.baselib.base.net.Constant.RESULT_TRUE
import com.example.baselib.base.utils.SharedPreferencesManager
import com.example.loginlib.R
import com.example.loginlib.lifeBean.RegisterBean
import com.example.loginlib.lifeBean.BusinessBean
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.top_toolbar.*

/**
 * Created by zj on 2019/4/10.
 * is use for: 注册界面
 */
class RegisterActivity: BaseActivity(), RegisterContract.View {
    override fun setRegisterResult(bean: RegisterBean) {
        if (bean.err == RESULT_TRUE){
            showHintDialog(bean.errkey, Constant.HINT_FAIL)
        }else{
            showHintDialog("注册成功", Constant.HINT_SUCCESS)
            SharedPreferencesManager.getInstance().putData(this,"userInfo","companyId",bean.company_id.toString())
        }
    }

    override fun startLoading() {
    }

    override fun finishLoading() {
    }

    private val mPresenter by lazy { RegisterPresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun layoutId(): Int = R.layout.activity_register

    override fun initData() {

    }

    private val mBusinessList:MutableList<BusinessBean> = ArrayList()
    private var rvBusiness:RecyclerView? = null
    private var mBusinessAdapter: BusinessAdapter? = null
    private var mSelectBusiness:String = "请选择主营行业"
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {

        tvToolbarTitle.text = "用户注册"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        clientId = SharedPreferencesManager.getInstance().getData(this,"deviceInfo","clientId")
        mBusinessList.clear()
        mBusinessList.add(BusinessBean("废塑料", "10001000"))
        mBusinessList.add(BusinessBean("废金属", "10001001"))
        mBusinessList.add(BusinessBean("废纸", "10001002"))
        mBusinessList.add(BusinessBean("废旧轮胎与废橡胶", "10001003"))
        mBusinessList.add(BusinessBean("废纺织品与废皮革", "10001004"))
        mBusinessList.add(BusinessBean("废电子电器", "10001005"))
        mBusinessList.add(BusinessBean("废玻璃", "10001006"))
        mBusinessList.add(BusinessBean("废旧二手设备", "10001007"))
        mBusinessList.add(BusinessBean("其他废料", "10001008"))
        mBusinessList.add(BusinessBean("服务", "10001009"))
        mBusinessList.add(BusinessBean("塑料原料", "10001010"))

        llBusiness.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setView(R.layout.dialog_business)
                .create()
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()

            rvBusiness = dialog.findViewById(R.id.rvBusiness)

            mBusinessAdapter = BusinessAdapter(mBusinessList)
            rvBusiness?.layoutManager = LinearLayoutManager(this)
            mBusinessAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
            rvBusiness?.adapter = mBusinessAdapter

            mBusinessAdapter?.setOnItemClickListener { adapter, view, position ->
                mSelectBusiness = mBusinessList[position].name
                tvBusiness.text = mSelectBusiness

                dialog.cancel()
            }
        }
    }

    private var userName:String = ""
    private var password:String = ""
    private var contract:String = ""
    private var companyName:String = ""
    private var clientId:String = ""
    override fun startRequest() {
        btnDredge.setOnClickListener {
            userName = etPhone.text.toString().trim()
            if (userName.isEmpty()){
                showHintDialog("请输入手机号", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            if (!userName.matches("1[3|4|5|7|8][0-9]{9}$".toRegex())){
                showHintDialog("请输入正确的手机号", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            password = etPassword.text.toString().trim()
            if (password.length < 6){
                showHintDialog("密码必须为6-8位", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            contract = etContract.text.toString().trim()
            if (contract.isEmpty()){
                showHintDialog("请填写联系人", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            if (mSelectBusiness == "请选择主营行业"){
                showHintDialog("请选择主营行业", Constant.HINT_FAIL)
                return@setOnClickListener
            }
            companyName = etCompanyName.text.toString().trim()

            mPresenter.register(userName,password,contract,mSelectBusiness,companyName,clientId)
        }
    }
}