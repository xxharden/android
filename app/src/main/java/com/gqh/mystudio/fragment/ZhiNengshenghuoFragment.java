package com.gqh.mystudio.fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.ZhiYinShuiActivity;
import com.gqh.mystudio.activity.ZhinengKaisuoLishiActivity;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.bean.FacilityOperate;
import com.gqh.mystudio.bean.YunYouLock;
import com.gqh.mystudio.entity.UnlockHistoryResult;
import com.gqh.mystudio.facility.DeviceListResponseEntity;
import com.gqh.mystudio.facility.FacilityAPI;
import com.gqh.mystudio.facility.FacilityAdapter;
import com.gqh.mystudio.facility.Http;
import com.gqh.mystudio.facility.TypeDeviceListResponseEntity;
import com.gqh.mystudio.utill.DensityUtil;
import com.gqh.mystudio.utill.HttpUtil;
import com.gqh.mystudio.utill.TextMoveLayout;
import com.gqh.mystudio.utill.VibrateHelp;
import com.gqh.mystudio.view.MyListView;
import com.gqh.mystudio.view.SlidButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.myapp.base.BaseHttpFragment;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;
import de.tavendo.autobahn.WebSocketException;
import http.RequestParams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gqh.mystudio.R.id.seekbar;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: fragment 智能生活
 */
public class ZhiNengshenghuoFragment extends BaseHttpFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    /**
     * 按钮震动时间
     */
    private final int VIBRATE_TIME = 100;

//    private SoundPool sp;//声明一个SoundPool
//    private int music;//定义一个整型用load（）；来设置suondID

    private static final int HANDELR_TEXT = 10000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    String s = (String) msg.obj;
                    showToast(s);
                case HANDELR_TEXT:
                    wiperSwitch1.setLockOff();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @ViewInject(R.id.kaisuo_time)
    TextView kaisuo_time;
    @ViewInject(R.id.wiperSwitch1)
    private SlidButton wiperSwitch1;
    @ViewInject(R.id.kaisuo_lishi)
    private TextView kaisuo_lishi;
    @ViewInject(R.id.bt_kaiguan)
    private CheckBox bt_kaiguan;
    //    @ViewInject(R.id.bt_kaiguan_1)
//    private CheckBox bt_kaiguan1;
    @ViewInject(R.id.bt_kaiguan_2)
    private CheckBox bt_kaiguan2;
    @ViewInject(R.id.bt_kaiguan_3)
    private CheckBox bt_kaiguan3;
    @ViewInject(seekbar)
    private SeekBar seekBar;
    @ViewInject(R.id.ll_parent)
    private LinearLayout ll_parent;
    @ViewInject(R.id.rl_zhiyinshui)
    private RelativeLayout rl_zhiyinshui;
    private User mUser;
    // @ViewInject(R.id.zys_refresh_layout)
    // private SwipeRefreshLayout refreshLayout;
    @ViewInject(R.id.zys_lv)
    private MyListView listView;
    private TextView moveText;

    private int screenWidth;//屏幕宽度
    @ViewInject(R.id.tvWendu)
    private TextMoveLayout textMoveLayout;
    @ViewInject(R.id.iv_cl_off)
    private ImageView iv_cl_off;
    @ViewInject(R.id.iv_cl_pause)
    private ImageView iv_cl_pause;
    @ViewInject(R.id.iv_cl_on)
    private ImageView iv_cl_on;
    @ViewInject(R.id.iv_erjian1)
    private ImageView iv_erjian1;
    @ViewInject(R.id.iv_erjian2)
    private ImageView iv_erjian2;
    @ViewInject(R.id.iv_sanjian1)
    private ImageView iv_sanjian1;
    @ViewInject(R.id.iv_sanjian2)
    private ImageView iv_sanjian2;
    @ViewInject(R.id.iv_sanjian3)
    private ImageView iv_sanjian3;
    @ViewInject(R.id.iv_kt_di)
    private ImageView iv_kt_di;
    @ViewInject(R.id.iv_kt_zhong)
    private ImageView iv_kt_zhong;
    @ViewInject(R.id.iv_kt_gao)
    private ImageView iv_kt_gao;
    @ViewInject(R.id.bt_zhileng)
    private Button bt_zhileng;
    @ViewInject(R.id.bt_zhire)
    private Button bt_zhire;
    @ViewInject(R.id.bt_tongfeng)
    private Button bt_tongfeng;
    @ViewInject(R.id.tv_snwd)
    private TextView tv_snwd;
    private ViewGroup.LayoutParams layoutParams;
    private int mLeftMargin;
    private TextPaint mPaint;
    FacilityAdapter adapter;

    private static double wenduNum = 21.0;
    private boolean flag = true;
    protected boolean switch1 = true, switch2 = true, switch3 = true, switch4 = true, switch5 = true;
    protected int switchNum = 0;
    private WebSocketConnection connection1 = new WebSocketConnection();
    private WebSocketConnection connection2 = new WebSocketConnection();
    private WebSocketConnection connection3 = new WebSocketConnection();
    private WebSocketConnection connection4 = new WebSocketConnection();

    @OnClick({R.id.tvMore, R.id.ll_gongyumen, R.id.kaisuo_lishi, R.id.bt_kaiguan, R.id.bt_kaiguan_2,
            R.id.bt_kaiguan_3, R.id.rl_zhiyinshui, R.id.iv_cl_off, R.id.iv_cl_pause, R.id.iv_cl_on,
            R.id.iv_erjian1, R.id.iv_erjian2, R.id.iv_sanjian1, R.id.iv_sanjian2, R.id.iv_sanjian3,
            R.id.iv_kt_di, R.id.iv_kt_zhong, R.id.iv_kt_gao, R.id.bt_zhileng, R.id.bt_zhire, R.id.bt_tongfeng})
    private void onClick(View v) {
        try {
            HashMap<String, Object> data = new HashMap<>();
            switch (v.getId()) {
                case R.id.kaisuo_lishi:
                    toActivity(ZhinengKaisuoLishiActivity.class);
                    break;
                case R.id.rl_zhiyinshui:
                    //toActivity(ZhiYinShuiActivity.class);
                    break;
                case R.id.bt_kaiguan:
                    if (bt_kaiguan.isChecked()) {
                        data.put("status_onoff", 0);
                        sendAction(connection4, "海林温控器1.测试", "setStatusOnOff", data);
                        bt_kaiguan.setBackgroundResource(R.drawable.kaiguan_off);
                    } else {
                        data.put("status_onoff", 1);
                        sendAction(connection4, "海林温控器1.测试", "setStatusOnOff", data);
                        bt_kaiguan.setBackgroundResource(R.drawable.kaiguan_on);
                    }
                    break;
//                case R.id.bt_kaiguan_1:
//                    if (bt_kaiguan1.isChecked()){
//                        bt_kaiguan1.setBackgroundResource(R.drawable.kaiguan_off);
//                    }else {
//                        bt_kaiguan1.setBackgroundResource(R.drawable.kaiguan_on);
//                    }
//                    break;
                case R.id.bt_kaiguan_2:
                    if (bt_kaiguan2.isChecked()) {
                        bt_kaiguan2.setBackgroundResource(R.drawable.kaiguan_off);
                    } else {
                        bt_kaiguan2.setBackgroundResource(R.drawable.kaiguan_on);
                    }
                    break;
                case R.id.bt_kaiguan_3:
                    if (bt_kaiguan3.isChecked()) {
                        bt_kaiguan3.setBackgroundResource(R.drawable.kaiguan_off);
                    } else {
                        bt_kaiguan3.setBackgroundResource(R.drawable.kaiguan_on);
                    }
                    break;
                case R.id.ll_gongyumen:
                    openDoor(DOOR_TYPE_GONGYUMEN, mUser.getApartmentID());
                    break;
                case R.id.iv_cl_off:
                    data.put("value", "OFF");
                    sendAction(connection1, "20170527-005.测试.测试", "switch", data);
                    iv_cl_off.setImageResource(R.mipmap.zn_chuanglian_off);
                    iv_cl_on.setImageResource(R.mipmap.zn_chuanglian_on1);
                    iv_cl_pause.setImageResource(R.mipmap.zn_chuanglian_pause1);
                    break;
                case R.id.iv_cl_on:
                    data.put("value", "ON");
                    sendAction(connection1, "20170527-005.测试.测试", "switch", data);
                    iv_cl_off.setImageResource(R.mipmap.zn_chuanglian_off1);
                    iv_cl_on.setImageResource(R.mipmap.zn_chuanglian_on);
                    iv_cl_pause.setImageResource(R.mipmap.zn_chuanglian_pause1);
                    break;
                case R.id.iv_cl_pause:
                    data.put("value", "STOP");
                    sendAction(connection1, "20170527-005.测试.测试", "switch", data);
                    iv_cl_off.setImageResource(R.mipmap.zn_chuanglian_off1);
                    iv_cl_on.setImageResource(R.mipmap.zn_chuanglian_on1);
                    iv_cl_pause.setImageResource(R.mipmap.zn_chuanglian_pause);
                    break;
                case R.id.iv_erjian1:
                    if (switch4) {
                        data.put("id", 1);
                        data.put("value", "OFF");
                        sendAction(connection2, "switch20161121-002.测试", "switch", data);
                        iv_erjian1.setImageResource(R.mipmap.zn_on);
                        switch4 = false;
                    } else {
                        data.put("id", 1);
                        data.put("value", "ON");
                        sendAction(connection2, "switch20161121-002.测试", "switch", data);
                        iv_erjian1.setImageResource(R.mipmap.zn_off);
                        switch4 = true;
                    }
                    break;
                case R.id.iv_erjian2:
                    if (switch5) {
                        data.put("id", 2);
                        data.put("value", "OFF");
                        sendAction(connection2, "switch20161121-002.测试", "switch", data);
                        iv_erjian2.setImageResource(R.mipmap.zn_on);
                        switch5 = false;
                    } else {
                        data.put("id", 2);
                        data.put("value", "ON");
                        sendAction(connection2, "switch20161121-002.测试", "switch", data);
                        iv_erjian2.setImageResource(R.mipmap.zn_off);
                        switch5 = true;
                    }
                    break;
                case R.id.iv_sanjian1:
                    if (switch1) {
                        data.put("id", 1);
                        data.put("value", "OFF");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian1.setImageResource(R.mipmap.zn_on);
                        switch1 = false;
                    } else {
                        data.put("id", 1);
                        data.put("value", "ON");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian1.setImageResource(R.mipmap.zn_off);
                        switch1 = true;
                    }
                    break;
                case R.id.iv_sanjian2:
                    if (switch2) {
                        data.put("id", 2);
                        data.put("value", "OFF");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian2.setImageResource(R.mipmap.zn_on);
                        switch2 = false;
                    } else {
                        data.put("id", 2);
                        data.put("value", "ON");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian2.setImageResource(R.mipmap.zn_off);
                        switch2 = true;
                    }
                    break;
                case R.id.iv_sanjian3:
                    if (switch3) {
                        data.put("id", 3);
                        data.put("value", "OFF");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian3.setImageResource(R.mipmap.zn_on);
                        switch3 = false;
                    } else {
                        data.put("id", 3);
                        data.put("value", "ON");
                        sendAction(connection3, "switch20161121-001.测试", "switch", data);
                        iv_sanjian3.setImageResource(R.mipmap.zn_off);
                        switch3 = true;
                    }
                    break;
                case R.id.iv_kt_di:
                    data.put("fan_mod", 3);
                    sendAction(connection4, "海林温控器1.测试", "setFanMod", data);
                    iv_kt_di.setImageResource(R.mipmap.kongtiao_di1);
                    iv_kt_zhong.setImageResource(R.mipmap.kongtiao_zhong);
                    iv_kt_gao.setImageResource(R.mipmap.kongtiao_gao);
                    break;
                case R.id.iv_kt_zhong:
                    data.put("fan_mod", 4);
                    sendAction(connection4, "海林温控器1.测试", "setFanMod", data);
                    iv_kt_di.setImageResource(R.mipmap.kongtiao_di);
                    iv_kt_zhong.setImageResource(R.mipmap.kongtiao_zhong1);
                    iv_kt_gao.setImageResource(R.mipmap.kongtiao_gao);
                    break;
                case R.id.iv_kt_gao:
                    data.put("fan_mod", 5);
                    sendAction(connection4, "海林温控器1.测试", "setFanMod", data);
                    iv_kt_di.setImageResource(R.mipmap.kongtiao_di);
                    iv_kt_zhong.setImageResource(R.mipmap.kongtiao_zhong);
                    iv_kt_gao.setImageResource(R.mipmap.kongtiao_gao1);
                    break;
                case R.id.bt_zhire:
                    data.put("status", 2);
                    sendAction(connection4, "海林温控器1.测试", "setStatus", data);
                    bt_zhire.setBackgroundResource(R.mipmap.zhire1);
                    bt_zhileng.setBackgroundResource(R.mipmap.zhileng);
                    bt_tongfeng.setBackgroundResource(R.mipmap.tongfeng);
                    flag = true;
                    break;
                case R.id.bt_zhileng:
                    data.put("status", 1);
                    sendAction(connection4, "海林温控器1.测试", "setStatus", data);
                    bt_zhire.setBackgroundResource(R.mipmap.zhire);
                    bt_zhileng.setBackgroundResource(R.mipmap.zhileng1);
                    bt_tongfeng.setBackgroundResource(R.mipmap.tongfeng);
                    flag = false;
                    break;
                case R.id.bt_tongfeng:
                    data.put("status", 5);
                    sendAction(connection4, "海林温控器1.测试", "setStatus", data);
                    bt_zhire.setBackgroundResource(R.mipmap.zhire);
                    bt_zhileng.setBackgroundResource(R.mipmap.zhileng);
                    bt_tongfeng.setBackgroundResource(R.mipmap.tongfeng1);
                    break;
//                case R.id.ivAdd:
//                    wenduNum=wenduNum+1;
//                    tvWendu.setText(String.valueOf(wenduNum));
//                    break;
//                case R.id.ivDelete:
//                    wenduNum=wenduNum-1;
//                    tvWendu.setText(String.valueOf(wenduNum));
//                    break;
                case R.id.tvMore:
                    showToast("敬请期待");
                    break;
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode == 10) {
                MyLog.i("=====", jsonStr);
                //{"message":"新建开门记录成功。","code":"0"}
                JSONObject object = new JSONObject(jsonStr);
                String code = object.getString("code");
                if (code.equals("0")) {
                    showToast("已开门");
                    //启动震动，并持续指定的时间
                    VibrateHelp.vSimple(context, VIBRATE_TIME);
//                sp.play(music, 1, 1, 0, 0, 1);
                } else {
                    showToast(object.getString("message"));
                }
            }
            if (requestCode == 11) {
                UnlockHistoryResult result = GsonUtils.getSingleBean(jsonStr, UnlockHistoryResult.class);
                if (result.isSuccess()) {
                    if (result.data.size() == 0) {
                        kaisuo_time.setText("最近还没开过锁哦");
                    } else {
                        kaisuo_time.setText("上次开锁时间：" + result.data.get(0).getCreateTime());
                    }
                } else {
                    showToast(result.message);
                }
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        RequestParams params = new RequestParams();
        params.put("CustID", mUser.getCustID());
//        params.put("DoorType",);//公寓门-1，房间门-2
//        params.put("PageIndex",);
//        params.put("PageNum",);
        postDialog(Constant.LIST_UNLOCK_DOOR_HISTORY, params, 11, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestParams params = new RequestParams();
        params.put("CustID", mUser.getCustID());
//        params.put("DoorType",);//公寓门-1，房间门-2
//        params.put("PageIndex",);
//        params.put("PageNum",);
        postDialog(Constant.LIST_UNLOCK_DOOR_HISTORY, params, 11, true);
        getSocketConnect(connection1, "101.201.30.234:8080", 1);
        getSocketConnect(connection4, "101.201.30.234:8080", 4);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_zhi_nengshenghuo_fragment;
    }

    private static final int DOOR_TYPE_FANGJIANMEN = 2;
    private static final int DOOR_TYPE_GONGYUMEN = 1;

    @Override
    protected void initView(View contentView) {
        ViewUtils.inject(this, contentView);
//        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
//        music = sp.load(context, R.raw.opendoor, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

        mUser = BamsApplication.getInstance().getUser();
        wiperSwitch1.SetOnChangedListener(new SlidButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean checkState) {
                if (checkState) {
                    //openDoor(DOOR_TYPE_FANGJIANMEN, mUser.getRoomID());

                    try {
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                Message msg = handler.obtainMessage();
                                msg.what = 123;
                                msg.obj = getData();
                                handler.sendMessage(msg);
                            }
                        }).start();
                        //定时器2秒自动滑回
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //2秒 跳回 发消息
                                Message msg = new Message();
                                msg.what = HANDELR_TEXT;
                                handler.sendMessage(msg);
                            }
                        }, 2000);

                    } catch (Exception e) {
                        ExceptionUtil.handleException(e);
                    }
                }
            }
        });
        screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        setMoveTextView();
        getChildenLayoutParams();
        setSeekBarValues();
        Log.d("zp", "iaaaaaaa");
        // refreshLayout.setOnRefreshListener(this);
        loadFacility("315");

        getSocketConnect(connection1, "101.201.30.234:8080", 1);
        getSocketConnect(connection4, "101.201.30.234:8080", 4);
        // getSocketConnect(connection3, "101.201.30.234:8080", 3);

    }

    private String getData() {
        String s = HttpUtil.openDoor("00124b000f0915cf", "open");
        if (!s.equals("")) {
            Gson g = new Gson();
            YunYouLock yy = g.fromJson(s, YunYouLock.class);
            if (yy.isSuccess()) {
                return "开锁成功";
            }
        }
        return null;
    }

    /**
     * 开门
     */
    private void openDoor(int DoorType, int doorId) {
        RequestParams params = new RequestParams();
        params.put("CustID", mUser.getCustID());
        params.put("DoorType", DoorType);
        params.put("DoorID", doorId);
        params.put("Phone", mUser.getULoginID());
        postDialog(Constant.LIST_UNLOCK_DOOR_OPEN, params, 10, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unSubscribe();
        connection1.disconnect();
        connection4.disconnect();
        //释放震动
        VibrateHelp.stop();
    }

    /**
     * 获取子view的marginLayoutParams
     */
    private void getChildenLayoutParams() {
        View childAt = ll_parent.getChildAt(0);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
        mLeftMargin = marginLayoutParams.leftMargin;
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImp());
    }


    /**
     * 设置moveTextView的基础参数
     */
    private void setMoveTextView() {

        moveText = new TextView(getActivity());
        moveText.setText(21 + "");
        moveText.setTextColor(0xff52d666);
        moveText.setTextSize(16);

        layoutParams = new ViewGroup.LayoutParams(screenWidth, 50);
        //textMoveLayout = (TextMoveLayout) findViewById(R.id.tvWendu);
        textMoveLayout.addView(moveText, layoutParams);
        moveText.layout(5, 20, screenWidth, 80);
    }


    /**
     * 设置seekbar的基础参数
     */
    public void setSeekBarValues() {

        //mTvMax.setText("500");
        seekBar.setEnabled(true);
        seekBar.setMax(9);
        //seekBar.setProgress(11);
    }

    @Override
    public void onRefresh() {
        //refreshLayout.setRefreshing(false);
        loadFacility("315");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("zp", "onItemClick: ");
        DeviceListResponseEntity.Device item = (DeviceListResponseEntity.Device) ((FacilityAdapter) listView.getAdapter()).getItem(position);
        String templateId = item.templateId;
        if (templateId.contains("净水机")) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ZhiYinShuiActivity.class);
            intent.putExtra("facility", item);
            getActivity().startActivity(intent);
        }
    }


    /**
     * seekbar滑动监听
     */
    private class OnSeekBarChangeListenerImp implements
            SeekBar.OnSeekBarChangeListener {

        // 触发操作，拖动
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setMoveTextLayout();
        }

        // 开始拖动时候触发的操作
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        // 停止拖动时候
        public void onStopTrackingTouch(SeekBar seekBar) {
            double d = seekBar.getProgress() + wenduNum;
            HashMap<String, Object> hs = new HashMap<>();
            if (flag) {
                hs.put("temp_heat", "c" + d);
                sendAction(connection4, "海林温控器1.测试", "setTempHeat", hs);
            } else {
                hs.put("temp_cool", "c" + d);
                sendAction(connection4, "海林温控器1.测试", "setTempCool", hs);
            }
        }
    }

    /**
     * seekbar滑动过程中改变moveTextView的位置
     */
    private void setMoveTextLayout() {
        if (mPaint == null) {
            mPaint = new TextPaint();
        }

        float measureText = mPaint.measureText(moveText.getText().toString().trim());
        Rect bounds = seekBar.getProgressDrawable().getBounds();
        int xFloat = (int) (bounds.width() * seekBar.getProgress() / seekBar.getMax() - measureText / 2 + DensityUtil.px2dip(getActivity(), mLeftMargin));
        moveText.layout(xFloat, 20, screenWidth, 80);
        moveText.setText(seekBar.getProgress() + 21 + "℃");
    }

    private void loadFacility(String groupId) {
        //Log.d("zp", "iaaaaaaa11111111111");
        FacilityAPI facilityAPI = Http.getInstance().create(FacilityAPI.class);
        Call<TypeDeviceListResponseEntity> typeDeviceListResponseEntityCall = facilityAPI.deviceList(String.valueOf("40"), groupId);
        typeDeviceListResponseEntityCall.enqueue(new Callback<TypeDeviceListResponseEntity>() {
            @Override
            public void onResponse(Call<TypeDeviceListResponseEntity> call, Response<TypeDeviceListResponseEntity> response) {

                TypeDeviceListResponseEntity body = response.body();
                if (body == null) return;
                List<DeviceListResponseEntity.Device> data = body.data;
                if (data.size() == 0) {
                    showToast("此分组下无设备");
                    return;
                }
                Log.i("zp", "onResponse: " + data.toString());
                adapter = new FacilityAdapter(getActivity(), data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(ZhiNengshenghuoFragment.this);

            }

            @Override
            public void onFailure(Call<TypeDeviceListResponseEntity> call, Throwable t) {
//                Logger.e(t.getMessage());
//                closeDialog();
            }
        });
    }

    private void sendAction(WebSocketConnection connection, String thingId, String serviceId, HashMap<String, Object> param) {
        FacilityOperate.Other operate = new FacilityOperate.Other();
        // Log.i("dyy", device.thingId);
        operate.thingId = thingId;
        operate.serviceId = serviceId;
        operate.param = param;
        String s = new Gson().toJson(operate);
        if (connection.isConnected()) {
            connection.sendRawTextMessage(s.getBytes());
            //Logger.i("发送内容".concat(s));
        } else {
            //Toast.makeText(WaterDispenserActivity.this, "未连接到设备,请返回重试", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSocketConnect(final WebSocketConnection connection, String ip, final int i) {
        ip = "ws://".concat(ip).concat("/IotHarborWebsocket");
        ip = ip.replace("8080", "8999");
        final String wsuri = ip;

        try {
            connection.connect(wsuri, new WebSocketConnectionHandler() {

                //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onOpen() {
                    if (getActivity().isDestroyed() || getActivity().isFinishing()) {
                        return;
                    }
                    //Logger.d("Status: Connected to " + wsuri);
                    switch (i) {
                        case 1:
                            Toast.makeText(getActivity(), "已连接窗帘", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getActivity(), "已连接空调", Toast.LENGTH_SHORT).show();
                            subscribe();
                            break;
                        case 3:
                            Toast.makeText(getActivity(), "已连接三键", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }

                //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onTextMessage(String payload) {
                    Log.i("zp", "payload:|==" + payload + "==|");
                    /**
                     *判断设备是否离线
                     */
                    if (payload.contains("thing not online")) {
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
                    if (payload.equals("subscribe success") || payload.equals("already subscribe")) {
                        showToast("订阅成功");
                        Log.i("zp", "订阅成功");
                        return;
                    }
                    if (i == 4) {
                        try {
                            Map<String, Object> map = new Gson().fromJson(payload, new TypeToken<Map<String, Object>>() {
                            }.getType());
                            if (map != null && map.size() > 0) {
                                String s = (String) map.get("dis_temp");
                                if (!s.equals("")) {
                                    String wd = s.substring(1, s.length());
                                    tv_snwd.setText("室内温度：" + wd + "℃");
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }
                    if (i == 3) {
                        try {
                            Map<String, Object> map = new Gson().fromJson(payload, new TypeToken<Map<String, Object>>() {
                            }.getType());
                            if (map != null && map.size() > 0) {
                                Object data = map.get("data");
                                if (data instanceof Map) {
                                    Map<String, String> data1 = (Map<String, String>) data;
                                    for (int i = 1; i < i + 1; i++) {
                                        if (data1.get(String.valueOf(i)) == "ON") {
                                            setSwitchStatus(i, true);
                                        }
                                        if (data1.get(String.valueOf(i)) == "OFF") {
                                            setSwitchStatus(i, false);
                                        }
                                    }
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    } else if (i == 2) {
                        try {
                            Map<String, Object> map = new Gson().fromJson(payload, new TypeToken<Map<String, Object>>() {
                            }.getType());
                            if (map != null && map.size() > 0) {
                                Object data = map.get("data");
                                if (data instanceof Map) {
                                    Map<String, String> data1 = (Map<String, String>) data;
                                    for (int i = 1; i < i + 1; i++) {
                                        if (data1.get(String.valueOf(i)) == "ON") {
                                            setSwitchStatus1(i, true);
                                        }
                                        if (data1.get(String.valueOf(i)) == "OFF") {
                                            setSwitchStatus1(i, false);
                                        }
                                    }
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
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

    private void setSwitchStatus(int switchNo, boolean status) {
        switch (switchNo) {
            case 1:
                if (status) {
                    iv_sanjian1.setImageResource(R.mipmap.zn_off);
                    switch1 = true;
                } else {
                    iv_sanjian1.setImageResource(R.mipmap.zn_on);
                    switch1 = false;
                }
                break;
            case 2:
                if (status) {
                    iv_sanjian2.setImageResource(R.mipmap.zn_off);
                    switch2 = true;
                } else {
                    iv_sanjian2.setImageResource(R.mipmap.zn_on);
                    switch2 = false;
                }
                break;
            case 3:
                if (status) {
                    iv_sanjian3.setImageResource(R.mipmap.zn_off);
                    switch3 = true;
                } else {
                    iv_sanjian3.setImageResource(R.mipmap.zn_on);
                    switch3 = false;
                }
                break;
            default:
                break;
        }
    }

    private void setSwitchStatus1(int switchNo, boolean status) {
        switch (switchNo) {
            case 1:
                if (status) {
                    iv_erjian1.setImageResource(R.mipmap.zn_off);
                    switch4 = true;
                } else {
                    iv_erjian1.setImageResource(R.mipmap.zn_on);
                    switch4 = false;
                }
                break;
            case 2:
                if (status) {
                    iv_erjian2.setImageResource(R.mipmap.zn_off);
                    switch5 = true;
                } else {
                    iv_erjian2.setImageResource(R.mipmap.zn_on);
                    switch5 = false;
                }
                break;
            default:
                break;
        }
    }

    private void unSubscribe() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("subscribe", "off");
        sendAction(connection4, "海林温控器1.测试", "trapData", param);
    }

    private void subscribe() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("subscribe", "on");
        sendAction(connection4, "海林温控器1.测试", "trapData", param);
    }

}
