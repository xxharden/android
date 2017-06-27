package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.DaijiaofeiEntity;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * @author : 红仔
 * @date : 2016/3/14
 * desc:
 */
public class DaijiaofeiAdapter extends MyBaseAdapter<DaijiaofeiEntity>{

    private Context context;
    private List<DaijiaofeiEntity> mList;
    private LayoutInflater mInflater;
    public DaijiaofeiAdapter(List<DaijiaofeiEntity> mList, Context context) {
        super(mList, context);
        this.context=context;
        this.mList=mList;
        mInflater=LayoutInflater.from(context);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holder = null;
        DaijiaofeiEntity entity = mList.get(position);
        if (convertView==null){
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_listview_xinpaibi_jiaofei, null);
            holder.tv_style= (TextView) convertView.findViewById(R.id.tv_style);
            holder.tv_feiyong= (TextView) convertView.findViewById(R.id.tv_feiyong);
            holder.image_check= (CheckBox) convertView.findViewById(R.id.image_check);
            convertView.setTag(holder);
        }else {

            holder= (HoldView) convertView.getTag();
        }

        holder.tv_style.setText(entity.getFeeItem());
        holder.tv_feiyong.setText("费用：" + entity.getAmount());

        return convertView;
    }
    private static class HoldView{
        TextView tv_style;
        TextView tv_feiyong;
        CheckBox image_check;
    }
}
