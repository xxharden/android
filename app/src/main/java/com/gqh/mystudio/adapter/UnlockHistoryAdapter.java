package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.UnlockHisturyEntity;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * Created by 红哥哥 on 2016/7/19.
 */
public class UnlockHistoryAdapter extends MyBaseAdapter<UnlockHisturyEntity> {

    public UnlockHistoryAdapter(List<UnlockHisturyEntity> mList, Context context) {
        super(mList, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_listview_unlock_history,null);
            mHolder=new ViewHolder();
            mHolder.tv_open_time= (TextView) convertView.findViewById(R.id.tv_open_time);
            mHolder.tv_door_name= (TextView) convertView.findViewById(R.id.tv_door_name);
            convertView.setTag(mHolder);
        }else {
            mHolder= (ViewHolder) convertView.getTag();
        }

        UnlockHisturyEntity entity = mList.get(position);

        mHolder.tv_open_time.setText(entity.getCreateTime());
        mHolder.tv_door_name.setText(entity.getDoorName());
        return convertView;
    }
    private static final class ViewHolder{
        TextView tv_open_time;
        TextView tv_door_name;
    }
}
