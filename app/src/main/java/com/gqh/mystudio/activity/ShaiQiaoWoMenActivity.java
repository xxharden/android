package com.gqh.mystudio.activity;


import android.widget.ListView;

import com.gqh.mystudio.R;

import cn.myapp.base.BaseHttpActivity;
/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 左邻右舍  晒： 个人主页的谁看过我
 */
public class ShaiQiaoWoMenActivity extends BaseHttpActivity {

    private ListView listView;
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_qiao_wo_men;
    }

    @Override
    protected void initView() {
        listView= (ListView) findViewById(R.id.listView_qiaowomen);
    }
}
