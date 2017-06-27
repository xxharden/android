package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.gqh.mystudio.R;

import java.util.List;


/**
 * @author : 红仔
 * @date : 2016/3/9
 * desc:
 */
public class GroupPopupAdapter  extends BaseAdapter{
    private Context context;

    private List<String> list;

    public GroupPopupAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder=null;
        if (convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.item_listview_item_xiala, null);
            holder=new ViewHolder();

            convertView.setTag(holder);

            holder.groupItem=(TextView) convertView.findViewById(R.id.groupItem);

        } else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.groupItem.setText(list.get(position));

        return convertView;
    }

    private static class ViewHolder {
        TextView groupItem;
    }
}
