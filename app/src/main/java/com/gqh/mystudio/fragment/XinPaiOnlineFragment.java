package com.gqh.mystudio.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.ShangchengJiesuanActivity;
import com.gqh.mystudio.activity.ShangchengMydingdanActivity;
import com.gqh.mystudio.activity.ShangpinDetailActivity;
import com.gqh.mystudio.adapter.HorizontalListViewAdapter;
import com.gqh.mystudio.adapter.ShangpinListAdapter;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.entity.ShangpinListResult;
import com.gqh.mystudio.event.AddToListEvent;
import com.gqh.mystudio.view.HorizontalListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpFragment;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: fragment 新派online
 */

public class XinPaiOnlineFragment extends BaseHttpFragment {

    @ViewInject(R.id.gridview)
    private GridView gridview;
    @ViewInject(R.id.horizon_listview)
    private HorizontalListView horizon_listview;
    @ViewInject(R.id.tv_jiaobiao)
    private TextView tv_jiaobiao;
    @ViewInject(R.id.jiesuan)
    private Button jiesuan;
    @ViewInject(R.id.my_dingdan)
    private Button my_dingdan;
    private HorizontalListViewAdapter hListViewAdapter;
    private List<ShangpinListEntity> data;
    private boolean isCunzai;


    @OnClick({R.id.jiesuan, R.id.my_dingdan, R.id.rl_jiaobia})
    private void onClick(View v) {
        try {
            switch (v.getId()) {

                case R.id.jiesuan:
                    if (listEntity.size() != 0) {
                        Intent intent = new Intent(getActivity(), ShangchengJiesuanActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listEntity", (Serializable) listEntity);
                        intent.putExtras(bundle);
//                        toActivity(intent);
                        startActivityForResult(intent, 200);
                        //得到新打开Activity关闭后返回的数据
                        //第二个参数为请求码，可以根据业务需求自己编号

                    } else {
                        showToast("请选取购买的商品");

                    }
                    break;
                case R.id.rl_jiaobia:
                    if (listEntity.size() != 0) {
                        Intent intent = new Intent(getActivity(), ShangchengJiesuanActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listEntity", (Serializable) listEntity);
                        intent.putExtras(bundle);
//                        toActivity(intent);
                        startActivityForResult(intent, 200);
                        //得到新打开Activity关闭后返回的数据
                        //第二个参数为请求码，可以根据业务需求自己编号

                    } else {
                        showToast("请选取购买的商品");

                    }
                    break;
                case R.id.my_dingdan:
                    toActivity(ShangchengMydingdanActivity.class);
                    break;
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     * <p>
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
//        listEntity= (ArrayList<ShangpinListEntity>)context.getIntent().getSerializableExtra("listEntity");
//        hListViewAdapter.notifyDataSetChanged();
//    }
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {

            if (requestCode == 1) {
                ShangpinListResult result = GsonUtils.getSingleBean(jsonStr, ShangpinListResult.class);
                if (result.isSuccess()) {
                    setData(result.data);
                }
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {

        //获取商品列表
        postDialog(Constant.LIST_PRODUCT, null, 1, true);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xinpai_online_fragment;
    }

    @Override
    protected void initView(View contentView) {
        EventBus.getDefault().register(this);
        ViewUtils.inject(this, contentView);
        horizon_listview.setVisibility(View.GONE);
        hListViewAdapter = new HorizontalListViewAdapter(listEntity, getActivity());
    }

    public void setData(final List<ShangpinListEntity> data) {
        this.data = data;
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消GridView中Item选中时默认的背景色
        ShangpinListAdapter adapter = new ShangpinListAdapter(data, this);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                addPic(data.get(position));
                Intent intent = new Intent(context, ShangpinDetailActivity.class);
                intent.putExtra("shangpin", data.get(position));
                startActivity(intent);
            }
        });
    }

    ArrayList<ShangpinListEntity> listEntity = new ArrayList<ShangpinListEntity>();

    public void addPic(ShangpinListEntity entity) {

        if (listEntity.size() == 0) {
            listEntity.add(entity);
            HorizontalListViewAdapter hListViewAdapter2 = new HorizontalListViewAdapter(listEntity, getActivity());
            horizon_listview.setAdapter(hListViewAdapter2);
        } else {
            int newId = entity.getProductID();
            for (int i = 0; i < listEntity.size(); i++) {
                int oldId = listEntity.get(i).getProductID();
                int count = listEntity.get(i).getMyNumber();
                if (newId == oldId) {
                    isCunzai = true;
                    entity.setMyNumber(count + 1);
                    break;
                } else {
                    isCunzai = false;
                }
            }

            if (!isCunzai) {
                listEntity.add(entity);
                HorizontalListViewAdapter hListViewAdapter2 = new HorizontalListViewAdapter(listEntity, getActivity());
                horizon_listview.setAdapter(hListViewAdapter2);
            }
        }


        //更新horlistview //横向listview
        if (listEntity.size() != 0) {
            horizon_listview.setVisibility(View.VISIBLE);
            tv_jiaobiao.setVisibility(View.VISIBLE);
            tv_jiaobiao.setText(listEntity.size() + "");
        }
    }

    //    private ACache aCache;
    @Override
    public void onResume() {
        super.onResume();
//获取商品列表
        postDialog(Constant.LIST_PRODUCT, null, 1);

       /* aCache = ACache.get(context);
        ArrayList<ShangpinListEntity> listEntity2 = (ArrayList<ShangpinListEntity>) aCache.getAsObject("listEntity");
        if (!listEntity.toString().equals(listEntity2.toString())){
            //更新horlistview //横向listview
            if (listEntity2.size()!=0){
                horizon_listview.setVisibility(View.VISIBLE);
                tv_jiaobiao.setVisibility(View.VISIBLE);
                tv_jiaobiao.setText(listEntity2.size() + "");

                HorizontalListViewAdapter hListViewAdapter2 = new HorizontalListViewAdapter(listEntity2, getActivity());
                horizon_listview.setAdapter(hListViewAdapter2);
            }else {
                horizon_listview.setVisibility(View.GONE);
                tv_jiaobiao.setVisibility(View.GONE);
            }
        }*/
    }

    public void UpdateUI(Intent data) {
        listEntity.clear();
        listEntity = (ArrayList<ShangpinListEntity>) data.getSerializableExtra("listEntity");
        //更新horlistview //横向listview
        if (listEntity.size() != 0) {
            horizon_listview.setVisibility(View.VISIBLE);
            tv_jiaobiao.setVisibility(View.VISIBLE);
            tv_jiaobiao.setText(listEntity.size() + "");

            HorizontalListViewAdapter hListViewAdapter2 = new HorizontalListViewAdapter(listEntity, getActivity());
            horizon_listview.setAdapter(hListViewAdapter2);
        } else {
            horizon_listview.setVisibility(View.GONE);
            tv_jiaobiao.setVisibility(View.GONE);
        }
    }

    /**
     * 接收EventBus事件，更新信息
     *
     * @param event
     */
    @Subscribe
    public void addTolistEvent(AddToListEvent event) {
        addPic(event.getData());
    }
}