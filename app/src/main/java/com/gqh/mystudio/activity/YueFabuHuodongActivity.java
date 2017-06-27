package com.gqh.mystudio.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.FabuPhotoGrideviewAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FileResult;
import com.gqh.mystudio.utill.BitmapUtil;
import com.gqh.mystudio.utill.Const;
import com.gqh.mystudio.view.PickerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class YueFabuHuodongActivity extends BaseHttpActivity {

    private List<Bitmap> mList=new ArrayList<Bitmap>();
    private FabuPhotoGrideviewAdapter adapter;


    @ViewInject(R.id.fen_pv)
    private PickerView fen_pv;
    @ViewInject(R.id.shi_pv)
    private PickerView shi_pv;
    @ViewInject(R.id.nian_pv)
    private PickerView nian_pv;
    @ViewInject(R.id.yue_pv)
    private PickerView yue_pv;
    @ViewInject(R.id.ri_pv)
    private PickerView ri_pv;
    @ViewInject(R.id.datePicker)
    private LinearLayout datePicker;

    @ViewInject(R.id.sv)
    private ScrollView sv;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.et_address)
    private EditText et_address;
    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.gridview)
    private GridView gv;
    @ViewInject(R.id.et_text)
    private EditText et_text;
    private String text;
    private String address;
    private String name;
    private int uId;
    private int month;
    private int day;
    private String str_mon;
    private String str_day;
    private int data_year;
    private int data_month;
    private static boolean isLun1=false;
    private static boolean isLun2=false;
    private static boolean isLun3=false;
    private static boolean isLun4=false;
    private static boolean isLun5=false;
    private String data_day;
    private int hour;
    private String str_hour;
    private String data_hour;
    private String data_fen;
    private String str_fen;
    private int fen;

    @OnClick({R.id.image_fanhui,R.id.bt_photo,R.id.bt_faqi,R.id.tv_time,R.id.quxiao,R.id.wancheng})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.tv_time:
                    datePicker.setVisibility(View.VISIBLE);
                    break;
                case R.id.quxiao:
                    datePicker.setVisibility(View.GONE);
                    break;
                case R.id.wancheng:
                    String time;
                    String data_month1 = data_month < 10 ? "0" + data_month : data_month + "";
                    String montn1=month<10 ? "0"+month : ""+month;
                    String day1=day<10?"0"+day:day+"";
                    String hour1=hour<10?"0"+hour:hour+"";
                    String fen1=fen <10?"0"+fen :fen +"";
                    MyLog.i("day========",day+"");
                    MyLog.i("day========",day1);
                    if (isLun1){
                        if (isLun2){
                            if (isLun3){
                                if (isLun4){
                                    if (isLun5){
                                        time = data_year + "-" + data_month1 + "-" + data_day+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + data_month1 + "-" + data_day+"  "+data_hour+":"+fen1;
                                    }
                                }else {
                                    if (isLun5){
                                        time = data_year + "-" + data_month1 + "-" + data_day+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + data_month1 + "-" + data_day+"  "+hour1+":"+fen1;
                                    }
                                }
                            }else {
                                if (isLun4){
                                    if (isLun5){
                                        time = data_year + "-" + data_month1 + "-" + day1+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + data_month1 + "-" + day1+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = data_year + "-" + data_month1 + "-" + day1+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + data_month1 + "-" + day1+"  "+hour1+":"+fen1;
                                    }
                                }
                            }
                        }else {
                            if (isLun3){
                                if (isLun4){
                                    if (isLun5){
                                        time = data_year + "-" + montn1 + "-" + data_day+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + montn1 + "-" + data_day+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = data_year + "-" + montn1 + "-" + data_day+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + montn1 + "-" + data_day+"  "+hour1+":"+fen1;
                                    }

                                }
                            }else {
                                if (isLun4){
                                    if (isLun5){
                                        time = data_year + "-" + montn1 + "-" + day1+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + montn1 + "-" + day1+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = data_year + "-" + montn1 + "-" + day1+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = data_year + "-" + montn1 + "-" + day1+"  "+hour1+":"+fen1;
                                    }

                                }
                            }
                        }
                    }else{
                        if (isLun2){
                            if (isLun3){
                                if (isLun4){
                                    if (isLun5){
                                        time = year + "/" + data_month1 + "/" + data_day+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = year + "/" + data_month1 + "/" + data_day+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = year + "/" + data_month1 + "/" + data_day+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = year + "/" + data_month1 + "/" + data_day+"  "+hour1+":"+fen1;
                                    }

                                }
                            }else {
                                if (isLun4){
                                    if (isLun5){
                                        time = year + "/" + data_month1 + "/" + day1+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = year + "/" + data_month1 + "/" + day1+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = year + "/" + data_month1 + "/" + day1+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = year + "/" + data_month1 + "/" + day1+"  "+hour1+":"+fen1;
                                    }

                                }
                            }
                        }else {
                            if (isLun3){
                                if (isLun4){
                                    if (isLun5){
                                        time = year + "/" + montn1 + "/" + data_day+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = year + "/" + montn1 + "/" + data_day+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = year + "/" + montn1 + "/" + data_day+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = year + "/" + montn1 + "/" + data_day+"  "+hour1+":"+fen1;
                                    }
                                }
                            }else {
                                if (isLun4){
                                    if (isLun5){
                                        time = year + "/" + montn1 + "/" + day1+"  "+data_hour+":"+data_fen;
                                    }else {
                                        time = year + "/" + montn1 + "/" + day1+"  "+data_hour+":"+fen1;
                                    }

                                }else {
                                    if (isLun5){
                                        time = year + "/" + montn1 + "/" + day1+"  "+hour1+":"+data_fen;
                                    }else {
                                        time = year + "/" + montn1 + "/" + day1+"  "+hour1+":"+fen1;
                                    }
                                }
                            }
                        }
                    }

                    tv_time.setText(time);
                    datePicker.setVisibility(View.GONE);
                    break;
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_photo:
                    //选取照片
                    showDialog();
                    break;

                case R.id.bt_faqi:
                    //非空验证
                    name= et_name.getText().toString();
                    address = et_address.getText().toString();
                    String time1 = tv_time.getText().toString();
                    text = et_text.getText().toString();

                    if (StringUtil.isEmpty(address)){
                        showToast("请填写活动地点");
                        break;
                    }else if (StringUtil.isEmpty(time1)){
                        showToast("请填写活动时间");
                        break;
                    }else if(StringUtil.isEmpty(text)){
                        showToast("请简单描述活动介绍");
                        break;
                    }else if (StringUtil.isEmpty(name)){
                        showToast("请填写活动名称");
                        break;
                    }

                    if (mList.size()==0){
                        showToast("请选取活动照片");
                        break;
                    }else {

                        MyLog.i("mlist===========",mList.size()+"");
                        if (!ivUrl.equals("")){
                            //请求 发起
                            RequestParams params=new RequestParams();
                            params.put("Area",address);
                            params.put("beginDate", time1);
                            params.put("picID", ivUrl);
                            params.put("Intro",text);
                            params.put("UID",uId);
                            params.put("Name",name);
                            postDialog(Constant.YUE_FABU, params,100);
                        }
                    }
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yue_fabu_huodong;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        sv.smoothScrollTo(0, 0);
        initDatePicker();
        String name = BamsApplication.getInstance().getUser().getUNickName();
        uId= BamsApplication.getInstance().getUser().getUID();
        tv_name.setText(name);

        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消GridView中Item选中时默认的背景色

    }

    String ivUrl=null;
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        MyLog.i("jsonStr" + "====", jsonStr);
        try{

            if (requestCode==0){
                FileResult result=GsonUtils.getSingleBean(jsonStr, FileResult.class);
                if (result.isSuccess()){
                    String uri=result.data.getUrl();
                    if (ivUrl==null){
                        ivUrl=uri;
                    }else {
                        ivUrl=ivUrl+";"+uri;
                    }
                }
            }
            if (requestCode==100){
                JSONObject object=new JSONObject(jsonStr);
                if (object.getString("code").equals("0")){
                    //提交成功
                    showToast("活动发布成功");
                    finish();
                }

            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.PHOTO_REQUEST_TAKEPHOTO:
                BitmapUtil.startPhotoZoom(this, Uri.fromFile(tempFile), 350);
                break;

            case Constant.PHOTO_REQUEST_GALLERY:
                if (data != null)
                    BitmapUtil.startPhotoZoom(this,data.getData(), 350);
                break;

            case Constant.PHOTO_REQUEST_CUT:
                if (data != null)
//                    setPicToView(data);
                    try {
                        //这个方法是根据Uri获取Bitmap图片的静态方法
//                    Bitmap image = getBitmapFromUri(data.getData());
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            Bitmap image = bundle.getParcelable("data");
                            if (image != null) {
                                File file = BitmapUtil.saveBitmapFile(image);
                                //照片上传服务器
                                RequestParams params=new RequestParams();
                                params.put("file", file);
                                postDialog(Constant.UP_LOAD, params, 0);

                                mList.add(image);
                                //更新UI
                                if (adapter==null){
                                    adapter=new FabuPhotoGrideviewAdapter(mList,this,Constant.STYLE_ADAPTER_NO);
                                    gv.setAdapter(adapter);
                                }else {
                                    adapter.notifyDataSetChanged();
                                }

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private  ArrayList<String> days;
    private  int year;

    private void initDatePicker(){
        //时间选择器
        List<String> years = new ArrayList<String>();
        List<String> months = new ArrayList<String>();
        List<String> hours = new ArrayList<String>();
        List<String> fens = new ArrayList<String>();
        days = new ArrayList<String>();
        //当前时间
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        fen=c.get(Calendar.MINUTE);
        MyLog.i("day2========",day+"");


        str_mon = month < 10 ? "0" + month : month + "";
        str_day=   day < 10 ? "0" + day : day + "";
        str_hour=   hour < 10 ? "0" + hour : hour + "";
        str_fen=   fen < 10 ? "0" + fen : fen + "";

//        tv_time.setText(year + "-" + str_mon + "-" + str_day + "  " + str_hour);


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


        for (int i = 1; i < 25; i++){
            hours.add(i < 10 ? "0" + i : "" + i);
        }
        shi_pv.setData(hours);
        shi_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                //                showToast("选择了 " + text + " 时");
                data_hour =text;
                isLun4 = true;
            }
        });
        shi_pv.setSelected(str_hour);

        for (int i = 0; i < 60; i++){
            hours.add(i < 10 ? "0" + i : "" + i);
        }
        fen_pv.setData(hours);
        fen_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                //                showToast("选择了 " + text + " 时");
                data_fen =text;
                isLun5 = true;
            }
        });
        fen_pv.setSelected(str_hour);
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

   /* public  Bitmap getBitmapFromUri(Uri uri) {
        try{
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            return bitmap;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),BitmapUtil.getPhotoFileName());
    //提示对话框方法
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选取图片")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        // 调用系统的拍照功能
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 指定调用相机拍照后照片的储存路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        startActivityForResult(intent, Constant.PHOTO_REQUEST_TAKEPHOTO);
                    }
                })
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                        startActivityForResult(intent, Constant.PHOTO_REQUEST_GALLERY);
                    }
                }).show();
    }
}
