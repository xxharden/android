package com.gqh.mystudio.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.JiaofeiJiluAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FeiyongTongjiEntity;
import com.gqh.mystudio.entity.FeiyongTongjiResult;
import com.gqh.mystudio.entity.JiaofeiJiluEntity;
import com.gqh.mystudio.entity.JiaofeiJiluResult;
import com.gqh.mystudio.view.PickerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class XinpaibiLishiZhangdanActivity extends BaseHttpActivity {


    @ViewInject(R.id.tv_zongzhichu)
    private TextView tv_zongzhichu;
    @ViewInject(R.id.tv_shenghuofei)
    private TextView tv_shenghuofei;
    @ViewInject(R.id.tv_gouwufei)
    private TextView tv_gouwufei;
    @ViewInject(R.id.tv_time_start)
    private TextView tv_time_start;
    @ViewInject(R.id.tv_time_stop)
    private TextView tv_time_stop;
    @ViewInject(R.id.listview)
    private ListView listview;

    @ViewInject(R.id.nian_pv)
    private PickerView nian_pv;
    @ViewInject(R.id.yue_pv)
    private PickerView yue_pv;
    @ViewInject(R.id.ri_pv)
    private PickerView ri_pv;
    @ViewInject(R.id.datePicker)
    private LinearLayout datePicker;



    private GraphicalView graphicalView;
    //    int[] colors={R.color.lishizhangdan_bingzhuangtu_1,R.color.lishizhangdan_bingzhuangtu_2};
    int[] colors={Color.RED,Color.CYAN};
    DefaultRenderer renderer=buildCategoryRenderer(colors);


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
    private int custId;
    private String beginDate;
    private String endDate;
    private List<JiaofeiJiluEntity> data;
    private FeiyongTongjiEntity data2;

    @OnClick({R.id.image_fanhui,R.id.tv_time_start,R.id.tv_time_stop,R.id.quxiao,R.id.wancheng})
    private void onClick(View v){
        try{
            switch (v.getId()){
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
                        tv_time_start.setText(time);
                        beginDate=time;
                    }else {
                        tv_time_stop.setText(time);
                        endDate=time;
                        RequestParams params1 =new RequestParams();
                        params1.put("CustID",custId);
                        params1.put("BeginDate",beginDate);
                        params1.put("EndDate",endDate);
//                        params1.put("CustID",48);
//                        params1.put("BeginDate","2016/01/01");
//                        params1.put("EndDate","2016/03/15");
                        //        params1.put("PageIndex",PageIndex);//分页
                        //        params1.put("PageNum",PageNum);
                        postDialog(Constant.RMB_LIST_BUSINESS,params1,1,false);
                    }
                    datePicker.setVisibility(View.GONE);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e
            );
        }
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_xinpaibi_lishi_zhangdan;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        custId= BamsApplication.getInstance().getUser().getCustID();


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

        tv_time_start.setText(year+"/"+str_mon+"/"+str_day);
        tv_time_stop.setText(year+"/"+str_mon+"/"+str_day);


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

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode==1){
                JiaofeiJiluResult result1= GsonUtils.getSingleBean(jsonStr,JiaofeiJiluResult.class);
                if (result1.isSuccess()){
                    setData(result1.data);
                }
            }

            if (requestCode==2){
                FeiyongTongjiResult result2=GsonUtils.getSingleBean(jsonStr,FeiyongTongjiResult.class);
                if (result2.isSuccess()){
                    setData2(result2.data);
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {


        RequestParams params2=new RequestParams();
        params2.put("CustID",custId);
        postDialog(Constant.RMB_STAT_BUSINESS,params2,2,false);
    }



    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("生活缴费", values[0]);
        series.add("购物", values[1]);
        return series;
    }

    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();

//		renderer.setLegendTextSize(20);//设置左下角表注的文字大小
        renderer.setShowLegend(false);
        //renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮
        renderer.setZoomEnabled(false);//设置不允许放大缩小.
//		renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
//		renderer.setLabelsTextSize(20);//饼图上标记文字的字体大小


        //renderer.setLabelsColor(Color.WHITE);//饼图上标记文字的颜色
        renderer.setShowLabels(false);

        renderer.setPanEnabled(false);//设置是否可以平移
        //renderer.setDisplayValues(true);//是否显示值
        renderer.setClickEnabled(true);//设置是否可以被点击

        renderer.setMargins(new int[] { 0, 0, 0,0 });//距离上下左右
        //margins - an array containing the margin size values, in this order: top, left, bottom, right
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
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

    public void setData(final List<JiaofeiJiluEntity> data) {
        this.data = data;
        JiaofeiJiluAdapter adapter=new JiaofeiJiluAdapter(data,this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录详情
                Intent intent =new Intent(XinpaibiLishiZhangdanActivity.this,XinpaibiJiaofeiDetailActivity.class);
                intent.putExtra("BusinessFlow",data.get(position).getBusinessFlow());
                intent.putExtra("BusiDate",data.get(position).getBusiDate());
                toActivity(intent);
            }
        });

    }

    public void setData2(FeiyongTongjiEntity data2) {
        this.data2 = data2;

        double lifeTotal = data2.getLifeTotal();
        double shopTotal = data2.getShopTotal();
        tv_zongzhichu.setText("总支出   "+data2.getTotal());
        tv_shenghuofei.setText(lifeTotal+"");
        tv_gouwufei.setText(shopTotal+"");


        //饼状图
        double[] values={lifeTotal,shopTotal};//比例值

        CategorySeries dataset=buildCategoryDataset("测试饼图", values);
        graphicalView= ChartFactory.getPieChartView(getBaseContext(), dataset, renderer);//饼状图
        LinearLayout layout=(LinearLayout)findViewById(R.id.linearlayout);
        layout.removeAllViews();
        layout.addView(graphicalView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    }
}
