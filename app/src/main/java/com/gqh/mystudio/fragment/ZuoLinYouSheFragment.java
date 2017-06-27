package com.gqh.mystudio.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.QiaoMoreActivity;
import com.gqh.mystudio.activity.ShaiMoreActivity;
import com.gqh.mystudio.activity.ShaiWoYaoShaiActivity;
import com.gqh.mystudio.activity.YueFabuHuodongActivity;
import com.gqh.mystudio.activity.YueJiaruHongdongOkActivity;
import com.gqh.mystudio.activity.YueMoreActivity;
import com.gqh.mystudio.adapter.viewpageAdapterQiao;
import com.gqh.mystudio.adapter.viewpageAdapterShai;
import com.gqh.mystudio.adapter.viewpageAdapterYue;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.QiaoMoreEntity;
import com.gqh.mystudio.entity.QiaoMoreResult;
import com.gqh.mystudio.entity.QiaoPageListEntity;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.gqh.mystudio.entity.ShaiDongtaiResult;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.gqh.mystudio.entity.YueMoreResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpFragment;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: fragment 左邻右舍
 */

public class ZuoLinYouSheFragment extends BaseHttpFragment {

    @ViewInject(R.id.listview_shai)
    private ViewPager lv_shai;
    @ViewInject(R.id.listview_qiao)
    private ViewPager lv_qiao;
    @ViewInject(R.id.listview_yue)
    private ViewPager lv_yue;


    private int uId;
    private int apartmentId;
    private int groupID;
    private ACache aCache;

    @OnClick({R.id.ll_woyaoshai,R.id.bt_fabu,R.id.ll_more_shai,R.id.ll_more_qiao,R.id.ll_more_yue})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.ll_woyaoshai:
                    toActivity(ShaiWoYaoShaiActivity.class);
                    break;
                case R.id.bt_fabu:
                    toActivity(YueFabuHuodongActivity.class);
                    break;
                case R.id.ll_more_shai:
                    toActivity(ShaiMoreActivity.class);
                    break;
                case R.id.ll_more_qiao:
                    toActivity(QiaoMoreActivity.class);
                    break;
                case R.id.ll_more_yue:
                    toActivity(YueMoreActivity.class);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    private int page=0;
    private List<QiaoPageListEntity> qiaoList=new ArrayList<QiaoPageListEntity>();
    @Override
    protected void onSuccess(int requestCode, String jsonStr) throws Exception {
        try{

            if (requestCode==2){
                JSONObject object=new JSONObject(jsonStr);
                String code = object.getString("code");
                if (code.equals("0")){
                    Intent intent=new Intent(getActivity(),YueJiaruHongdongOkActivity.class);
                    intent.putExtra("GroupID",groupID);
                    toActivity(intent);
                }
            }
            if (requestCode==100) {
                //晒最新
                ShaiDongtaiResult result = GsonUtils.getSingleBean(jsonStr, ShaiDongtaiResult.class);
                if (result.isSuccess()) {
                    setDataShai(result.data);
                }
            }

            if(requestCode==0) {//敲最新
                QiaoMoreResult result1 = GsonUtils.getSingleBean(jsonStr, QiaoMoreResult.class);
                if (result1.isSuccess()) {
                    List<QiaoMoreEntity> qiaoentityList = result1.data;

                    MyLog.i("qiaoentityList=", qiaoentityList.toString());
                    if (!qiaoentityList.toString().equals("[]")) {
                        QiaoPageListEntity listEntity = new QiaoPageListEntity();
                        listEntity.setData(qiaoentityList);
                        qiaoList.add(listEntity);
                        page += 1;
                        //敲最热
                        RequestParams params = new RequestParams();
                        params.put("UID", uId);//用户ID
                        params.put("Type", 1);//0-最新公民 1最热公民
                        params.put("PageIndex", page);
                        params.put("PageNum", 4);
                        postDialog(Constant.QIAO_LIEBIAO, params, 0,false);
                    }else {
                        setDataQiao(qiaoList);
                    }
                }
            }

            if (requestCode==200) {
                //约
                YueMoreResult result2 = GsonUtils.getSingleBean(jsonStr, YueMoreResult.class);
                if (result2.isSuccess()) {
                    setDataYue(result2.data);
                }
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        //晒最新
        postDialog(Constant.SHAI_GONGMIN_DONGTAI, null,100,false);

        //敲最热
        RequestParams params=new RequestParams();
        params.put("UID",uId);//用户ID
        params.put("Type",1);//0-最新公民 1最热公民
        params.put("PageIndex",page);
        params.put("PageNum",4);
        postDialog(Constant.QIAO_LIEBIAO, params, 0);

        //约
        RequestParams params1=new RequestParams();
        params1.put("type", 0);//0最新列表  1历史列表
        postDialog(Constant.YUE_LIEBIAO, params1, 200,false);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_zuoling_youshe;
    }

    @Override
    protected void initView(View contentView) {
        ViewUtils.inject(this, contentView);
        apartmentId=BamsApplication.getInstance().getUser().getApartmentID();
        uId= BamsApplication.getInstance().getUser().getUID();

        aCache = ACache.get(context);
        aCache.put("zuolinIsfirt", true);//存缓存

    }

    public void setDataShai( final List<ShaiDongtaiEntity> dataShai) {

        viewpageAdapterShai adapterShai=new viewpageAdapterShai(getActivity(),dataShai);
        lv_shai.setAdapter(adapterShai);
    }


    public void setDataQiao(List<QiaoPageListEntity> dataQiao){
        viewpageAdapterQiao adapterQiao=new viewpageAdapterQiao(getActivity(),dataQiao);
        lv_qiao.setAdapter(adapterQiao);
    }


    public void setDataYue( final List<YueMoreEntity> dataYue) {

        viewpageAdapterYue  adapterYue=new viewpageAdapterYue(this,dataYue);
        lv_yue.setAdapter(adapterYue);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onResume() {
        super.onResume();

       boolean isFirst= (boolean) aCache.getAsObject("zuolinIsfirt");


        if (!isFirst){
            //晒最新
            postDialog(Constant.SHAI_GONGMIN_DONGTAI, null,100,false);

            //敲最热
            RequestParams params=new RequestParams();
            params.put("UID",uId);//用户ID
            params.put("Type",1);//0-最新公民 1最热公民
            params.put("PageIndex",0);
            params.put("PageNum",4);
            postDialog(Constant.QIAO_LIEBIAO, params, 0);

            //约
            RequestParams params1=new RequestParams();
            params1.put("type", 0);//0最新列表  1历史列表
            postDialog(Constant.YUE_LIEBIAO, params1, 200,false);
        }

        aCache.put("zuolinIsfirt", false);//存缓存
        MyLog.i("isFirst==",isFirst+"");
    }

    public void sendRequest(int groupID){
        this.groupID=groupID;
        //加入活动请求
        RequestParams params =new RequestParams();
        params.put("UID",uId);
        params.put("GroupID",groupID);
        postDialog(Constant.YUE_JOIN,params,2,false);
    }
}
