package com.gqh.mystudio.adapter;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.MyMessageListEntity;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * @author : 红仔
 * @date : 2016/3/17
 * desc:
 */
public class MyMessageListAdapter extends MyBaseAdapter<MyMessageListEntity>{


    public MyMessageListAdapter(List<MyMessageListEntity> mList, Context context) {
        super(mList, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyMessageListEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_my_message_list,null);
            holder.tv= (TextView) convertView.findViewById(R.id.tv);
            holder.ll_more= (LinearLayout) convertView.findViewById(R.id.ll_more);
            holder.tv_more= (TextView) convertView.findViewById(R.id.tv_more);
            holder.iv_more= (ImageView) convertView.findViewById(R.id.iv_more);
            convertView.setTag(holder);
        }else {

            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(entity.getContent());
        final ViewHolder finalHolder = holder;


        holder.ll_more.setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    finalHolder.tv_more.setText("收起");
                    finalHolder.iv_more.setImageResource(R.mipmap.ic_shrink_up);
                    finalHolder.tv.setEllipsize(null); // 展开
                    finalHolder.tv.setSingleLine(flag);
                }else{
                    flag = true;
                    finalHolder.tv_more.setText("展开");
                    finalHolder.iv_more.setImageResource(R.mipmap.ic_details_more);
                    finalHolder.tv.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                    finalHolder.tv.setMaxLines(3);
                }
            }
        });

        return convertView;
    }
    private static class ViewHolder{
        TextView tv;
        TextView tv_more;
        ImageView iv_more;
        LinearLayout ll_more;
    }
}
