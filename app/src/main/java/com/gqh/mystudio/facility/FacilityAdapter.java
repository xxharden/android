package com.gqh.mystudio.facility;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gqh.mystudio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class FacilityAdapter extends BaseAdapter {

    private Activity mContext;
    private List<DeviceListResponseEntity.Device> facilities = new ArrayList<>();

    public FacilityAdapter(Activity context, List<DeviceListResponseEntity.Device> fs) {
        this.mContext = context;
        this.facilities = fs;
    }

    @Override
    public int getCount() {
        return facilities.size();
    }

    @Override
    public Object getItem(int position) {
        return facilities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DeviceListResponseEntity.Device device = facilities.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_facility, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.item_zys_iv);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.item_zys_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext.getApplicationContext()).load(device.picUrl).into(viewHolder.iv);
        String templateId = device.templateId;
        String[] split = templateId.split("//");
        viewHolder.tv.setText(split[2].concat(split[3]));
        return convertView;
    }

    class ViewHolder{
        CheckBox cb;
        ImageView iv;
        TextView tv;
    }
}
