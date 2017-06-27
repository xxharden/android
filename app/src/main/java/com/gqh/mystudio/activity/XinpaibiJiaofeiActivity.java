package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.DaijiaofeiAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.DaijiaofeiEntity;
import com.gqh.mystudio.entity.DaijiaofeiResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class XinpaibiJiaofeiActivity extends BaseHttpActivity {

    @ViewInject(R.id.rl_jiaofei)
    private RelativeLayout rl_jiaofei;
    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.tv_heji)
    private TextView tv_heji;
    @ViewInject(R.id.bt_jiaofei)
    private Button bt_jiaofei;
    @ViewInject(R.id.listview)
    private ListView lv;
    private List<DaijiaofeiEntity> data;
    private int amount=0;
    private ArrayList<Integer>BillID=new ArrayList<Integer>();


    @Override
    protected int getContentView() {
        return R.layout.activity_xinpaibi_jiaofei;
    }


    @Override
    protected void initView() {

        try {
            ViewUtils.inject(this);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            bt_jiaofei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (amount!=0) {
                        boolean isUpass = BamsApplication.getInstance().getUser().getUPassWord();
                        if(!isUpass){
                            Intent intent=new Intent(XinpaibiJiaofeiActivity.this,PhoneYanzhengActivity.class);
                            intent.putExtra("style",1);
                            toActivity(intent);
                        }else {
                            Intent intent = new Intent(XinpaibiJiaofeiActivity.this, ZhifuFangshiActivity.class);
                            intent.putExtra("jine", amount + "");
                            String BillIDValue = null;
                            for (int i = 0; i < BillID.size(); i++) {
                                String b = BillID.get(i) + "";
                                if (BillIDValue == null) {
                                    BillIDValue = b;
                                } else {
                                    BillIDValue += "," + b;
                                }
                            }
                            intent.putExtra("BillID", BillIDValue);
                            toActivity(intent);
                        }
                    }
                }
            });

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
            if (requestCode==1){
                DaijiaofeiResult result1= GsonUtils.getSingleBean(jsonStr,DaijiaofeiResult.class);
                if (result1.isSuccess()){
                    if (result1.data.size()==0){
                        showToast("暂无账单");
                    }else {

                        setData(result1.data);
                    }
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        RequestParams params1=new RequestParams();
        params1.put("CustID", BamsApplication.getInstance().getUser().getCustID());
//        params1.put("CustID", 48);
        postDialog(Constant.DAI_JIAOFEI_LIST, params1,1,false);

    }

    public void setData(final List<DaijiaofeiEntity> data) {
        this.data = data;
        if (data.size()!=0){
            rl_jiaofei.setVisibility(View.VISIBLE);

            DaijiaofeiAdapter daijiaofeiAdapter=new DaijiaofeiAdapter(data,this);
            lv.setAdapter(daijiaofeiAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.image_check);

                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        BillID.remove(BillID.size()-1);
                        amount -= (data.get(position).getAmount());
                        tv_heji.setText("合计：" + amount);
                    } else {
                        checkBox.setChecked(true);
                        BillID.add(data.get(position).getBillID());
                        amount += (data.get(position).getAmount());
                        tv_heji.setText("合计：" + amount);
                    }
                }
            });


        }
    }
}
