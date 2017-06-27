package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.PictureShowActivity;
import com.gqh.mystudio.entity.ShaiDetaiShowEntity;
import com.gqh.mystudio.utill.BitmapUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;

/**
 * @author : 红仔
 * @date : 2016/3/9
 * desc:
 */
public class ShaiDetailAdapter extends MyBaseAdapter<ShaiDetaiShowEntity> {


    private Context context;
    private List<ShaiDetaiShowEntity>mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;
    public ShaiDetailAdapter(List<ShaiDetaiShowEntity> mList, Context context) {
        super(mList, context);

        mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mList=mList;

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
//				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//				.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ShaiDetaiShowEntity entity = mList.get(position);
        HoldView holder = null;
        if (convertView == null) {
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_listview_shai_wode_shai,null);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_dongtai= (TextView) convertView.findViewById(R.id.tv_dongtai);
            holder.gridview= (GridView) convertView.findViewById(R.id.gridview);
            convertView.setTag(holder);
        }else {

            holder= (HoldView) convertView.getTag();
        }


        holder.tv_time.setText(entity.getCreateTime());
        holder.tv_dongtai.setText(entity.getShowCont());

        String strPic=entity.getShowPic();

        MyLog.i("picId=", strPic.toString());
        final List<String> List=new ArrayList<String>();
        if (strPic.indexOf(";")==-1){
            List.add(Constant.IMAGE_URL + strPic);
        }else {
            String[] list = strPic.split(";");
            for (int i=0;i<list.length;i++){
                List.add(Constant.IMAGE_URL+list[i]);
            }
        }
        MyLog.i("mList=", List.toString());
        GrideviewAdapter gvAdapter=new GrideviewAdapter(List,context,Constant.STYLE_ADAPTER_NO);
        holder.gridview.setAdapter(gvAdapter);

        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(context, PictureShowActivity.class);
                intent.putExtra("picId",List.get(position));
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class HoldView{
        TextView tv_time;
        TextView tv_dongtai;
        GridView gridview;
    }
}
