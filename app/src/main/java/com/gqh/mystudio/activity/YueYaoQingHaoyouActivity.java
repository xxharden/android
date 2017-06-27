package com.gqh.mystudio.activity;


import android.view.View;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.YueFriendListAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.YueFriendEntity;
import com.gqh.mystudio.entity.YueFrienfResult;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 左邻右舍  约： 邀请好友
 */
public class YueYaoQingHaoyouActivity extends BaseHttpActivity {

    @ViewInject(R.id.listview)
    private ListView lv;
    private int groupId;
    private int uId;

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
    protected void onSuccess(int requestCode, String jsonStr) {
        try{

            if (requestCode==1){//获取好友列表
                YueFrienfResult result=GsonUtils.getSingleBean(jsonStr, YueFrienfResult.class);
                if (result.isSuccess()){
                    setData(result.data);
                }
            }

            if (requestCode==2){//发送邀请请求
                MyLog.i("json===",jsonStr);
                JSONObject obj=new JSONObject(jsonStr);
                if (obj.getString("code").equals("0")){
                    showToast("邀请成功");
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    private void setData(List<YueFriendEntity> data) {

        YueFriendListAdapter adapter=new YueFriendListAdapter(data,this);
        lv.setAdapter(adapter);
    }

    @Override
    protected void request() {
        if (groupId!=0){
            //获取好友列表
            RequestParams params1=new RequestParams();
            params1.put("UID",uId);
            params1.put("GroupID",groupId);
            postDialog(Constant.YUE_FRIEND_LIST,params1,1);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yao_qing_haoyou;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        groupId=getIntent().getIntExtra("GroupID",0);
        uId= BamsApplication.getInstance().getUser().getUID();

    }


    //adapter 中回调
    public void sendPostRequest(int fId){
        //发送邀请请求
        RequestParams params2=new RequestParams();
        params2.put("FID",fId);
        params2.put("GroupID",groupId);
        postDialog(Constant.YUE_GROUP_INVITE, params2, 2,false);
    }
}
