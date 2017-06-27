package com.gqh.mystudio.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.MyMessageListAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyMessageListEntity;
import com.gqh.mystudio.entity.MyMessageListResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class MyMessageListActivity extends BaseHttpActivity {

    @ViewInject(R.id.listView_friend)
    private ListView listView;
    private List<MyMessageListEntity> data;
    private ACache aCache;

    @OnClick(R.id.image_fanhui)
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_hao_you;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        aCache = ACache.get(context);
    }

    private List<MyMessageListEntity> msgList=new ArrayList<MyMessageListEntity>();
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode == 1) {
                MyLog.i("json===",jsonStr+ "====================================================");
                MyMessageListResult result = GsonUtils.getSingleBean(jsonStr, MyMessageListResult.class);
                if (result.isSuccess()) {
                    if(result.data.size()!=0){
                        aCache.put("message", jsonStr);
                        if (aCache.getAsObject("message") != null) {
                            MyMessageListResult acacheMessageResult= (MyMessageListResult)aCache.getAsObject("message");
                            msgList.addAll(acacheMessageResult.data);
                            msgList.addAll(result.data);
                            setData(msgList);
                        }else {
                            setData(result.data);
                        }
                    }else {
                        showToast("暂无您的消息");
                    }
                }
            }
        }catch(Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected void request() {

        Boolean isFirstIn = false;
        SharedPreferences pref = getSharedPreferences("myActivityName", 0);
        //取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = pref.getBoolean("isFirstIn", true);

        if (isFirstIn){

            SharedPreferences pref1 = getSharedPreferences("myActivityName", 0);
            SharedPreferences.Editor editor = pref1.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();

            RequestParams params=new RequestParams();
            params.put("UID", BamsApplication.getInstance().getUser().getUID());
            postDialog(Constant.MY_MESSAGE_LIST, params, 1,false);

            MyLog.i("first===", "====================================================");

            SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss     ");
            Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
            String    strDate    =    formatter.format(curDate);
            aCache.put("group_message",strDate);//缓存

        }else {
            String time= (String) aCache.getAsObject("group_message");
            RequestParams params=new RequestParams();
            params.put("UID", BamsApplication.getInstance().getUser().getUID());
            params.put("Time",time);//2016-03-17 14:09:11
            postDialog(Constant.MY_MESSAGE_LIST, params, 1,false);
            MyLog.i("2222===", "====================================================");
            SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss     ");
            Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
            String    strDate    =    formatter.format(curDate);
            aCache.put("group_message",strDate);//缓存
        }
    }

    public void setData(List<MyMessageListEntity> data) {
        this.data = data;
        MyMessageListAdapter adapter= new MyMessageListAdapter(data,this);
        listView.setAdapter(adapter);
    }
}
