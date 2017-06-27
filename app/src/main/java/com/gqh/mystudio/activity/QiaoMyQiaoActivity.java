package com.gqh.mystudio.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.MyQiaolistAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyQiaoEntity;
import com.gqh.mystudio.entity.MyQiaoResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class QiaoMyQiaoActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.listView_friend)
    private ListView listView;
    private List<MyQiaoEntity> data;
    private int UID;
    private MyQiaolistAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_hao_you;
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
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
           if(requestCode==1){
               MyLog.i("json=====",jsonStr);
               MyQiaoResult result= GsonUtils.getSingleBean(jsonStr,MyQiaoResult.class);
               if (result.isSuccess()){
                   setData(result.data);
               }
           }
            if (requestCode==2){
                JSONObject jsonObject=new JSONObject(jsonStr);
                if (jsonObject.getString("code").equals("0")){
                    MyLog.i("json+++",jsonStr);
                    if (Ftype.equals("1")){
                        MyLog.i("json---", jsonStr);
                        showToast("已接受");

                        RequestParams params=new RequestParams();
                        params.put("UID", UID);
                        postDialog(Constant.MY_LIST_KNOCK, params, 1,false);

                    }else {
                        showToast("已拒绝");

                        RequestParams params=new RequestParams();
                        params.put("UID", UID);
                        postDialog(Constant.MY_LIST_KNOCK, params, 1,false);
                    }
                }
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        RequestParams params=new RequestParams();
        params.put("UID", UID);
        postDialog(Constant.MY_LIST_KNOCK,params,1);
    }

    public void setData(List<MyQiaoEntity> data) {
        this.data = data;
        adapter=new MyQiaolistAdapter(data,this);
        listView.setAdapter(adapter);
    }

    //供adapter 回调   接受-1，拒绝-0
    private String Ftype;
    public void sendRequest(String Ftype,int FID){
        this.Ftype=Ftype;
        RequestParams params =new RequestParams();
        params.put("UID",UID);
        params.put("FID",FID);
        params.put("Ftype",Ftype);
        postDialog(Constant.FRIEND_ACCEPT,params,2,false);
    }
}
