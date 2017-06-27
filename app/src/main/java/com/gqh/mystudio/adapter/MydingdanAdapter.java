package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.MyOrderEntity;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * @author : 红仔
 * @date : 2016/3/20
 * desc:
 */
public class MydingdanAdapter extends MyBaseAdapter<MyOrderEntity> {

    public MydingdanAdapter(List<MyOrderEntity> mList, Context context) {
        super(mList, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyOrderEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_listview_my_order,null);

            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_totalfee= (TextView) convertView.findViewById(R.id.tv_totalfee);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText("收货人："+entity.getUNickName());
        holder.tv_totalfee.setText("总费用："+entity.getTotalFee());
        holder.tv_time.setText("下单时间："+entity.getOrderTime());
        return convertView;
    }

    private static class ViewHolder{
        TextView tv_name;
        TextView tv_totalfee;
        TextView tv_time;
    }
}
