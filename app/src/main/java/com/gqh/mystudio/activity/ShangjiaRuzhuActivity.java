package com.gqh.mystudio.activity;


import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class ShangjiaRuzhuActivity extends BaseHttpActivity {

    PopupWindow popuWindow;
    private View view;

    @ViewInject(R.id.image_fanhui)
    private ImageView iv_back;
    @ViewInject(R.id.tv_leixing)
    private TextView tv_leixing;
    @ViewInject(R.id.et_mingcheng)
    private EditText et_mingcheng;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_email)
    private EditText et_email;
    @ViewInject(R.id.et_detail)
    private EditText et_detail;
    @ViewInject(R.id.bt_commit)
    private Button bt_commit;
    private Button bt_meishi;
    private Button bt_dianying;
    private Button bt_tuangou;
    private Button bt_waimai;
    private Button bt_lvxing;
    private Button bt_liren;
    private Button bt_gouwu;
    private Button bt_chuxing;
    private Button bt_fuwu;
    private String mingcheng;
    private String name;
    private String phone;
    private String email;
    private String detail;

    @OnClick({R.id.image_fanhui,
            R.id.tv_leixing,
            R.id.bt_commit})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;

                case R.id.bt_commit:
                    String leixing = tv_leixing.getText().toString();
                    mingcheng=et_mingcheng.getText().toString();
                    name=et_name.getText().toString();
                    phone=et_phone.getText().toString();
                    email=et_email.getText().toString();
                    detail=et_detail.getText().toString();

                    //非空验证
                    if(StringUtil.isEmpty(mingcheng)){
                        showToast("请输入单位名称");
                        break;
                    }
                    if(StringUtil.isEmpty(name)){
                        showToast("请输入姓名");
                        break;
                    }
                    if(StringUtil.isEmpty(phone)){
                        showToast("请输入联系电话");
                        break;
                    }
                    if(StringUtil.isEmpty(email)){
                        showToast("请输入邮箱");
                        break;
                    }
                    if(StringUtil.isEmpty(detail)){
                        showToast("请输入具体事项");
                        break;
                    }

                    String subType = null;
                    if (leixing.equals("美食")){
                        subType="07_005_01";
                    }else if (leixing.equals("电影")){
                        subType="07_005_02";
                    }else if (leixing.equals("团购")){
                        subType="07_005_03";
                    }else if (leixing.equals("外卖")){
                        subType="07_005_04";
                    }else if (leixing.equals("旅行")){
                        subType="07_005_05";
                    }else if (leixing.equals("丽人")){
                        subType="07_005_06";
                    }else if (leixing.equals("购物")){
                        subType="07_005_07";
                    }else if (leixing.equals("出行")){
                        subType="07_005_08";
                    }else if (leixing.equals("生活服务")){
                        subType="07_005_09";
                    }
                    //发送提交请求
                    RequestParams params=new RequestParams();
                    params.put("SubType",subType);
                    params.put("Name",mingcheng);
                    params.put("Contact",name);
                    params.put("ContactPhone",phone);
                    params.put("Email",email);
                    params.put("SupDesc",detail);
                    postDialog(Constant.SUPPLIER_NEW,params);
                    break;
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {

            JSONObject object=new JSONObject(jsonStr);
            if (object.getString("code").equals("0")){
                showToast("提交成功，稍后客户联系您");
                finish();
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shangjia_ruzhu;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        tv_leixing.setOnClickListener(new PopuOnClickListener());
        initPopupWindow();
    }



    /**
     * 这个类主要显示PopuWindow，并显示之后对里面的按钮添加监听事件。
     */
    private class PopuOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_leixing:
                    // 加上下面两行可以用back键关闭popupwindow，否则必须调用dismiss();
                    // 需要顺利让PopUpWindow dimiss；PopUpWindow的背景不能为空。
                    // 当有popuWindow.setFocusable(false);的时候，说明PopuWindow不能获得焦点，并不能点击外面消失，只能由dismiss()消失。
                    // 当设置为popuWindow.setFocusable(true);的时候，加上下面两行代码才会消失
                    // 注意这里添加背景并不会覆盖原来的背景。
                    ColorDrawable cd = new ColorDrawable();
                    popuWindow.setBackgroundDrawable(cd);
                    popuWindow.showAsDropDown(v);
                    bt_meishi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("美食");
                        }
                    });

                    bt_dianying.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("电影");
                        }
                    });

                    bt_tuangou.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("团购");
                        }
                    });
                    bt_waimai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("外卖");
                        }
                    });
                    bt_lvxing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("旅行");
                        }
                    });
                    bt_liren.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("丽人");
                        }
                    });
                    bt_gouwu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("购物");
                        }
                    });
                    bt_chuxing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("出行");
                        }
                    });
                    bt_fuwu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("生活服务");
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }

    private void initPopupWindow() {
        view = this.getLayoutInflater().inflate(R.layout.activity_popupwindow, null);
        popuWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // 这里设置显示PopuWindow之后在外面点击是否有效。如果为false的话，那么点击PopuWindow外面并不会关闭PopuWindow。
        popuWindow.setOutsideTouchable(true);//不能在没有焦点的时候使用
        bt_meishi = (Button) view.findViewById(R.id.bt_meishi);
        bt_dianying = (Button) view.findViewById(R.id.bt_dianying);
        bt_tuangou = (Button) view.findViewById(R.id.bt_tuangou);
        bt_waimai = (Button) view.findViewById(R.id.bt_waimai);
        bt_lvxing = (Button) view.findViewById(R.id.bt_lvxing);
        bt_liren = (Button) view.findViewById(R.id.bt_liren);
        bt_gouwu = (Button) view.findViewById(R.id.bt_gouwu);
        bt_chuxing = (Button) view.findViewById(R.id.bt_chuxing);
        bt_fuwu = (Button) view.findViewById(R.id.bt_fuwu);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (popuWindow.isShowing()) {
            popuWindow.dismiss();
        }
    }
}
