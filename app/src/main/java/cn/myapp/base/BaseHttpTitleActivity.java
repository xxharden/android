
package cn.myapp.base;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.gqh.mystudio.R;

import cn.myapp.util.StringUtil;


public abstract class BaseHttpTitleActivity extends BaseActivity {


    private RelativeLayout titleView;
    private LinearLayout ll;
    private TextView titleTv;
    private RelativeLayout leftView;
    private RelativeLayout rightView;
    private RelativeLayout centerView;
    protected View loadingView;
    protected View vi;
    protected View nodataView;
    protected View failureView;
    private RelativeLayout contentView;
    @Override
    protected void onCreate(Bundle arg0) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        setTheme(R.style.ActionSheetStyleIOS7);

        setContentView(R.layout.base_main_layout);
        initMainView(inflater.inflate(getContentView(), null));
        initView();
        initHttp();
    }

    /**
     * 初始化共用view
     * 
     * @param view
     * @param contentView
     */
    private void initMainView(View child) {
    	ll=(LinearLayout)findViewById(R.id.layout_zj);
    	vi=(View)findViewById(R.id.view_zj);
    	
        titleView = (RelativeLayout) findViewById(R.id.title_bar);
        titleTv = (TextView) findViewById(R.id.title_bar_tv);

        contentView = (RelativeLayout) findViewById(R.id.main_content);
        leftView = (RelativeLayout) findViewById(R.id.title_bar_left);
        rightView = (RelativeLayout) findViewById(R.id.title_bar_right);
        centerView = (RelativeLayout) findViewById(R.id.title_bar_center);

        loadingView = findViewById(R.id.loading_layout);
        nodataView = findViewById(R.id.nodata_layout);
        failureView = findViewById(R.id.failure_layout);

        loadingView.setOnClickListener( new OnClickListener( ) {
			@Override
			public void onClick( View v ) {
			}
		} );
        
        loadingView.setVisibility(View.GONE);
        nodataView.setVisibility(View.GONE);
        failureView.setVisibility(View.GONE);

        contentView.addView(child, new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        
        
        View view = inflater.inflate(R.layout.back_left, null);
		setTitleLeftView(view);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leftClick( v );
				
			}
		});
    }

    /**
     * 左边按钮的点击事件，有特殊需要的重写这个方法
     * @param v
     */
    protected void leftClick(View v){
    	finish();
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
     * 显示总计
     */
    protected void see() {
     
            ll.setVisibility(View.VISIBLE);
            vi.setVisibility(View.VISIBLE);
        
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
            centerView.addView(view);
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
            rightView.addView(view);
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
            leftView.addView(view);
        }
    }

    
    /**
     * 返回activity的布局view
     * 
     * @return
     */
    protected abstract int getContentView();

    /**
     * 子类负责初始话控件
     */
    protected abstract void initView();

    /**
     * 初始化http请求的东西
     */
    protected abstract void initHttp();

   
    
}
