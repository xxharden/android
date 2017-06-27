package com.gqh.mystudio.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.LinkListAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.LinkEntity;
import com.gqh.mystudio.entity.LinkListEntity;
import com.gqh.mystudio.entity.LinkListResult;
import com.gqh.mystudio.entity.LinkResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/23
 * desc: 新派link 分类详情
 */
public class LinkFenleiDetailActivity extends BaseHttpActivity {

    private   String httpUrl = "http://apis.baidu.com/baidunuomi/openapi/searchshops";
    private final static String PARAM="&page=1&page_size=40";
    private static final int MSG_TEXT_2 = 100002;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==MSG_TEXT_2){
                setData(result.data);
            }
        }
    };
    PopupWindow popuWindow;
    private View view;
    private Button bt_meishi;
    private Button bt_dianying;
    private Button bt_tuangou;
    private Button bt_waimai;
    private Button bt_lvxing;
    private Button bt_liren;
    private Button bt_gouwu;
    private Button bt_chuxing;
    private Button bt_fuwu;
    private TextView tv_leixing;
    private String leixing;
    private ImageView iv_back;
    private LinkEntity data;
    private ListView listview_link;
    private LinkResult result;

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

       /* try {
            LinkListResult result= GsonUtils.getSingleBean(jsonStr,LinkListResult.class);
            if (result.isSuccess()){
                setData(result.data);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }*/
    }

    public void setData(final LinkEntity data) {
        this.data = data;

        LinkListAdapter adapter=new LinkListAdapter(data.getShops(),this);
        listview_link.setAdapter(adapter);

        listview_link.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LinkFenleiDetailActivity.this,WoYaoZuXinpaiActivity.class);
                intent.putExtra("URL",data.getShops().get(position).getShop_url());
                toActivity(intent);
            }
        });
    }

    @Override
    protected void request() {

        if (leixing.equals("美食")){
//            sendRequest("07_005_01");
            String httpArg = "city_id=100010000&cat_ids=326"+PARAM;
            sendRequestApi2(httpArg);
        }else if (leixing.equals("电影")){
            String httpArg = "city_id=100010000&cat_ids=323&subcat_ids=345"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_02");
        }else if (leixing.equals("团购")){
            String httpArg = "city_id=100010000&cat_ids=320"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_03");
        }else if (leixing.equals("外卖")){
            Intent intent4=new Intent(this,WoYaoZuXinpaiActivity.class);
            intent4.putExtra("URL","http://waimai.baidu.com/waimai/shoplist/c63ab3051c9a6892");
            toActivity(intent4);
//            sendRequest("07_005_04");
        }else if (leixing.equals("旅行")){
            String httpArg = "city_id=100010000&cat_ids=377"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_05");
        }else if (leixing.equals("丽人")){
            String httpArg = "city_id=100010000&cat_ids=316&subcat_ids=956"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_06");
        }else if (leixing.equals("购物")){
            String httpArg = "city_id=100010000&cat_ids=316&subcat_ids=875"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_07");
        }else if (leixing.equals("出行")){
            String httpArg = "city_id=100010000&cat_ids=377"+PARAM;
            sendRequestApi2(httpArg);
//            sendRequest("07_005_08");
        }else if (leixing.equals("生活服务")){
            String httpArg1 =  "city_id=100010000&cat_ids=316"+PARAM;
            sendRequestApi2(httpArg1);
//            sendRequest("07_005_09");
        }

    }

    /* private void sendRequest(String subType){
         RequestParams params =new RequestParams();
         params.put("SubType",subType);
         postDialog(Constant.SUPPLIER_LIST_BY_TYPE,params);
     }
 */
    @Override
    protected int getContentView() {
        return R.layout.activity_link_fenlei_detail;
    }

    @Override
    protected void initView() {
        listview_link= (ListView) findViewById(R.id.listview_link);
        iv_back= (ImageView) findViewById(R.id.image_fanhui);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_leixing= (TextView) findViewById(R.id.tv_leixing);
        leixing=getIntent().getStringExtra("fenlei");
        //设置tv_leixing
        tv_leixing.setText(leixing);
        //给tv_leixing设置点击事件显示popupwindow
        tv_leixing.setOnClickListener(new PopuOnClickListener());
        initPopupWindow();
    }




    /**
     * 这个类主要显示PopuWindow，并显示之后对里面的按钮添加监听事件。
     */
    private class PopuOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_leixing:
                    // 加上下面两行可以用back键关闭popupwindow，否则必须调用dismiss();
                    // 需要顺利让PopUpWindow dimiss；PopUpWindow的背景不能为空。
                    // 当有popuWindow.setFocusable(false);的时候，说明PopuWindow不能获得焦点，并不能点击外面消失，只能由dismiss()消失。
                    // 当设置为popuWindow.setFocusable(true);的时候，加上下面两行代码才会消失
                    // 注意这里添加背景并不会覆盖原来的背景。
                    ColorDrawable cd = new ColorDrawable();
                    popuWindow.setBackgroundDrawable(cd);
                    popuWindow.showAsDropDown(v);
                    bt_meishi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("美食");
                            //发请求获取美食列表
                            String httpArg = "city_id=100010000&cat_ids=326"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_01");

                        }
                    });

                    bt_dianying.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("电影");
                            //发请求获取电影列表
                            String httpArg = "city_id=100010000&cat_ids=323&subcat_ids=345"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_02");
                        }
                    });

                    bt_tuangou.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("团购");
                            //发请求获取团购列表
                            String httpArg = "city_id=100010000&cat_ids=320"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_03");
                        }
                    });
                    bt_waimai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("外卖");
                            //发请求获取外卖列表
                            Intent intent4=new Intent(LinkFenleiDetailActivity.this,WoYaoZuXinpaiActivity.class);
                            intent4.putExtra("URL", "http://waimai.baidu.com/waimai/shoplist/c63ab3051c9a6892");
                            toActivity(intent4);
//                            sendRequest("07_005_04");
                        }
                    });
                    bt_lvxing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("旅行");
                            //发请求获取旅行列表
                            String httpArg = "city_id=100010000&cat_ids=377"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_05");

                        }
                    });
                    bt_liren.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("丽人");
                            //发请求获取丽人列表
                            String httpArg = "city_id=100010000&cat_ids=316&subcat_ids=956"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_06");
                        }
                    });
                    bt_gouwu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("购物");
                            //发请求获取购物列表
                            String httpArg = "city_id=100010000&cat_ids=316&subcat_ids=875"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_07");
                        }
                    });
                    bt_chuxing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("出行");
                            //发请求获取出行列表
                            String httpArg = "city_id=100010000&cat_ids=377"+PARAM;
                            sendRequestApi2(httpArg);
//                            sendRequest("07_005_08");
                        }
                    });
                    bt_fuwu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popuWindow.dismiss();
                            tv_leixing.setText("生活服务");
                            //发请求获取生活服务列表
                            String httpArg1 =  "city_id=100010000&cat_ids=316"+PARAM;
                            sendRequestApi2(httpArg1);
//                            sendRequest("07_005_09");
                        }
                    });
                    break;
            }
        }
    }

    private void initPopupWindow() {
        view = this.getLayoutInflater().inflate(R.layout.activity_popupwindow_fenlei, null);
        popuWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // 这里设置显示PopuWindow之后在外面点击是否有效。如果为false的话，那么点击PopuWindow外面并不会关闭PopuWindow。
        popuWindow.setOutsideTouchable(true);//不能在没有焦点的时候使用
        bt_meishi = (Button) view.findViewById(R.id.bt_meishi);
        bt_dianying = (Button) view.findViewById(R.id.bt_dianying);
        bt_tuangou = (Button) view.findViewById(R.id.bt_tuangou);
        bt_waimai = (Button) view.findViewById(R.id.bt_waimai);
        bt_lvxing = (Button) view.findViewById(R.id.bt_lvxing);
        bt_liren = (Button) view.findViewById(R.id.bt_liren);
        bt_gouwu = (Button) view.findViewById(R.id.bt_gouwu);
        bt_chuxing = (Button) view.findViewById(R.id.bt_chuxing);
        bt_fuwu = (Button) view.findViewById(R.id.bt_fuwu);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (popuWindow.isShowing()) {
            popuWindow.dismiss();
        }
    }


   /* private void sendRequestApi(){
       *//* ApiStoreSDK.execute(
                String url, 						// 接口地址
                String method, 					// 接口方法
                Parameters params, 			// 接口参数
                ApiCallBack callBack) {		// 接口回调方法
            其中方法为”get”或”post”，推荐使用sdk提供的变量ApiStoreSDK.GET和ApiStoreSDK.POST.
                    回调方法在UI线程中执行，可以直接修改UI。
            onError 调用时，statusCode参数值对应如下：
            -1 没有检测到当前网络；
            -3 没有进行初始化;
            -4 传参错误
            其他数值是http状态码，或服务器响应的errNum，请查阅响应字符串responseString*//*

        Parameters para = new Parameters();

//        城市ID
        para.put("city_id", 100010000);
        //WGS84坐标;用户所在位置longitude&lt;经度&gt;, latitude &lt;纬度&gt; 如：38.76623,116.43213
        para.put("location", "38.76623,116.43213");
//        基于location,搜索的半径范围，单位是米。 可选（若传入该参数，必须同时传入合法的经纬度坐标， radius字段默认半径3000米）
        para.put("radius", 3000);
//        商圈id, 支持多个查询， 多个商圈用英文逗号,连接
        para.put("bizarea_ids", "1322,1328");

//        页码，如不传入默认为1，即第一页
//        para.put("page", 1);
//        每页返回的团单结果条目数上限，最小值1，最大值50，如不传入默认为10
//        para.put("page_size", 25);
//        每页返回的团单结果条目数上限，最小值1，最大值50，如不传入默认为10
//        para.put("deals_per_shop", 25);

        ApiStoreSDK.execute("http://apis.baidu.com/baidunuomi/openapi/searchshops",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        MyLog.i("onSuccess====", responseString);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        showToast("亲,网络好像不给力哦!");
                        MyLog.i("onError====", responseString);
                        MyLog.i("errMsg====", e.getMessage());
                    }

                });
    }*/




//
////    "subcat_name": "丽人",
////            "subcat_ids": 956
//    "cat_ids": 316,
//            "cat_name": "生活服务",

    //    326美食
    //    316生活服务
//    330网购
//    377酒店旅游
//    1000出行代驾

    //    "cat_ids": 323,
//            "cat_name": "其他",
//            "subcategories": [
//    {
//        "subcat_name": "电影",
//            "subcat_idss": 345
//    },
    private void sendRequestApi2(final String httpArg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MyLog.i("================", "=========================");
                    String jsonResult = request(httpUrl, httpArg);
                    result= GsonUtils.getSingleBean(jsonResult,LinkResult.class);
                    if (result.data==null){
                        showToast("暂无数据");
                    }else {
                        if (result.isSuccess()){
                            handler.sendEmptyMessage(MSG_TEXT_2);
                        }
                    }
                    MyLog.i("onSuccess====", jsonResult);
                }catch (Exception e){
                    ExceptionUtil.handleException(e);
                }
            }
        }).start();
    }


    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public  String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "f999003df3a62315f33ad5bdf0d8ec92");
            connection.connect();

            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            MyLog.i("message===", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
