package com.gqh.mystudio.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.view.PickerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class Guanjia_XinzengFuwuActivity extends BaseHttpActivity {

    @ViewInject(R.id.tv_start)
    private TextView mStart;
    @ViewInject(R.id.tv_stop)
    private TextView mStop;
    @ViewInject(R.id.ll_time_picker)
    private LinearLayout ll_time_picker;
    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.tv_zhi)
    private TextView tv_zhi;
    @ViewInject(R.id.et_yaoqiu)
    private EditText et_yaoqiu;
    @ViewInject(R.id.tv_home)
    private TextView tv_room;
    @ViewInject(R.id.tv_style)
    private TextView tv_style;
    @ViewInject(R.id.image_fanhui)
    private ImageView iv_back;
    @ViewInject(R.id.tv_time_start)
    private TextView tv_start;
    @ViewInject(R.id.tv_time_stop)
    private TextView tv_stop;
    @ViewInject(R.id.bt_queren)
    private Button bt_ok;

    @ViewInject(R.id.nian_pv)
    private PickerView nian_pv;
    @ViewInject(R.id.yue_pv)
    private PickerView yue_pv;
    @ViewInject(R.id.ri_pv)
    private PickerView ri_pv;
    @ViewInject(R.id.datePicker)
    private LinearLayout datePicker;
    @ViewInject(R.id.quxiao)
    private TextView quxiao;
    @ViewInject(R.id.wancheng)
    private TextView wancheng;

    private int room;
    private String style;
    private String yaoqiu;
    private String start;
    private String stop;
    private User user;


    private int data_year;
    private int data_month;
    private List<String> days;
    private String data_day;
    private static boolean isStart;
    private static boolean isLun1=false;
    private static boolean isLun2=false;
    private static boolean isLun3=false;
    private int day;
    private int month;
    private int year;
    private String str_mon;
    private String str_day;
    private Integer mHour;
    private Integer mMinute;

    @OnClick({R.id.image_fanhui,R.id.tv_time_start,R.id.tv_time_stop,R.id.bt_queren,R.id.quxiao,R.id.wancheng,R.id.tv_start,R.id.tv_stop})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.tv_start:
                    //时间段
                    setTimerPicker(mStart,true);
                    break;
                case R.id.tv_stop:
                    setTimerPicker(mStop,false);
                    break;
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.tv_time_start:
                    isStart=true;
                    datePicker.setVisibility(View.VISIBLE);
                    break;
                case R.id.tv_time_stop:
                    isStart=false;
                    datePicker.setVisibility(View.VISIBLE);
                    break;
                case R.id.bt_queren:

                    if (!isCurrentInTimeScope(finalStartHour,finalStartMinute)){
                        showToast("客服服务时间段为10:30~18:00,请重新选择时间段");
                        break;
                    }
                    if (!isCurrentInTimeScope(finalEndHour,finalEndMinute)){
                        showToast("客服服务时间段为10:30~18:00,请重新选择时间段");
                        break;
                    }
                    yaoqiu=et_yaoqiu.getText().toString();
                    start=tv_start.getText().toString();
                    stop=tv_stop.getText().toString();

                    if(StringUtil.isEmpty(yaoqiu)){
                        showToast("请填写服务要求");
                        break;
                    }

                    //发生提交请求
                    RequestParams params=new RequestParams();
                    params.put("CustId",user.getCustID());
                    params.put("phone",user.getULoginID());
                    if (style.equals("室内清洁")){
                        params.put("orderType","04_001_2");
                    }else if (style.equals("室内维修")){
                        params.put("orderType","04_001_3");
                    }else {
                        params.put("orderType","04_001_1");
                    }
                    params.put("ApartmentID",user.getApartmentID());
                    params.put("RoomNo",room);
                    if (style.equals("建议投诉")){
                        params.put("workTime",tv_time.getText().toString());
                    }else {

                        params.put("workTime",start+" "+finalStartHour+":"+finalStartMinute+","+stop+" "+finalEndHour+":"+finalEndMinute);
                    }
                    params.put("orderdesc",yaoqiu);
                    postDialog(Constant.NEW_FUWU,params,false);
                    break;


                case R.id.quxiao:
                    datePicker.setVisibility(View.GONE);
                    break;
                case R.id.wancheng:
                    String time;
                    String data_month1 = data_month < 10 ? "0" + data_month : data_month + "";
                    String montn1=month<10 ? "0"+month : ""+month;
                    String day1=day<10?"0"+day:day+"";
                    if (isLun1){
                        if (isLun2){
                            if (isLun3){
                                time = data_year + "/" + data_month1 + "/" + data_day;
                            }else {
                                time = data_year + "/" + data_month1 + "/" + day1;
                            }
                        }else {
                            if (isLun3){
                                time = data_year + "/" + montn1 + "/" + data_day;
                            }else {
                                time = data_year + "/" + montn1 + "/" + day1;
                            }
                        }
                    }else{
                        if (isLun2){
                            if (isLun3){
                                time = year + "/" + data_month1 + "/" + data_day;
                            }else {
                                time = year + "/" + data_month1 + "/" + day1;
                            }
                        }else {
                            if (isLun3){
                                time = year + "/" + montn1 + "/" + data_day;
                            }else {
                                time = year + "/" + montn1 + "/" + day1;
                            }
                        }
                    }

                    if(isStart){
                        tv_start.setText(time);
                    }else {
                        tv_stop.setText(time);
                    }
                    datePicker.setVisibility(View.GONE);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            MyLog.i("json==",jsonStr);
            JSONObject obj=new JSONObject(jsonStr);
            String code = obj.getString("code");
            if(code.equals("0")){

                showToast("提交成功");
                finish();
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_guanjia_xinzeng;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);
        user = BamsApplication.getInstance().getUser();
        room=user.getRoomID();
        style=getIntent().getStringExtra("style");
        tv_room.setText(user.getRoomNo());
        tv_style.setText(style);


        if(style.equals("建议投诉")){
            tv_zhi.setVisibility(View.GONE);
            tv_stop.setVisibility(View.GONE);
            tv_start.setVisibility(View.GONE);
            tv_time.setVisibility(View.VISIBLE);
            ll_time_picker.setVisibility(View.GONE);
        }


        //时间选择器
        List<String> years = new ArrayList<String>();
        List<String> months = new ArrayList<String>();
        days = new ArrayList<String>();
        //当前时间
        Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);

        str_mon = month < 10 ? "0" + month : month + "";
        str_day=   day < 10 ? "0" + day : day + "";

        mStart.setText(new StringBuilder().append(c.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(c.get(Calendar.MINUTE) < 10 ? "0" + c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE)));

        mStop.setText(new StringBuilder().append(c.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(c.get(Calendar.MINUTE) < 10 ? "0" + c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE)));

        tv_start.setText(year+"/"+str_mon+"/"+str_day);
        tv_stop.setText(year+"/"+str_mon+"/"+str_day);
        tv_time.setText(year+"/"+str_mon+"/"+str_day);

        for (int i = 10; i > 0; i--){
            years.add("" +(year-i));
        }
        for (int i = 0; i < 10; i++){
            years.add("" + (year + i));
        }
        nian_pv.setData(years);
        nian_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                showToast("选择了 " + text + " 年");
                isLun1 = true;
                data_year = Integer.parseInt(text);
                erYueDays(data_month, data_year);
            }
        });
        nian_pv.setSelected(year + " ");



        for (int i = 1; i < 13; i++){
            months.add(i < 10 ? "0" + i : "" + i);
        }
        yue_pv.setData(months);
        yue_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                showToast("选择了 " + text + " 月");
                data_month = Integer.parseInt(text);
                erYueDays(data_month, data_year);
                isLun2 = true;
            }
        });

        yue_pv.setSelected(str_mon);


        //默认
        erYueDays(month, year);
        ri_pv.setData(days);
        ri_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
//                showToast("选择了 " + text + " 月");
                data_day = text;
                isLun3 = true;
            }
        });

        ri_pv.setSelected(str_day);

    }


    public void erYueDays(int moth,int ye){
        switch (moth){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                //31天
                days.clear();
                for (int i = 1; i < 32; i++){
                    days.add(i < 10 ? "0" + i : "" + i);
                }

                break;

            //对于2月份需要判断是否为闰年
            case 2:
                if ((ye % 4 == 0 && ye % 100 != 0) || (ye % 400 == 0)) {
                    //闰月  29天
                    days.clear();
                    for (int i = 1; i < 30; i++){
                        days.add(i < 10 ? "0" + i : "" + i);
                    }
                    break;
                } else {
                    // 28天
                    days.clear();
                    for (int i = 1; i < 29; i++){
                        days.add(i < 10 ? "0" + i : "" + i);
                    }
                    break;
                }

            case 4:
            case 6:
            case 9:
            case 11:
                //30天
                days.clear();
                for (int i = 1; i < 31; i++){
                    days.add(i < 10 ? "0" + i : "" + i);
                }
                break;
        }
    }


    private int finalStartHour;
    private int finalStartMinute;
    private int finalEndHour;
    private int finalEndMinute;
    private void setTimerPicker(final TextView mTime, final boolean isStart){
        //自定义控件
        Calendar calendar = Calendar.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.time_dialog, null);
        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        //初始化时间
        calendar.setTimeInMillis(System.currentTimeMillis());
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        //设置time布局
        builder.setView(view);
        builder.setTitle("选择时间");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mHour = timePicker.getCurrentHour();
                mMinute = timePicker.getCurrentMinute();
                //时间小于10的数字 前面补0 如01:12:00
                mTime.setText(new StringBuilder().append(mHour < 10 ? "0" + mHour : mHour).append(":")
                        .append(mMinute < 10 ? "0" + mMinute : mMinute));
                dialog.cancel();
                if (isStart){
                    finalStartHour=mHour;
                    finalStartMinute=mMinute;
                }else {
                    finalEndHour=mHour;
                    finalEndMinute=mMinute;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    /**
     * 判断当前系统时间是否在指定时间的范围内
     *
     * @return true表示在范围内，否则false
     */
    public static boolean isCurrentInTimeScope(int nHour, int nMinute) {
        boolean result = false;
        final long aDayInMillis = 1000 * 60 * 60 * 24;
        final long currentTimeMillis = System.currentTimeMillis();

        Time now = new Time();
        now.set(currentTimeMillis);
        now.hour=nHour;
        now.minute=nMinute;

        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = 10;
        startTime.minute = 30;

        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = 18;
        endTime.minute = 0;

        if (!startTime.before(endTime)) {
            // 跨天的特殊情况（比如22:00-8:00）
            startTime.set(startTime.toMillis(true) - aDayInMillis);
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
            Time startTimeInThisDay = new Time();
            startTimeInThisDay.set(startTime.toMillis(true) + aDayInMillis);
            if (!now.before(startTimeInThisDay)) {
                result = true;
            }
        } else {
            // 普通情况(比如 8:00 - 14:00)
            result = !now.before(startTime) && !now.after(endTime); // startTime <= now <= endTime
        }
        return result;
    }
}
