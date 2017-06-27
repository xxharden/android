package com.gqh.mystudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gqh.mystudio.R;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc:
 */
public class FabuPhotoGrideviewAdapter extends MyBaseAdapter<Bitmap> {


    List<Bitmap> mList;
    Context context;
    int style;

    private LayoutInflater mInflater;
    public FabuPhotoGrideviewAdapter(List<Bitmap> mList, Context context,int style) {//Constant.STYLE_ADAPTER_OK显示全部
        super(mList, context);
        this.context=context;
        this.mList=mList;
        this.style=style;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if (style== Constant.STYLE_ADAPTER_OK){
            return mList.size();
        }else {
            if (mList.size()<3){
                return mList.size();
            }else {
                return 3;
            }
        }


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Bitmap entity = mList.get(position);
        ImageView iv = null;
        if (convertView==null){
            convertView =mInflater.inflate(R.layout.item_gridview_photo_3, null) ;
//            ((ImageView) convertView).setScaleType(ImageView.ScaleType.FIT_XY);
        }
        iv = (ImageView) convertView.findViewById(R.id.iv_photo);

        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        int nh = (int) ( entity.getHeight() * (512.0 / entity.getWidth()) );
        Bitmap scaled = Bitmap.createScaledBitmap(entity, 512, nh, true);
        iv.setImageBitmap(entity);


        return convertView;
    }
}
