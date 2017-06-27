package com.gqh.mystudio.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.UnlockHistoryAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.UnlockHistoryResult;
import com.gqh.mystudio.entity.UnlockHisturyEntity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class ZhinengKaisuoLishiActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.listview)
    private ListView listview;
    private int mCustID;


    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            MyLog.i("========", jsonStr);
            UnlockHistoryResult result = GsonUtils.getSingleBean(jsonStr, UnlockHistoryResult.class);
            if (result.isSuccess()) {
                setData(result.data);
            } else {
                showToast(result.message);
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        MyLog.i("========", mCustID + "");
        RequestParams params = new RequestParams();
        params.put("CustID", mCustID);
//        params.put("DoorType",);//公寓门-1，房间门-2
//        params.put("PageIndex",);
//        params.put("PageNum",);
        postDialog(Constant.LIST_UNLOCK_DOOR_HISTORY, params, true);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_zhineng_kaisuo_lishi;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        mCustID = BamsApplication.getInstance().getUser().getCustID();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setData(List<UnlockHisturyEntity> data) {
        if (data.size() == 0) {
            showToast("暂无开锁记录");
        } else {
            UnlockHistoryAdapter adapter = new UnlockHistoryAdapter(data, context);
            listview.setAdapter(adapter);
        }
    }
}
