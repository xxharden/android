package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.JiaofeiJiluEntity;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * @author : 红仔
 * @date : 2016/3/15
 * desc:
 */
public class JiaofeiJiluAdapter extends MyBaseAdapter<JiaofeiJiluEntity> {

    List<JiaofeiJiluEntity> mList;
    Context context;
    LayoutInflater mInflayter;
    public JiaofeiJiluAdapter(List<JiaofeiJiluEntity> mList, Context context) {
        super(mList, context);
        this.context=context;
        this.mList=mList;
        mInflayter=LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JiaofeiJiluEntity entity = mList.get(position);
        HoldView holder = null;
        if (convertView == null) {
            holder = new HoldView();
            convertView = mInflater.inflate(R.layout.item_listview_jiaofei_list, null);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_totalFee= (TextView) convertView.findViewById(R.id.tv_totalFee);
            convertView.setTag(holder);
        }else {

            holder= (HoldView) convertView.getTag();
        }
        holder.tv_date.setText(entity.getBusiDate());
        holder.tv_totalFee.setText("总缴费："+entity.getTotalFee());
        return convertView;
    }
    private static class HoldView{
        TextView tv_date;
        TextView tv_totalFee;
    }
}
