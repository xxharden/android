package com.gqh.mystudio.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.FabuPhotoGrideviewAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FileResult;
import com.gqh.mystudio.utill.BitmapUtil;
import com.gqh.mystudio.utill.Const;
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

import cn.myapp.base.BaseActivity;
import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.IntentUtils;
import cn.myapp.util.StringUtil;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 左邻右舍  晒： 我要晒
 */
public class ShaiWoYaoShaiActivity extends BaseHttpActivity {
    private List<Bitmap> mList=new ArrayList<Bitmap>();
    private FabuPhotoGrideviewAdapter adapter;

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();

    @ViewInject(R.id.iv_photo)
    private ImageCircleView iv_photo;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.iv_xingbie)
    private ImageView iv_xingbie;
    @ViewInject(R.id.et_text)
    private EditText et_text;
    @ViewInject(R.id.gridview)
    private GridView gridview;
    private String ivUrl=null;
    private int uId;


    @OnClick({R.id.bt_paizhao,R.id.bt_x,R.id.bt_fabu})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.bt_paizhao:
                    //选取照片
                    showDialog();
                    break;
                case R.id.bt_x:
                    finish();
                    break;
                case R.id.bt_fabu:
                    String uShowCont = et_text.getText().toString();

                    if (StringUtil.isEmpty(uShowCont)){
                        showToast("请输入晒的内容！");
                        break;
                    }else if(mList.size()==0){
                        showToast("请选择图片");
                        break;
                    }
                    //发布请求
                    RequestParams params=new RequestParams();
                    params.put("UID",uId);
                    params.put("ShowPic",ivUrl);
                    params.put("ShowCont",uShowCont);
                    postDialog(Constant.WOYAO_SHAI,params,100);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_wo_yao_shai;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
//				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//				.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .build();


        User uSer = BamsApplication.getInstance().getUser();
        uId=uSer.getUID();
        tv_name.setText(uSer.getUNickName());
        imageLoader.displayImage(Constant.IMAGE_URL+uSer.getUHeadPortrait(), iv_photo, options);

        if (uSer.getUSex().equals("00_011_1")){
            iv_xingbie.setImageResource(R.drawable.sex_nan);
        }else {
            iv_xingbie.setImageResource(R.drawable.sex_nv);
        }

        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        if(mList.size()>0){
//            adapter=new FabuPhotoGrideviewAdapter(mList,this,Constant.STYLE_ADAPTER_NO);
//            gridview.setAdapter(adapter);
//            gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        }
    }


    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
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
                //提交成功
                JSONObject object=new JSONObject(jsonStr);
                if (object.getString("code").equals("0")){
                    showToast("晒成功");
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
                                    gridview.setAdapter(adapter);
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
