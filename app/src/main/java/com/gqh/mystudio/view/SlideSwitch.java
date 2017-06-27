package com.gqh.mystudio.view;

/**
 * @author : 红仔
 * @date : 2016/3/31
 * desc:
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.gqh.mystudio.R;

/**
 * 自定义滑动开关，用于替代3.0以上版本中的switch开关
 *
 * @author longmap
 *
 *         使用改控件必须在xml中配置以下属性，否则会报错
 *         android:layout_width     最外层宽
 *         android:layout_height    最外层高
 *         myview:background        底层图片背景
 *         myview:btn_background    按钮图片背景
 *
 *         以下属性为可选，主要是调整控件达到合适的大小
 *         myview:mheight           控件高
 *         myview:mwidth            控件宽
 *         myview:mbt_width         上层滑动按钮宽
 *
 */
public class SlideSwitch extends View implements OnTouchListener {

    /*
     * 上下文
     */
    private Context context;
    /*
     * 按下时的x坐标
     */
    private int downX;
    /*
     * 当前位置
     */
    private int lastX;
    /*
     * 手指移动距离
     */
    private int dis;
    /*
     * 底层背景
     */
    private Bitmap bgBmp;
    /*
     * 上层按钮背景
     */
    private Bitmap btnBmp;

    /*
     * 画笔
     */
    private Paint paint;
    /*
     * 开关状态
     */
    private boolean switchState = false;
    /*
     * 上层按钮距离左边界距离
     */
    private int btnLeft = 0;
    /*
     * 标示是不是点击事件
     */
    // private boolean isClick = true;
    /*
     * 最大滑动距离
     */
    private int juli;
    /*
     * 控件宽
     */
    private int mWidth;
    /*
     * 控件高
     */
    private int mHeight;
    /*
     * 控件上按钮的宽
     */
    private int mbtWidth;

    public SlideSwitch(Context context) {
        super(context);

    }

    public SlideSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();

        initAttrs(attrs);

    }

    /**
     * 测量控件大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 绘组件
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgBmp, 0, 0, paint);
        canvas.drawBitmap(btnBmp, btnLeft, 0, paint);
    }

    /**
     * 初始化
     */
    private void init() {
        // 画笔
        paint = new Paint();
        // 开启抗锯齿
        paint.setAntiAlias(true);
        setOnTouchListener(this);
    }

    /**
     * 获取xml配置文件中的属性值
     *
     * @param attrs
     */
    public void initAttrs(AttributeSet attrs) {
        TypedArray te = getContext().obtainStyledAttributes(attrs,
                R.styleable.SlideSwitch);

        // 获取xml中图片的id
        int bgId = te.getResourceId(R.styleable.SlideSwitch_background_back, 0);
        int btnbgId = te.getResourceId(R.styleable.SlideSwitch_btn_background,
                0);
        // 根据id获取图片
        bgBmp = BitmapFactory.decodeResource(getResources(), bgId);
        btnBmp = BitmapFactory.decodeResource(getResources(), btnbgId);

        // 获取控件尺寸信息
        mWidth = (int) te.getDimension(R.styleable.SlideSwitch_mwidth, 0f);
        mHeight = (int) te.getDimension(R.styleable.SlideSwitch_mheight, 0f);
        mbtWidth = (int) te.getDimension(R.styleable.SlideSwitch_mbt_width, 0f);

        //如果mwidth属性未配置
        if (mWidth == 0f) {
            mWidth = bgBmp.getWidth();
        }

        //如果mheight属性未配置
        if (mHeight == 0f) {
            mHeight = bgBmp.getHeight();
        }

        //如果mbt_width属性未配置
        if (mbtWidth == 0f) {
            mbtWidth = btnBmp.getWidth();
        }


        // 按xml中设置的尺寸改变bitmap
        bgBmp = Bitmap.createScaledBitmap(bgBmp, mWidth, mHeight, true);
        btnBmp = Bitmap.createScaledBitmap(btnBmp, mbtWidth, mHeight, true);
        // 计算最大滑动距离
        juli = mWidth - mbtWidth;

        te.recycle();
    }

    /**
     * 滑动事件
     */
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            // 按下
            case MotionEvent.ACTION_DOWN: {
//                downX = lastX = (int) event.getX();
                break;
            }
            // 移动
            case MotionEvent.ACTION_MOVE: {
                // 计算手指在屏幕上的移动距离
                dis = (int) (event.getX() - lastX);
                // 将本次的位置 设置给lastX
                lastX = (int) event.getX();
                // 根据移动距离计算左边距值
                btnLeft = btnLeft + dis;

                change();

                break;
            }
            // 抬起
            case MotionEvent.ACTION_UP: {
                lastX = (int) event.getX();
                changeState();
                break;
            }
        }
        return true;
    }

    /**
     * 改变按钮状态
     */
    public void changeState() {
        // 抬起时坐标与按下时坐标相对距离
        int x = lastX - downX;
        // 如果是点击 距离小于20即可视为点击
        if ((x > -20 && x <= 0) || (x < 20 && x >= 0)) {
            if (switchState) {
                btnLeft = 0;
            } else {
                btnLeft = juli;
            }
            switchState = !switchState;
        }

        // 如果滑动距离大于最大距离的一半，则自动完成状态转换,否则状态不变
        if (btnLeft >= juli / 4*3) {
            btnLeft = juli;
            switchState = true;
        } else if (btnLeft < juli / 4*3) {
            btnLeft = 0;
            switchState = false;
        }


        if (mListener != null) {
            mListener.onChange(this, switchState);
        }

        invalidate();

        dis = 0;
    }

    /**
     * 改变按钮位置
     */
    public void change() {

        // 如果滑动距离超过最大距离
        if (btnLeft > juli) {
            btnLeft = juli;
        }
        if (btnLeft < 0) {
            btnLeft = 0;
        }

        invalidate();
    }

    /*
     * 设置监听事件
     */
    private OnChangeListener mListener = null;

    public void setOnChangeListener(OnChangeListener listener) {
        mListener = listener;
    }


    /**
     * 监听时的接口
     *
     * @author longmap
     */
    public interface OnChangeListener {
        public void onChange(SlideSwitch sw, boolean state);
    }

    /**
     * 设置滑动开关的初始状态，供外部调用
     * @param checked
     */
    public void setChecked(boolean checked){
        if(checked){
            lastX = mWidth;
        }else{
            lastX = 0;
        }
        //刷新界面
        change();
        changeState();
    }
}
