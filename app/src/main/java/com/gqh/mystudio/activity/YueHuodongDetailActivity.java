package com.gqh.mystudio.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.FabuPhotoGrideviewAdapter;
import com.gqh.mystudio.adapter.GrideviewAdapter;
import com.gqh.mystudio.adapter.GrideviewAdapterPhoto;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.Person;
import com.gqh.mystudio.entity.YueDetailEntity;
import com.gqh.mystudio.entity.YueDetailResult;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.gqh.mystudio.entity.YueMoreResult;
import com.gqh.mystudio.utill.BitmapUtil;
import com.gqh.mystudio.utill.Const;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class YueHuodongDetailActivity extends BaseHttpActivity {

    @ViewInject(R.id.iv_photo)
    private ImageView iv_photo;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_age)
    private TextView tv_age;
    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;
    @ViewInject(R.id.bt_jiaru)
    private Button bt_jiaru;
    @ViewInject(R.id.bt_yaoqing)
    private Button bt_yaoqing;
    @ViewInject(R.id.bt_over)
    private Button bt_over;


    @ViewInject(R.id.gridview)
    private GridView gridview;

    @ViewInject(R.id.tv_huodong_name)
    private TextView tv_huodong_name;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.tv_text)
    private TextView tv_text;
    @ViewInject(R.id.tv_nums)
    private TextView tv_nums;

    @ViewInject(R.id.gridview_baominh)
    private GridView gridview_baominh;
    private int groupID;
    private int type;

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private int uId;


    @OnClick({R.id.bt_jiaru,R.id.bt_yaoqing,R.id.image_fanhui})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_jiaru:
                    //加入活动请求
                    RequestParams params =new RequestParams();
                    params.put("UID",uId);
                    params.put("GroupID",groupID);
                    postDialog(Constant.YUE_JOIN,params,2,false);
                    break;
                case R.id.bt_yaoqing:
                    Intent intent=new Intent(this,YueYaoQingHaoyouActivity.class);
                    intent.putExtra("GroupID",groupID);
                    toActivity(intent);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yue_huodong_detail;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        type=getIntent().getIntExtra("type", 0);
        groupID=getIntent().getIntExtra("GroupID",0);
        uId= BamsApplication.getInstance().getUser().getUID();

        if (type==0){//“State”:”活动状态， 0 表示还未开始，1表示已结束。”
            bt_over.setVisibility(View.GONE);
            bt_jiaru.setVisibility(View.VISIBLE);
            bt_yaoqing.setVisibility(View.VISIBLE);
            bt_over.setClickable(false);
        }else {
            bt_over.setVisibility(View.VISIBLE);
            bt_jiaru.setVisibility(View.GONE);
            bt_yaoqing.setVisibility(View.GONE);
            bt_over.setClickable(false);
        }

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

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{

            if (requestCode==1){
                MyLog.i("json===========",jsonStr);
                YueDetailResult result2 = GsonUtils.getSingleBean(jsonStr, YueDetailResult.class);
                if (result2.isSuccess()){
                    setDataYue(result2.data);
                }
            }

            if (requestCode==2){
                JSONObject object=new JSONObject(jsonStr);
                String code = object.getString("code");
                if (code.equals("0")){
                    Intent intent=new Intent(this,YueJiaruHongdongOkActivity.class);
                    intent.putExtra("GroupID",groupID);
                    toActivity(intent);
                }
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        //约  详情
        if (groupID!=0){
            RequestParams params=new RequestParams();
            params.put("GroupID",groupID);
            postDialog(Constant.YUE_DETAIL,params,1);
        }
    }

    public void setDataYue(final YueDetailEntity dataYue) {

        tv_name.setText(dataYue.getAdmin());
        tv_age.setText(dataYue.getUAge() + "岁");//年龄
        imageLoader.displayImage(Constant.IMAGE_URL + dataYue.getUHeadPortrait(), iv_photo, options);
        if (dataYue.getUSex().equals("00_011_1")){
            iv_sex.setImageResource(R.drawable.sex_nan);
        }else {
            iv_sex.setImageResource(R.drawable.sex_nv);
        }

        tv_huodong_name.setText(dataYue.getName());
        tv_address.setText(dataYue.getArea());
        tv_time.setText(dataYue.getBeginDate());
        tv_text.setText(dataYue.getIntro());
        tv_nums.setText("" + dataYue.getPersonNum());

        String picId = dataYue.getPicID();
        MyLog.i("picId=",picId.toString());
        final List<String> zList=new ArrayList<String>();
        if (picId.indexOf(";")==-1){
            zList.add(Constant.IMAGE_URL+picId);
        }else {
            String[] list = picId.split(";");
            for (int i=0;i<list.length;i++){
                zList.add(Constant.IMAGE_URL+list[i]);
            }
        }
        MyLog.i("mList=",zList.toString());
        GrideviewAdapter gvAdapter=new GrideviewAdapter(zList,this,Constant.STYLE_ADAPTER_OK);
        gridview.setAdapter(gvAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PictureShowActivity.class);
                intent.putExtra("picId", zList.get(position));
                context.startActivity(intent);
            }
        });

        final List<Person>list=dataYue.getPerson();
        List<String> xList=new ArrayList<String>();
        for (int i=0;i<list.size();i++){
            String headPor = Constant.IMAGE_URL+list.get(i).getUHeadPortrait();
            xList.add(headPor);
        }
        GrideviewAdapterPhoto gvbaoAdapter=new GrideviewAdapterPhoto(xList,this,Constant.STYLE_ADAPTER_OK);
        gridview_baominh.setAdapter(gvbaoAdapter);
        gridview_baominh.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridview_baominh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(YueHuodongDetailActivity.this,ShaiGerenXinxiActivity.class);
                intent.putExtra("UID",list.get(position).getUID());
                toActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //约  详情
        if (groupID!=0){
            RequestParams params=new RequestParams();
            params.put("GroupID",groupID);
            postDialog(Constant.YUE_DETAIL,params,1,false);
        }
    }
}
