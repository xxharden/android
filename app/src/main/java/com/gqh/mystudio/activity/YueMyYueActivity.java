package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.MyYueListAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyYueEntity;
import com.gqh.mystudio.entity.MyYueResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

import java.util.List;

import cn.myapp.base.BaseActivity;
import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class YueMyYueActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.rgroup)
    private RadioGroup rgroup;
    @ViewInject(R.id.rb_1)
    private RadioButton rb_1;
    @ViewInject(R.id.rb_2)
    private RadioButton rb_2;
    @ViewInject(R.id.rb_3)
    private RadioButton rb_3;
    @ViewInject(R.id.listview)
    private ListView listView;
    private int UID;
    private List<MyYueEntity> data;


    @Override
    protected int getContentView() {
        return R.layout.activity_yue_my_yue;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);

        UID=BamsApplication.getInstance().getUser().getUID();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                try {
                    switch (checkedId) {
                        case R.id.rb_1:
                            //受邀请
                            sendRequest("0");
                            break;
                        case R.id.rb_2:
                            //等待开始
                            sendRequest("1");
                            break;
                        case R.id.rb_3:
                            //已结束
                            sendRequest("2");
                            break;
                    }
                } catch (Exception e) {
                    ExceptionUtil.handleException(e);
                }
            }
        });

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
           if (requestCode==1){
               MyYueResult result= GsonUtils.getSingleBean(jsonStr,MyYueResult.class);
               if(result.isSuccess()){
                   setData(result.data);
               }
           }

            if (requestCode==2){
                JSONObject jsonObject=new JSONObject(jsonStr);
                if (jsonObject.getString("code").equals("0")){
                   if (s.equals("1")){
                       showToast("已接受");
                   }else {
                       showToast("已拒绝");
                   }
                }
            }


        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }


    }

    @Override
    protected void request() {
        //受邀请
        sendRequest("0");
    }

    private void sendRequest(String Type){
        //        活动类型， 0-受邀请，1-等待开始，2-已结束
        RequestParams params=new RequestParams();
        params.put("UID",UID);
        params.put("Type", Type);
        postDialog(Constant.MY_LIST_GROUP, params, 1);
    }

    public void setData(final List<MyYueEntity> data) {
        this.data = data;
//        活动类型， 0-受邀请，1-等待开始，2-已结束
        String type=null;
        if (rb_1.isChecked()){
            type="0";
        }else if (rb_2.isChecked()){
            type="1";
        }else {
            type="2";
        }
        MyYueListAdapter adapter=new MyYueListAdapter(data,this,type);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                type=getIntent().getIntExtra("type", 0);
//                groupID=getIntent().getIntExtra("GroupID",0);
                //“State”:”活动状态， 0 表示还未开始，1表示已结束。”
                Intent intent = new Intent(YueMyYueActivity.this, YueHuodongDetailActivity.class);
                if (rb_3.isChecked()) {
                    intent.putExtra("type", 1);
                } else {
                    intent.putExtra("type", 0);
                }

                intent.putExtra("GroupID", data.get(position).getGroupID());
                toActivity(intent);

            }
        });
    }

    //0表示拒绝，1表示接受
    private String s;
    public void sendRequest(int groupID, String s) {
        this.s=s;
        RequestParams params=new RequestParams();
        params.put("UID",UID);
        params.put("GroupID",groupID);
        params.put("Type",s);
        postDialog(Constant.GROUP_ACCEPT, params,2,false);
    }
}
