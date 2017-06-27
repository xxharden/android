
package cn.myapp.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.gqh.mystudio.R;

import cn.myapp.util.StringUtil;


/**
 * 需要通用标题的继承这个类
 * 
 * @author xiaoyang
 */
public abstract class BaseTitleFragment extends BaseFragment {

    private RelativeLayout titleView;
    private RelativeLayout contentView;
    private RelativeLayout leftView;
    private RelativeLayout rightView;
    protected View loadingView;
    protected View nodataView;
    protected View failureView;
    private RelativeLayout centerView;
    private TextView titleTv;
    protected FragmentActivity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.context = getActivity();
        View view = inflater.inflate(R.layout.base_main_layout, null);
        initMainView(view);
        initView(contentView);
        initHttp();
        return view;
    }

    /**
     * 初始化共用view
     * 
     * @param view
     * @param contentView
     */
    private void initMainView(View view) {
        titleView = (RelativeLayout) view.findViewById(R.id.title_bar);
        titleTv = (TextView) view.findViewById(R.id.title_bar_tv);

        contentView = (RelativeLayout) view
                .findViewById(R.id.main_content);
        leftView = (RelativeLayout) view.findViewById(R.id.title_bar_left);
        rightView = (RelativeLayout) view.findViewById(R.id.title_bar_right);
        centerView = (RelativeLayout) view.findViewById(R.id.title_bar_center);

        loadingView = view.findViewById(R.id.loading_layout);
        nodataView = view.findViewById(R.id.nodata_layout);
        failureView = view.findViewById(R.id.failure_layout);

        loadingView.setVisibility(View.GONE);
        nodataView.setVisibility(View.GONE);
        failureView.setVisibility(View.GONE);

        View child = inflater.inflate(getContentView(), null);
        contentView.addView(child);
    }

    /**
     * 设置标题view 用特殊的标题
     * 
     * @param titleStr
     */
    protected void setTitleView(View view) {
        if (titleView != null) {
            titleView.removeAllViews();
            titleView.addView(view);
        }
    }

    /**
     * 隐藏标题
     */
    protected void hideTitle() {
        if (titleView != null) {
            titleView.setVisibility(View.GONE);
        }
    }
    
    /**
     * 设置标题
     * 
     * @param titleStr
     */
    protected void setTitleStr(String titleStr) {
        if (titleTv != null && StringUtil.isNotEmpty(titleStr)) {
        	if(titleStr.length( )>10){
        		titleStr = titleStr.substring( 0, 9 )+"...";
        		titleTv.setText(titleStr);
        	}else{
        		titleTv.setText(titleStr);
        	}
        }
    }

    /**
     * 设置标题栏中间显示什么
     * 
     * @param view
     */
    protected void setTitleCenterView(View view) {
        if (centerView != null) {
            centerView.removeAllViews();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            centerView.addView(view, layoutParams);
        }
    }

    /**
     * 设置标题栏右边显示什么
     * 
     * @param view
     */
    protected void setTitleRightView(View view) {
        if (rightView != null) {
            rightView.removeAllViews();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            rightView.addView(view, layoutParams);
        }
    }

    /**
     * 设置标题栏左边显示什么
     * 
     * @param view
     */
    protected void setTitleLeftView(View view) {
        if (leftView != null) {
            leftView.removeAllViews();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            leftView.addView(view, layoutParams);
        }
    }

    /**
     * 返回fragment的布局view
     * 
     * @return
     */
    protected abstract int getContentView();

    /**
     * 子类负责初始话控件
     * 
     * @param contentView
     */
    protected abstract void initView(View contentView);

    /**
     * 初始化http请求的东西
     */
    protected abstract void initHttp();


}
