package com.gqh.mystudio.fragment;


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.MainActivity;
import com.gqh.mystudio.activity.WoYaoZuXinpaiActivity;
import com.gqh.mystudio.activity.XinpaiBiActivity;
import com.gqh.mystudio.activity.XinpaiGuanjiaActivity;
import com.gqh.mystudio.activity.XinpaiLinkActivity;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.NumberResult;
import com.gqh.mystudio.utill.VibrateHelp;
import com.gqh.mystudio.view.SlidButton;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cn.myapp.base.BaseHttpFragment;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: fragment 首页
 */

public class ShouYeFragment extends BaseHttpFragment implements View.OnClickListener, SlidButton.OnChangedListener{
    /** 按钮震动时间 */
    private final int VIBRATE_TIME = 100;

//    private SoundPool sp;//声明一个SoundPool
//    private int music;//定义一个整型用load（）；来设置suondID


    private static final int HANDELR_TEXT=10000;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HANDELR_TEXT:
                    wiperSwitch.setLockOff();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private MainActivity activity;

    private LinearLayout rl_zu;
    private RelativeLayout rl_guanjia;
    private LinearLayout rl_zuoyou;
    private LinearLayout rl_xinpaibi;
    private LinearLayout ll_link;
    private String userStyle;
    private TextView tv_jiaobiao;
    private LinearLayout ll_apartmentDoor;
    private SlidButton wiperSwitch;

    private boolean isFrist=true;
    private User mUser;

    public void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.rl_guanjia:
                    //新派币
                    if (userStyle.equals(Constant.STYLE_FANGKE)){
                        showToast("入住新派公寓，方可点击查看");
                    }else {
                        toActivity(XinpaiGuanjiaActivity.class);
                    }
                    break;


                case R.id.rl_zuoyou:
                    if (userStyle.equals(Constant.STYLE_FANGKE)){
                        showToast("入住新派公寓，方可点击查看");
                    }else {
                        activity.toStartFragment(0);
                    }
                    break;


                case R.id.rl_xinpaibi:
                    //新派币
                    if (userStyle.equals(Constant.STYLE_FANGKE)){
                        showToast("入住新派公寓，方可点击查看");
                    }else {
                        toActivity(XinpaiBiActivity.class);
                    }
                    break;


                case R.id.ll_link:
                    //link
                    toActivity(XinpaiLinkActivity.class);
                    break;


                case R.id.rl_zu:
                    Intent intent=new Intent(getActivity(),WoYaoZuXinpaiActivity.class);
                    String url1="http://www.cypalife.com/rent/leasing_new.html";
                    intent.putExtra("URL",url1);
                    toActivity(intent);
                    break;
                case R.id.ll_apartmentDoor:
                    //公寓门
                    if (!userStyle.equals(Constant.STYLE_GONGMIN)){
                        showToast("入住新派公寓，方可点击开门");
                    }else {
                        openDoor(DOOR_TYPE_GONGYUMEN,mUser.getApartmentID());
                    }
                    break;

            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected void onSuccess(int requestCode, String jsonStr) throws Exception {

        try{
            if (requestCode==1){
                //新派管家消息数
//                {"message":"","data":{"number":2},"code":"0"}
                NumberResult result=GsonUtils.getSingleBean(jsonStr, NumberResult.class);
                if (result.isSuccess()){
                    int num = result.data.getNumber();
                    tv_jiaobiao.setText(num+"");
                    if (num!=0){
                        tv_jiaobiao.setVisibility(View.VISIBLE);
                    }
                }
            }

            if (requestCode==10){
                //{"message":"新建开门记录成功。","code":"0"}
                JSONObject object=new JSONObject(jsonStr);
                String code = object.getString("code");
                if (code.equals("0")){
                    showToast("已开门");
                    //启动震动，并持续指定的时间
                    VibrateHelp.vSimple(context, VIBRATE_TIME);
//                    sp.play(music, 1, 1, 0, 0, 1);
                }else {
                    showToast(object.getString("message"));
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        if (!userStyle.equals(Constant.STYLE_FANGKE)){
            RequestParams params1=new RequestParams();
            params1.put("CustId",BamsApplication.getInstance().getUser().getCustID());
            postDialog(Constant.SHOUYE_XIAOXI,params1,1,false);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFrist){
            isFrist=false;
        }else {
            if (!userStyle.equals(Constant.STYLE_FANGKE)){
                RequestParams params1=new RequestParams();
                params1.put("CustId",BamsApplication.getInstance().getUser().getCustID());
                postDialog(Constant.SHOUYE_XIAOXI,params1,1,false);
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shou_ye_fragment;
    }

    @Override
    protected void initView(View contentView) {
//        sp= new SoundPool(10,AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
//        music = sp.load(context, R.raw.opendoor, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

        userStyle=BamsApplication.getInstance().getUserStyle();
        mUser= BamsApplication.getInstance().getUser();

        //初始化控件
        rl_zu= (LinearLayout) contentView.findViewById(R.id.rl_zu);
        rl_guanjia= (RelativeLayout) contentView.findViewById(R.id.rl_guanjia);
        rl_xinpaibi= (LinearLayout) contentView.findViewById(R.id.rl_xinpaibi);
        rl_zuoyou= (LinearLayout) contentView.findViewById(R.id.rl_zuoyou);
        ll_link= (LinearLayout) contentView.findViewById(R.id.ll_link);
        ll_apartmentDoor= (LinearLayout) contentView.findViewById(R.id.ll_apartmentDoor);
        tv_jiaobiao=(TextView)contentView.findViewById(R.id.shou_jiaobiao);

        activity=(MainActivity)getActivity();


        //实例化WiperSwitch 滑动开关
        wiperSwitch = (SlidButton)contentView.findViewById(R.id.wiperSwitch1);
        //设置监听
        wiperSwitch.SetOnChangedListener(this);


        rl_zu.setOnClickListener(this);
        rl_guanjia.setOnClickListener(this);
        rl_zuoyou.setOnClickListener( this);
        rl_xinpaibi.setOnClickListener(this);
        ll_link.setOnClickListener(this);
        ll_apartmentDoor.setOnClickListener(this);
    }

    private static final int DOOR_TYPE_FANGJIANMEN=2;
    private static final int DOOR_TYPE_GONGYUMEN=1;

    //滑动开关监听
    @Override
    public void OnChanged(boolean checkState) {
        // TODO Auto-generated method stub
        if (checkState){
            //房价门
            if (!userStyle.equals(Constant.STYLE_GONGMIN)){
                showToast("入住新派公寓，方可滑动开门");

            }else {
                //处理开门
                openDoor(DOOR_TYPE_FANGJIANMEN,mUser.getRoomID());
            }
            try {
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

            }catch (Exception e){
                ExceptionUtil.handleException(e);
            }
        }
    }

    /**
     * 开门
     */
    private void openDoor(int DoorType,int doorId) {
        RequestParams params=new RequestParams();
        params.put("CustID",mUser.getCustID());
        params.put("DoorType",DoorType);
//        params.put("DoorID",doorId);
        params.put("Phone",mUser.getULoginID());
        postDialog(Constant.LIST_UNLOCK_DOOR_OPEN,params,10,false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放震动
        VibrateHelp.stop();
    }
}
