package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.gqh.mystudio.R;
import com.gqh.mystudio.bean.ShuiBiao1;
import com.gqh.mystudio.bean.ShuiBiao2;
import com.gqh.mystudio.utill.HttpUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import cn.myapp.base.BaseActivity;

public class ShuiBiaoActivity extends BaseActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.db_tv1)
    private TextView tv1;
    @ViewInject(R.id.db_tv2)
    private TextView tv2;
    @ViewInject(R.id.db_tv3)
    private TextView tv3;
    @ViewInject(R.id.db_tv4)
    private TextView tv4;
    @ViewInject(R.id.db_tv5)
    private TextView tv5;
    @ViewInject(R.id.db_tv6)
    private TextView tv6;
    @ViewInject(R.id.db_tv7)
    private TextView tv7;
    @ViewInject(R.id.db_tv8)
    private TextView tv8;
    @ViewInject(R.id.db_tv9)
    private TextView tv9;
    @ViewInject(R.id.bt1)
    private TextView bt1;
    @ViewInject(R.id.bt2)
    private TextView bt2;

    @OnClick({R.id.bt1, R.id.bt2, R.id.image_fanhui})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_fanhui:
                finish();
                break;
            case R.id.bt1:

                break;
            case R.id.bt2:

                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    if (!s.equals("")) {
                        ShuiBiao1 s1 = new Gson().fromJson(s, ShuiBiao1.class);
                        if (s1.getCode().equals("200")) {
                            tv1.setText(s1.getMsg().get(0).getMeterId());
                            tv2.setText(s1.getMsg().get(0).getModel());
                            tv3.setText(s1.getMsg().get(0).getSwicthId());
                            tv4.setText(s1.getMsg().get(0).getRoomId());
                        }
                    }

                    break;
                case 2:
                    String s1 = (String) msg.obj;
                    if (!s1.equals("")) {
                        ShuiBiao2 s2 = new Gson().fromJson(s1, ShuiBiao2.class);
                        Log.d("zp", "handleMessage: " + s1);
                        if (s2.getCode().equals("200")) {
                            tv5.setText(s2.getMsg().get(0).getWaterTatol() + "");
                            tv6.setText(s2.getMsg().get(0).getWaterRemain() + "");
                            tv7.setText(s2.getMsg().get(0).getForceMode() + "");
                            tv8.setText(s2.getMsg().get(0).isPowerOwe() + "");
                            tv9.setText(s2.getMsg().get(0).isOweStatus() + "");
                        }
                    }
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui_biao);
        getData();
        ViewUtils.inject(this);

    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String s1 = HttpUtil.getDianBiao("http://192.168.1.216:8080/METER/TMW/TMW_queryTMeterWaterById", "1604040197");
                Message msg1 = handler.obtainMessage();
                msg1.what = 1;
                msg1.obj = s1;
                handler.sendMessage(msg1);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                String s2 = HttpUtil.getDianBiao("http://192.168.1.216:8080/METER/TCW/TCW_queryTCollectionWaterByMeterID", "1604040197");
                Message msg2 = handler.obtainMessage();
                msg2.what = 2;
                msg2.obj = s2;
                handler.sendMessage(msg2);
            }
        }.start();
    }
}
