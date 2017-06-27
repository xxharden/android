package com.gqh.mystudio.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.GroupPopupAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FileResult;
import com.gqh.mystudio.utill.BitmapUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.entity.UserResult;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class SetGerenXinActivity extends BaseHttpActivity {

    private List<String>xingzuoList=new ArrayList<String>();

    String[] str={"水瓶座","双鱼座","白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座",
            "天蝎座","射手座","摩羯座"};


    //    旅游，爬山，阅读，电影，上网，舞蹈，音乐，绘画，运动，交友，睡觉，美食，购物，其他
    List<String>listLover=new ArrayList<String>();


    //    @ViewInject(R.id.rl_background)
//    private RelativeLayout rl_background;
    @ViewInject(R.id.iv_photo)
    private ImageCircleView iv_photo;
    @ViewInject(R.id.et_nicheng)
    private EditText et_nicheng;
    @ViewInject(R.id.rb_nan)
    private RadioButton rb_nan;
    @ViewInject(R.id.rb_nv)
    private RadioButton rb_nv;
    @ViewInject(R.id.et_age)
    private EditText et_age;
    @ViewInject(R.id.et_country)
    private EditText et_country;
    @ViewInject(R.id.et_city)
    private EditText et_city;
    @ViewInject(R.id.et_other)
    private EditText et_other;
    @ViewInject(R.id.tv_lover)
    private TextView tv_lover;
    @ViewInject(R.id.tv_xingzuo)
    private TextView tv_xingzuo;
    @ViewInject(R.id.et_qianming)
    private TextView et_qianming;
    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    private String phone;
    private View view;
    private ListView listXingzuoPopu;
    private boolean isChange=false;
    //    private boolean isUpdate=false;
    private String name;
    private String age;
    private String lover;
    private String xingzuo;
    private String qianming;
    private String other;
    private User uSer;
    private String sex;
    private String imageUri;
    private String userStyle;

    private ACache aCache;
    private String isFirst;

    @OnClick({R.id.bt_ok,R.id.tv_lover,R.id.tv_xingzuo,R.id.iv_photo,R.id.image_fanhui})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.iv_photo:
                    //选取照片
                    showDialog();
                    break;
                case R.id.tv_lover:
                    showWindow(tv_lover,listLover);
                    break;
                case R.id.tv_xingzuo:
                    //星座popup
                    showWindow2(tv_xingzuo, xingzuoList);
                    break;
                case R.id.bt_ok:

                    if (isFirst.equals("first")){
                        other=et_other.getText().toString();
                        name=et_nicheng.getText().toString();
                        age=et_age.getText().toString();
                        String country = et_country.getText().toString();
                        String city = et_city.getText().toString();
                        lover=tv_lover.getText().toString();
                        xingzuo=tv_xingzuo.getText().toString();
                        qianming=et_qianming.getText().toString();

                        //非空验证
                        if (!isChange){
                            showToast("请选择自己的头像");
                            break;
                        }

                        if (StringUtil.isEmpty(name)){
                            showToast("请输入昵称");
                            break;
                        }
                        if (StringUtil.isEmpty(age)){
                            showToast("请输入年龄");
                            break;
                        }
                        if (StringUtil.isEmpty(country)|StringUtil.isEmpty(city)){
                            showToast("请填写家乡地址");
                            break;
                        }
                        if (lover.equals("兴趣爱好")){
                            showToast("请选择兴趣爱好");
                            break;
                        }else if (lover.equals("其他")&&StringUtil.isEmpty(other)){
                            showToast("请填写您的兴趣爱好");
                            break;
                        }

                        if (xingzuo.equals("选择星座")){
                            showToast("请选择星座");
                            break;
                        }
                        if (StringUtil.isEmpty(qianming)){
                            showToast("请填写签名");
                            break;
                        }


                        //提交
//                    判断其他（爱好）
                        int uId = uSer.getUID();
                        if (rb_nan.isChecked()){
                            sex="00_011_1";
                        }else if (rb_nv.isChecked()){
                            sex="00_011_2";
                        }
                        String jiaxiang = country + "-" + city;


                        RequestParams params=new RequestParams();
                        params.put("UID",uId);
                        params.put("UNickName",name);
                        params.put("USignaTure",qianming);
                        params.put("USex",sex);
                        params.put("UAge",age);
                        params.put("UConstellation",xingzuo);

                        if (lover.equals("其他")){
                            params.put("UHobby",other);
                        }else {
                            params.put("UHobby",lover);
                        }

                        params.put("UHome",jiaxiang);
                        params.put("UHeadPortrait", imageUri);//头像
                        postDialog(Constant.USER_EDIT, params,2);

                    }else {

                        other=et_other.getText().toString();
                        name=et_nicheng.getText().toString();
                        age=et_age.getText().toString();
                        String country = et_country.getText().toString();
                        String city = et_city.getText().toString();
                        lover=tv_lover.getText().toString();
                        xingzuo=tv_xingzuo.getText().toString();
                        qianming=et_qianming.getText().toString();

                        //提交
//                    判断其他（爱好）
                        int uId = uSer.getUID();
                        if (rb_nan.isChecked()){
                            sex="00_011_1";
                        }else if (rb_nv.isChecked()){
                            sex="00_011_2";
                        }
                        String jiaxiang = country + "-" + city;


                        boolean isUpdate = false;
                        if (uSer!=null){
                            if (imageUri!=null){
                                isUpdate=true;
                            }else {
                                imageUri=uSer.getUHeadPortrait();
                            }
                            if (!name.equals(uSer.getUNickName())){
                                isUpdate=true;
                            }
                            if (!sex.equals(uSer.getUSex())){
                                isUpdate=true;
                            }
                            if (!age.equals(uSer.getUAge()+"")){
                                isUpdate=true;
                            }
                            if (!jiaxiang.equals(uSer.getUHome())){
                                isUpdate=true;
                            }
                            if (!lover.equals(uSer.getUHobby())){
                                isUpdate=true;
                            }
                            if (!xingzuo.equals(uSer.getUConstellation())){
                                isUpdate=true;
                            }
                            if (!qianming.equals(uSer.getUSignaTure())){
                                isUpdate=true;
                            }

                            if (isUpdate){
                                RequestParams params=new RequestParams();
                                params.put("UID",uId);
                                params.put("UNickName",name);
                                params.put("USignaTure",qianming);
                                params.put("USex",sex);
                                params.put("UAge",age);
                                params.put("UConstellation",xingzuo);

                                if (lover.equals("其他")){
                                    params.put("UHobby",other);
                                }else {
                                    params.put("UHobby",lover);
                                }

                                params.put("UHome",jiaxiang);
                                params.put("UHeadPortrait", imageUri);//头像
                                postDialog(Constant.USER_EDIT, params,2);
                            }else {
                                showToast("信息未更新");
                                finish();
                            }
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
        return R.layout.activity_set_geren_xin;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        aCache = ACache.get(context);

//        intent.putExtra("enter","first");
        isFirst=getIntent().getStringExtra("enter");
        uSer=BamsApplication.getInstance().getUser();
        userStyle = uSer.getUSTATE();
        phone=getIntent().getStringExtra("phone");

        if (isFirst.equals("first")){
            back.setVisibility(View.GONE);
        }else {
            setData(uSer);
        }



        for (int i=0;i<str.length;i++){
            xingzuoList.add(str[i]);
        }
//        旅游，爬山，阅读，电影，上网，舞蹈，音乐，绘画，运动，交友，睡觉，美食，购物，其他
        listLover.add("旅游");
        listLover.add("爬山");
        listLover.add("阅读");
        listLover.add("电影");
        listLover.add("上网");
        listLover.add("舞蹈");
        listLover.add("音乐");
        listLover.add("绘画");
        listLover.add("运动");
        listLover.add("交友");
        listLover.add("睡觉");
        listLover.add("美食");
        listLover.add("购物");
        listLover.add("其他");
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {

            MyLog.i("json======",jsonStr);

            if (requestCode == 0) {
                FileResult result = GsonUtils.getSingleBean(jsonStr, FileResult.class);
                if (result.isSuccess()) {
                    imageUri = result.data.getUrl();
                }
            }
            if (requestCode == 2) {
                MyLog.i("json======",jsonStr);
                //提交成功后
                JSONObject obj = new JSONObject(jsonStr);
                String code = obj.getString("code");
                if (code.equals("0")) {

                    showToast("提交成功");
                    Intent intent = new Intent(this, MainActivity.class);
                    if (userStyle.equals("退租")) {//退租公民
                        intent.putExtra("quanxian", Constant.STYLE_GONGMIN_OLD);
                    } else {//在租公民

                        intent.putExtra("quanxian", Constant.STYLE_GONGMIN);
                    }
                    intent.putExtra("phone", phone);
                    toActivity(intent);
                    finish();
                }

                //发送获取公民信息  获取用户信息存全局
                RequestParams params8 = new RequestParams();
                params8.put("phone", phone);
                postDialog(Constant.GET_USER_INFO, params8, 8,false);
            }

            if (requestCode == 8) {
                UserResult result = GsonUtils.getSingleBean(jsonStr, UserResult.class);
                if (result.isSuccess()) {
                    BamsApplication.getInstance().setUser(result.data);//存全局
                    aCache.put("user", result.data);//缓存
                }
            }


           /* if (requestCode == 5) {
                UserResult result = GsonUtils.getSingleBean(jsonStr, UserResult.class);
                if (result.isSuccess()) {
                    uSer = result.data;
                    userStyle = result.data.getUSTATE();

                    if (!isFirst.equals("first")) {
                        setData(result.data);
                    }
                }
            }*/
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        /*if (!isFirst.equals("first")){
            //发送获取公民信息
            RequestParams params5=new RequestParams();
            params5.put("phone",phone);
            postDialog(Constant.GET_USER_INFO,params5,5);
        }*/
    }



    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (popupWindow!=null){
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }

        if (popupWindow2!=null){
            if (popupWindow2.isShowing()) {
                popupWindow2.dismiss();
            }
        }

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
                                postDialog(Constant.UP_LOAD, params, 0,true);

                                //更新UI
                                iv_photo.setImageBitmap(image);
                                isChange=true;
//                                rl_background.setBackgroundDrawable(drawable);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private PopupWindow popupWindow;
    private PopupWindow popupWindow2;

    /**
     * 显示
     *
     * @param parent
     */
    private void showWindow(final TextView parent, final List<String> groups) {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.popup_listview_1, null);

            listXingzuoPopu = (ListView) view.findViewById(R.id.lvGroup);

            GroupPopupAdapter groupAdapter = new GroupPopupAdapter(this, groups);
            listXingzuoPopu.setAdapter(groupAdapter);
            // 创建一个PopuWidow对象
            int xPos = windowManager.getDefaultDisplay().getWidth() / 4 ;
            int yPos = windowManager.getDefaultDisplay().getHeight() / 3 ;
            popupWindow = new PopupWindow(view, xPos, yPos);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());


        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
//        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 ;
//        int yPos = windowManager.getDefaultDisplay().getHeight() / 2 ;
////        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
//
//        popupWindow.showAsDropDown(parent, xPos, yPos);

        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0]+parent.getWidth(), location[1]);

        listXingzuoPopu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                parent.setText(groups.get(position));
                if (groups.get(position).equals("其他")){
                    et_other.setVisibility(View.VISIBLE);
                }else {
                    et_other.setVisibility(View.GONE);
                }

                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void showWindow2(final TextView parent, final List<String> groups) {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        if (popupWindow2 == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.popup_listview_1, null);

            listXingzuoPopu = (ListView) view.findViewById(R.id.lvGroup);

            GroupPopupAdapter groupAdapter = new GroupPopupAdapter(this, groups);
            listXingzuoPopu.setAdapter(groupAdapter);
            // 创建一个PopuWidow对象
            int xPos = windowManager.getDefaultDisplay().getWidth() / 4 ;
            int yPos = windowManager.getDefaultDisplay().getHeight() / 3 ;
            popupWindow2 = new PopupWindow(view, xPos, yPos);
        }

        // 使其聚集
        popupWindow2.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow2.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());


        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
//        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 ;
//        int yPos = windowManager.getDefaultDisplay().getHeight() / 2 ;
////        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
//
//        popupWindow.showAsDropDown(parent, xPos, yPos);

        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        popupWindow2.showAtLocation(parent, Gravity.NO_GRAVITY, location[0]+parent.getWidth(), location[1]);

        listXingzuoPopu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                parent.setText(groups.get(position));


                if (popupWindow2 != null) {
                    popupWindow2.dismiss();
                }
            }
        });
    }


    long temptime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN)) {
            if (isFirst!=null&&isFirst.equals("first")){
                if(System.currentTimeMillis() - temptime >2000) // 2s内再次选择back键有效
                {
                    System.out.println(Toast.LENGTH_LONG);
                    Toast.makeText(this, "请在按一次返回退出", Toast.LENGTH_LONG).show();
                    temptime = System.currentTimeMillis();
                }
                else {
                    finish();
                    BamsApplication.getInstance().exit();
                }
                return true;
            }else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    public void setData(User data) {

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
//				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .build();

        imageLoader.displayImage(Constant.IMAGE_URL + data.getUHeadPortrait(), iv_photo, options);
//        isChange=true;
        et_nicheng.setText(data.getUNickName());
        if (data.getUSex().equals(Constant.SEX_MAN)){
            rb_nan.setChecked(true);
            rb_nv.setChecked(false);
        }else {
            rb_nv.setChecked(true);
            rb_nan.setChecked(false);
        }

        et_age.setText(data.getUAge()+"");
        String jiaxiang = data.getUHome();
        et_country.setText(jiaxiang.substring(0,jiaxiang.indexOf("-")));
        et_city.setText(jiaxiang.substring(jiaxiang.indexOf("-")+1,jiaxiang.length()));
        tv_lover.setText(data.getUHobby());
        tv_xingzuo.setText(data.getUConstellation());
        et_qianming.setText(data.getUSignaTure());
    }

    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(),BitmapUtil.getPhotoFileName());
    //提示对话框方法
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("头像设置")
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
