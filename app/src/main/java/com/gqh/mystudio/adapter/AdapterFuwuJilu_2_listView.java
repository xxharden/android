package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.Guanjia_PingjiaActivity;
import com.gqh.mystudio.entity.FuwuEntity;


import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.StringUtil;

/**
 * @author : 红仔
 * @date : 2016/2/25
 * desc:
 */
public class AdapterFuwuJilu_2_listView extends MyBaseAdapter<FuwuEntity> {

    private HoldView holdView;
    private List<FuwuEntity> mList;
    private Context context;
    private LayoutInflater mInflater;
    public AdapterFuwuJilu_2_listView(List<FuwuEntity> mList, Context context) {
        super(mList, context);
        this.context=context;
        this.mList=mList;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holdView = null;
        if (convertView == null) {
            holdView = new HoldView();
            convertView = mInflater.inflate(R.layout.item_listview_fuwu_jilu_2, null);

            holdView.iv_tupian= (ImageView) convertView.findViewById(R.id.baojie_photo);
            holdView.tv_name= (TextView) convertView.findViewById(R.id.baojie_name);
            holdView.tv_time= (TextView) convertView.findViewById(R.id.tv_time_baojie);
            holdView.tv_style= (TextView) convertView.findViewById(R.id.tv_style_baojie);
            holdView.bt_pingjia= (Button) convertView.findViewById(R.id.bt_pingjia);
            convertView.setTag(holdView);

        }else {
            holdView = (HoldView)convertView.getTag();
        }

        final FuwuEntity fuwuEntity = getItem(position);
        if(!StringUtil.isEmpty(fuwuEntity.getOrderType())){
            holdView.tv_name.setText(fuwuEntity.getOrderType());

            if(fuwuEntity.getOrderType().equals("保洁")){
                holdView.iv_tupian.setImageResource(R.drawable.guanjia_baojie);
            }else if(fuwuEntity.getOrderType().equals("报修")){
                holdView.iv_tupian.setImageResource(R.drawable.guanjia_weixiu);
            }else if(fuwuEntity.getOrderType().equals("投诉")){
                holdView.iv_tupian.setImageResource(R.drawable.guanjia_tousu);
            }
        }else {

        }

        if(!StringUtil.isEmpty(fuwuEntity.getCREATETime())){
            holdView.tv_time.setText(fuwuEntity.getCREATETime());
        }else {

        }

        if(!StringUtil.isEmpty(fuwuEntity.getSTATUS())){
//            未处理，处理中，完成
            String score = fuwuEntity.getScore();
            if(fuwuEntity.getSTATUS().equals("处理中")|fuwuEntity.getSTATUS().equals("未处理")|(score!=null|(!score.equals("")))){
                holdView.tv_style.setVisibility(View.VISIBLE);
                holdView.bt_pingjia.setVisibility(View.GONE);
            }else if(fuwuEntity.getSTATUS().equals("完成")&&(score==null|score.equals(""))){
                holdView.bt_pingjia.setVisibility(View.VISIBLE);
                holdView.tv_style.setVisibility(View.GONE);
            }
            holdView.tv_style.setText(fuwuEntity.getSTATUS());
        }else {

        }

        holdView.bt_pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转评价界面
                Intent intent=new Intent(context, Guanjia_PingjiaActivity.class);
                intent.putExtra("WorkOrderID",fuwuEntity.getWorkOrderID());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class HoldView{
        ImageView iv_tupian;
        TextView tv_name;
        TextView tv_time;
        TextView tv_style;
        Button bt_pingjia;
    }
}

