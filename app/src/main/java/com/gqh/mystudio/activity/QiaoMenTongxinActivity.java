package com.gqh.mystudio.activity;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.FaceAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MessageEntity;
import com.gqh.mystudio.entity.MessageResult;
import com.gqh.mystudio.entity.PersonEntity;
import com.gqh.mystudio.entity.PersonResult;
import com.gqh.mystudio.utill.ChatUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.StringUtil;
import http.RequestParams;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 左邻右舍  敲： 留言聊天
 */
public class QiaoMenTongxinActivity extends BaseHttpActivity {

    @ViewInject(R.id.scrollView1)
    private ScrollView scrollView;
    @ViewInject(R.id.bt_send)
    private Button bt_send;
    @ViewInject(R.id.listview)
    private ListView listview;
    @ViewInject(R.id.et_xiaoxi)
    private EditText et_xiaoxi;
    @ViewInject(R.id.linearLayoutChatContent)
    private LinearLayout llChatContent;

    @ViewInject(R.id.gridView1)
    private GridView gridView;

    View view = null;
    Handler handler = new Handler();
    private int uId;
    private int fId;
    private String code1;
    private String code2;
    private FaceAdapter faceAdapter;
    private List<MessageEntity> listData;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private User uSer;
    private PersonEntity person;
//    private boolean isFriend;


    @OnClick({R.id.image_fanhui, R.id.bt_songli, R.id.bt_send})
    private void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.image_fanhui:
                    finish();

                    break;
//                case R.id.bt_biaoqing:
//                    gridView.setVisibility(View.VISIBLE);

//                    break;
                case R.id.bt_songli:
                    Intent intent=new Intent(this,QiaoSongLiActivity.class);
                    intent.putExtra("FID",fId);
                    toActivity(intent);
                    break;

                case R.id.bt_send:
                    String content = et_xiaoxi.getText().toString();
                    if (StringUtil.isEmpty(content)) {
                        break;
                    } else {

//                        if (!isFriend) {
                        //敲门请求
//                            RequestParams params1 = new RequestParams();
//                            params1.put("UID", uId);//自己id
//                            params1.put("FID", fId);//用户id
//                            postDialog(Constant.KNOCK, params1, 1);
//                        }else {
                        //发送留言
                        RequestParams params2 = new RequestParams();
                        params2.put("UID", uId);
                        params2.put("FID", fId);
                        params2.put("content", content);
                        postDialog(Constant.SEND_MSG, params2, 2,false);
//                        }

                        //更新聊天UI
                        view = View.inflate(context, R.layout.item_qiaomen_tongxin_right, null);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
                        ImageCircleView iv_photo = (ImageCircleView) view.findViewById(R.id.iv_photo);
                        ImageView iv_sex = (ImageView) view.findViewById(R.id.iv_sex);
                        User user = BamsApplication.getInstance().getUser();
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格

                        tv_name.setText(user.getUNickName());
                        tv_date.setText(dateFormat.format(now));
                        if (user.getUSex().equals("00_011_1")){
                            iv_sex.setImageResource(R.drawable.sex_nan);
                        }else {
                            iv_sex.setImageResource(R.drawable.sex_nv);
                        }
                        imageLoader.displayImage(Constant.IMAGE_URL+user.getUHeadPortrait(),iv_photo,options);

                        // 显示，判断数据的类型，有文本，有face
                        // 不同类型显示不一样
                        int type = ChatUtil.getType(content);
                        if (type == ChatUtil.TYPE_TEXT) {
                            TextView tv_text = (TextView) view.findViewById(R.id.tv_text);
                            tv_text.setVisibility(View.VISIBLE);
                            tv_text.setText(content);
                        }
                        if (type == ChatUtil.TYPE_FACE) {
                            GifImageView gifImageView = (GifImageView) view.findViewById(R.id.gifImageView);
                            gifImageView.setVisibility(View.VISIBLE);
                            String fileName = ChatUtil.getFaceFileName(content);
                            GifDrawable gifDrawable = new GifDrawable(getAssets(), fileName);
                            gifImageView.setBackgroundDrawable(gifDrawable);
                        }

                        llChatContent.addView(view);

                    }

                    et_xiaoxi.setText("");
                    break;

            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {

            if (requestCode==10){
                PersonResult result = GsonUtils.getSingleBean(jsonStr, PersonResult.class);
                if (result.isSuccess()){
                    person=result.data;
                    //留言记录
                    RequestParams params = new RequestParams();
                    params.put("UID", uId);
                    params.put("FID", fId);
                    postDialog(Constant.LIUYAN_JILU, params, 3,false);
                }
            }

            if (requestCode == 2) {
                JSONObject object2 = new JSONObject(jsonStr);
                code2 = object2.getString("code");
                if (code2.equals("0")){
                    showToast("发送成功");
                }
            }


            if (requestCode == 3) {
                MessageResult result = GsonUtils.getSingleBean(jsonStr, MessageResult.class);
                if (result.isSuccess()) {
                    listData = result.data;
                    if (listData.size()!=0){
                        setData();
                    }

                }
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    private void setData() {

        try {
            for (MessageEntity msg : listData) {
                int perId = msg.getMsgFrom();
                String body = msg.getContent();

                //判断是谁说的话
                if (perId == uId) {//我说的

                    view = View.inflate(context, R.layout.item_qiaomen_tongxin_right, null);
                    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                    TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
                    ImageCircleView iv_photo = (ImageCircleView) view.findViewById(R.id.iv_photo);
                    ImageView iv_sex = (ImageView) view.findViewById(R.id.iv_sex);

                    tv_name.setText(uSer.getUNickName());
                    tv_date.setText(msg.getCreatetime());
                    if (uSer.getUSex().equals("00_011_1")){
                        iv_sex.setImageResource(R.drawable.sex_nan);
                    }else {
                        iv_sex.setImageResource(R.drawable.sex_nv);
                    }
                    imageLoader.displayImage(Constant.IMAGE_URL+uSer.getUHeadPortrait(),iv_photo,options);

                } else {//对方说的
                    view = View.inflate(context, R.layout.item_qiaomen_tongxin_left, null);
                    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                    TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
                    ImageCircleView iv_photo = (ImageCircleView) view.findViewById(R.id.iv_photo);
                    ImageView iv_sex = (ImageView) view.findViewById(R.id.iv_sex);

                    tv_name.setText(person.getUNickName());
                    tv_date.setText(msg.getCreatetime());
                    if (person.getUSex().equals("00_011_1")){
                        iv_sex.setImageResource(R.drawable.sex_nan);
                    }else {
                        iv_sex.setImageResource(R.drawable.sex_nv);
                    }
                    imageLoader.displayImage(Constant.IMAGE_URL+person.getUHeadPortrait(),iv_photo,options);
                }


                // 显示，判断数据的类型，有文本，有face
                // 不同类型显示不一样
                int type = ChatUtil.getType(body);
                if (type == ChatUtil.TYPE_TEXT) {
                    TextView tv_text = (TextView) view.findViewById(R.id.tv_text);
                    tv_text.setVisibility(View.VISIBLE);
                    tv_text.setText(body);
                }
                if (type == ChatUtil.TYPE_FACE) {
                    GifImageView gifImageView = (GifImageView) view.findViewById(R.id.gifImageView);
                    gifImageView.setVisibility(View.VISIBLE);
                    String fileName = ChatUtil.getFaceFileName(body);
                    GifDrawable gifDrawable = new GifDrawable(getAssets(), fileName);
                    gifImageView.setBackgroundDrawable(gifDrawable);
                }

                llChatContent.addView(view);
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            // 向上移
                            // int linearLayoutHeight = llChatContent.getHeight();
                            //
                            // int scrollViewHeight = scrollView.getHeight();
                            //
                            // LogUtil.i("向上移", "linearLayoutHeight="
                            // + linearLayoutHeight
                            // + " scrollViewHeight="
                            // + scrollViewHeight);
                            //
                            // if (linearLayoutHeight > scrollViewHeight) {
                            // int moveY = linearLayoutHeight- scrollViewHeight;
                            //
                            // scrollView.scrollTo(0, moveY);
                            // }

                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                }, 1);// 有的手机samsung s4时间要长一点
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        //获取发言人信息
        RequestParams params10=new RequestParams();
        params10.put("UID",fId);
        postDialog(Constant.GET_SPEAK_USER_INFO,params10,10,false);



    }

    @Override
    protected int getContentView() {
        return R.layout.activity_qiao_men_tongxin;
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


        uSer=BamsApplication.getInstance().getUser();
        uId = uSer.getUID();
        fId = getIntent().getIntExtra("fId", 0);


        faceAdapter = new FaceAdapter(this, faceFileName);
        gridView.setAdapter(faceAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String faceFileName = (String) faceAdapter.getItem(position);
                String body = ChatUtil.addFaceTag(faceFileName);

                //发送留言
                RequestParams params2 = new RequestParams();
                params2.put("UID", uId);
                params2.put("FID", fId);
                params2.put("content", body);
                postDialog(Constant.SEND_MSG, params2, 2,false);
                gridView.setVisibility(View.GONE);
            }
        });
    }


    String[] faceFileName = new String[]{"f001.gif", "f002.gif", "f003.gif",
            "f004.gif", "f005.gif", "f006.gif", "f007.gif", "f008.gif",
            "f009.gif", "f010.gif", "f011.gif", "f012.gif", "f013.gif",
            "f014.gif", "f015.gif", "f016.gif", "f017.gif", "f018.gif",
            "f019.gif", "f020.gif", "f021.gif", "f022.gif", "f023.gif",
            "f024.gif", "f025.gif", "f026.gif", "f027.gif", "f028.gif",
            "f029.gif", "f030.gif", "f031.gif", "f032.gif", "f033.gif",
            "f034.gif", "f035.gif", "f036.gif", "f037.gif", "f038.gif",
            "f039.gif", "f040.gif", "f041.gif", "f042.gif", "f043.gif",
            "f044.gif", "f045.gif", "f046.gif", "f047.gif", "f048.gif",
            "f049.gif", "f050.gif", "f051.gif", "f052.gif", "f053.gif",
            "f054.gif", "f055.gif", "f056.gif", "f057.gif", "f058.gif",
            "f059.gif", "f060.gif", "f061.gif", "f062.gif", "f063.gif",
            "f064.gif", "f065.gif", "f066.gif", "f067.gif", "f068.gif",
            "f069.gif", "f070.gif", "f071.gif", "f072.gif", "f073.gif",
            "f074.gif", "f075.gif", "f076.gif", "f077.gif", "f078.gif",
            "f079.gif", "f080.gif", "f081.gif", "f082.gif", "f083.gif",
            "f084.gif", "f085.gif", "f086.gif", "f087.gif", "f088.gif",
            "f089.gif", "f090.gif", "f091.gif", "f092.gif", "f093.gif",
            "f094.gif", "f095.gif", "f096.gif", "f097.gif", "f098.gif",
            "f099.gif", "f100.gif", "f101.gif", "f102.gif", "f103.gif",
            "f104.gif", "f105.gif", "f106.gif", "f107.gif", "f108.gif",
            "f109.gif", "f110.gif", "f111.gif", "f112.gif", "f113.gif",
            "f114.gif", "f115.gif", "f116.gif", "f117.gif", "f118.gif",
            "f119.gif", "f120.gif", "f121.gif", "f122.gif", "f123.gif",
            "f124.gif", "f125.gif", "f126.gif", "f127.gif", "f128.gif",
            "f129.gif", "f130.gif", "f131.gif", "f132.gif", "f133.gif",
            "f134.gif", "f135.gif", "f136.gif", "f137.gif", "f138.gif",
            "f139.gif", "f140.gif", "f141.gif", "f142.gif"};
}
