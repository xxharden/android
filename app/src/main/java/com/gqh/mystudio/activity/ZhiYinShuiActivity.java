package com.gqh.mystudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.gqh.mystudio.R;
import com.gqh.mystudio.bean.FacilityOperate;
import com.gqh.mystudio.bean.ServiceResponseEntity;
import com.gqh.mystudio.facility.DeviceListResponseEntity;
import com.gqh.mystudio.facility.FacilityServiceAPI;
import com.gqh.mystudio.facility.Http;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.HashMap;
import java.util.Map;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;
import de.tavendo.autobahn.WebSocketException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ZhiYinShuiActivity extends BaseActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.zys_tv_open)
    private TextView zys_tv_open;
    @ViewInject(R.id.zys_iv_shuizhi)
    private ImageView iv_shuizhi;
    @ViewInject(R.id.zys_tv_shuijia)
    private TextView tv_shuijia;
    @ViewInject(R.id.zys_tv_shuiliang)
    private TextView tv_shuiliang;
    @ViewInject(R.id.zys_tv_yue)
    private TextView tv_yue;
    @ViewInject(R.id.zys_ll_chongzhi)
    private LinearLayout ll_chongzhi;
    @ViewInject(R.id.zys_ll1)
    private LinearLayout ll1;
    @ViewInject(R.id.zys_ll2)
    private LinearLayout ll2;
    @ViewInject(R.id.zys_ll_bianhao)
    private LinearLayout ll_bianhao;
    @ViewInject(R.id.zys_ll_maichong)
    private LinearLayout ll_maichong;
    @ViewInject(R.id.zys_et1)
    private EditText et1;
    @ViewInject(R.id.zys_et2)
    private EditText et2;
    @ViewInject(R.id.zys_et3)
    private EditText et3;
    @ViewInject(R.id.zys_et4)
    private EditText et4;
    @ViewInject(R.id.zys_tv_shezhi1)
    private TextView tv_shezhi1;
    @ViewInject(R.id.zys_tv_shezhi2)
    private TextView tv_shezhi2;
    @ViewInject(R.id.zys_tv_shezhi3)
    private TextView tv_shezhi3;
    @ViewInject(R.id.zys_tv_yc1)
    private TextView tv_yc1;
    @ViewInject(R.id.zys_tv_yc2)
    private TextView tv_yc2;

    private boolean flag = false;
    private boolean flag_ll1 = false;
    private boolean flag_ll2 = false;

    int pointHeight = 0;
    private String money;
    private String number;

    private final WebSocketConnection mConnection = new WebSocketConnection();
    private RotateAnimation animation;

    @OnClick({R.id.image_fanhui, R.id.zys_tv_open, R.id.zys_ll_chongzhi, R.id.zys_ll1, R.id.zys_ll2,
            R.id.zys_tv_shezhi1, R.id.zys_tv_shezhi2, R.id.zys_tv_shezhi3})
    private void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.zys_tv_open:
                    if (flag) {
                        //关
                        HashMap<String, Object> param = new HashMap<>();
                        param.put("control", "1");
                        sendAction("control", param);
                        flag = false;
                        //zys_tv_open.setText("开");
                    } else {
                        HashMap<String, Object> param = new HashMap<>();
                        param.put("control", "0");
                        sendAction("control", param);
                        flag = true;
                        //zys_tv_open.setText("关");
                    }
                    break;
                case R.id.zys_ll_chongzhi:
                    Intent i = new Intent(ZhiYinShuiActivity.this, ZysPayActivity.class);
                    i.putExtra("thingId", "003.w3c-test");
                    startActivity(i);
                    break;
                //查询设备编号
                case R.id.zys_ll1:
                    if (flag_ll1) {
                        ll_bianhao.setVisibility(View.GONE);
                        flag_ll1 = false;
                    } else {
                        HashMap<String, Object> param = new HashMap<>();
                        sendAction("getDeviceID", param);
                        ll_bianhao.setVisibility(View.VISIBLE);
                        flag_ll1 = true;
                    }
                    break;
                case R.id.zys_ll2:
                    if (flag_ll2) {
                        ll_maichong.setVisibility(View.GONE);
                        flag_ll2 = false;
                    } else {
                        HashMap<String, Object> param = new HashMap<>();
                        sendAction("getFluxPulse", param);
                        ll_maichong.setVisibility(View.VISIBLE);
                        flag_ll2 = true;
                    }
                    break;
                case R.id.zys_tv_shezhi1:

                    HashMap<String, Object> param = new HashMap<>();
                    String etSdn = et2.getText().toString();
                    String eSdt = et1.getText().toString();
                    Log.i("zp", etSdn + "etSdn");
                    if (etSdn.length() != 0 || eSdt.length() != 0) {

                        if (!etSdn.equals("10")) {
                            param.put("type", eSdt);
                        } else {
                            param.put("type", "10");
                        }
                        if (!eSdt.equals("12000")) {
                            param.put("number", etSdn);
                        } else {
                            param.put("number", "12000");
                        }
                    } else {
                        param.put("type", "10");
                        param.put("number", "12000");
                    }
                    sendAction("setDeviceID", param);
                    showToast("设置成功");
                    break;
                case R.id.zys_tv_shezhi2:
                    HashMap<String, Object> param1 = new HashMap<>();
                    String s = et3.getText().toString();
                    if (s.length() != 0) {
                        param1.put("flux", s);
                    } else {
                        param1.put("flux", "5000");
                    }
                    sendAction("setPrice", param1);
                    onUnitPrice();
                    showToast("设置成功");
                    break;
                case R.id.zys_tv_shezhi3:
                    HashMap<String, Object> param2 = new HashMap<>();
                    String s1 = et4.getText().toString();
                    if (s1.length() != 0) {
                        param2.put("pulse", s1);
                    } else {
                        param2.put("pulse", "516");
                    }
                    sendAction("setFluxPulse", param2);
                    showToast("设置成功");
                    break;

            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    DeviceListResponseEntity.Device device;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_zhi_yin_shui);

        device = getIntent().getExtras().getParcelable("facility");
        loadService(device);
        ViewUtils.inject(this);


    }

    private void loadService(final DeviceListResponseEntity.Device device) {
        FacilityServiceAPI facilityServiceAPI = Http.getInstance().create(FacilityServiceAPI.class);
        Log.i("dyy", "thingid" + device.thingId);
        Call<ServiceResponseEntity> up = facilityServiceAPI.up("down-up", device.thingId, device.harborIp);

        requestNoloading(up, new Callback<ServiceResponseEntity>() {
            @Override
            public void onResponse(Call<ServiceResponseEntity> call, Response<ServiceResponseEntity> response) {
                ServiceResponseEntity body = response.body();
                if (body != null && body.error_code == 0) {
                    Toast.makeText(ZhiYinShuiActivity.this, "正在连接设备", Toast.LENGTH_SHORT).show();
                    Log.i("dyy", "har" + device.harborIp);
                    getSocketConnect(device.harborIp);
                }

                if (body != null && body.error_code != 0) {
                    Toast.makeText(ZhiYinShuiActivity.this, body.error_msg, Toast.LENGTH_SHORT).show();
                    Log.i("dyy", "body.error_msg:" + body.error_msg);
                }
            }

            @Override
            public void onFailure(Call<ServiceResponseEntity> call, Throwable t) {
                //Logger.e(t.getMessage());
            }
        });
    }

    private void sendAction(String serviceId, HashMap<String, Object> param) {
        FacilityOperate.Other operate = new FacilityOperate.Other();
        // Log.i("dyy", device.thingId);
        operate.thingId = device.thingId;
        operate.serviceId = serviceId;
        operate.param = param;
        operate.seq = "沃特德";
        String s = new Gson().toJson(operate);
        if (mConnection.isConnected()) {
            mConnection.sendRawTextMessage(s.getBytes());
            //Logger.i("发送内容".concat(s));
        } else {
            //Toast.makeText(WaterDispenserActivity.this, "未连接到设备,请返回重试", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSocketConnect(String ip) {
        ip = "ws://".concat(ip).concat("/IotHarborWebsocket");
        ip = ip.replace("8080", "8999");
        final String wsuri = ip;

        try {
            mConnection.connect(wsuri, new WebSocketConnectionHandler() {

                //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onOpen() {
                    if (ZhiYinShuiActivity.this.isDestroyed() || ZhiYinShuiActivity.this.isFinishing()) {
                        return;
                    }
                    //Logger.d("Status: Connected to " + wsuri);
                    Toast.makeText(ZhiYinShuiActivity.this, "已连接设备", Toast.LENGTH_SHORT).show();
                    //订阅
                    subscribes();
                }

                //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onTextMessage(String payload) {
                    Log.i("dyy", "payload:|==" + payload + "==|");
                    // Logger.d("Got echo: " + payload);
                    if (ZhiYinShuiActivity.this.isFinishing() || ZhiYinShuiActivity.this.isDestroyed()) {
                        return;
                    }
                    /**
                     *  判断是否订阅成功
                     */
                    if (payload.equals("subscribe success")) {
                        Log.i("dyy", "订阅成功");
                        return;
                    }
                    /**
                     * 判断是否收到数据
                     */
                    if (payload.endsWith("timeout")) {
                        showToast("没有收到设备的数据");
                        return;
                    }
                    /**
                     *判断设备是否离线
                     */
                    if (payload.equals("thing not online:003.w3c-test")) {
//                        Log.i("dyy", device.thingId);
                        showToast("设备已离线");
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        finish();
                        return;
                    }
                    try {
                        Map<String, Object> map = new Gson().fromJson(payload, new TypeToken<Map<String, Object>>() {
                        }.getType());

                        if (map != null && map.size() > 0) {

                            Object data = map.get("data");
                            Object serviceID1 = map.get("serviceID");
                            Log.i("dyy", serviceID1.toString() + "===========serviceID1");

                            if (data instanceof Map) {
                                Map<String, String> data1 = (Map<String, String>) data;
                                Log.i("dyy", "data:|==" + data1.toString() + "==|");
                                /**
                                 * 到此............
                                 */
                                money = String.valueOf(data1.get("money"));
                                number = String.valueOf(data1.get("number"));
                                String sum = String.valueOf(data1.get("sum"));
                                String type = String.valueOf(data1.get("type"));
                                String number2 = String.valueOf(data1.get("number"));
                                String user = String.valueOf(data1.get("user"));
                                String flux = String.valueOf(data1.get("flux"));
                                String pulse = String.valueOf(data1.get("pulse"));


                                //设置价格
                                if (serviceID1.equals("getBalance")) {
                                    if (money != null) {
                                        setMoney(money);
                                    }
                                }
                                //设置水质
                                if (serviceID1.equals("getTDS")) {
                                    if (number != null) {
                                        setNumber(number);
                                    }
                                }
                                //设置水量
                                if (serviceID1.equals("getFluxSum")) {
                                    if (sum != null) {
                                        setWater(sum);
                                    }
                                }
                                //设置设备编号
                                if (serviceID1.equals("getDeviceID")) {
                                    setTypeAndNumber(type, number2);
                                }
                                //查询单价
                                if (serviceID1.equals("getPrice")) {
                                    tv_shuijia.setText("￥" + flux);
                                    Toast.makeText(ZhiYinShuiActivity.this, flux + "￥", Toast.LENGTH_SHORT).show();
                                }
                                //查询脉冲
                                if (serviceID1.equals("getFluxPulse")) {
                                    tv_yc2.setText("￥" + pulse);
                                    Toast.makeText(ZhiYinShuiActivity.this, "￥" + pulse, Toast.LENGTH_SHORT).show();
                                }
                                //查看是否停机
                                if (serviceID1.equals("stop")) {
                                    Toast.makeText(ZhiYinShuiActivity.this, "用户" + user + "停机", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    //Logger.d("Connection lost.");
                }

            });
        } catch (WebSocketException e) {
            // Logger.d(e.toString());
        }
    }

    //查看设备编号
    private void setTypeAndNumber(String type, String number2) {
        tv_yc1.setText("Type:" + type + "---Number:" + number2);
        // Toast.makeText(this, "type" + type + "======number" + number2, Toast.LENGTH_SHORT).show();
    }

    //设定余额
    public void setMoney(String money) {
//        double credit = Double.parseDouble(money);
//        String textShow = String.valueOf(credit) + "元";
        tv_yue.setText(money + "元");
    }

    //水价
    public void onUnitPrice() {
        HashMap<String, Object> param = new HashMap<>();
        sendAction("getPrice", param);
    }

    //设定水量
    public void setWater(String ml) {
        tv_shuiliang.setText(ml + "ml");
    }

    //设定水质
    public void setNumber(String number) {
        Log.i("dyy", number + "========number");
        double quality = Double.parseDouble(number);
        pointHeight = iv_shuizhi.getHeight();
        //字
        String qualityWord = "优";
        if (quality > 0) {
            qualityWord = "优";
        }
        if (quality > 25) {
            qualityWord = "良";
        }
//        if (quality > 50) {
//            qualityWord = "中";
//        }
        if (quality > 75) {
            qualityWord = "差";
        }
        //tv_quality_word.setText(qualityWord);
        //表
//        double degree = (quality + 50) / 5 * 12;
//        iv_shuizhi.setRotation((float) degree);
        if (qualityWord.equals("优")) {
            animation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            iv_shuizhi.startAnimation(animation);
        } else if (qualityWord.equals("差")) {
            animation = new RotateAnimation(0f, -90f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            iv_shuizhi.startAnimation(animation);
        }


    }


    //订阅余额、停机、水质信息
    private void subscribes() {
        onCreditClick1();
        onWaterQualityClick1();
        onbtnQuerywater1();
//        OnUploadSmartDeviceNumber();
//        OnReturnWaterFlow();
        OnStopClick();
        // OnPayMoney();
        onUnitPrice();
    }

    public void onCreditClick1() {
        HashMap<String, Object> param = new HashMap<>();
//        param.put("subscribe", "on");
        sendAction("getBalance", param);
    }

    public void onWaterQualityClick1() {
        HashMap<String, Object> param = new HashMap<>();
        sendAction("getTDS", param);
    }

    private void onbtnQuerywater1() {
        HashMap<String, Object> param = new HashMap<>();
        sendAction("getFluxSum", param);
    }

    //停机
    public void OnStopClick() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("subscribe", "on");
        sendAction("stop", param);
    }
}