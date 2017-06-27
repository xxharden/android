package com.gqh.mystudio.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.fragment.ShouYeFragment;
import com.gqh.mystudio.fragment.WoDeFangjianFragment;
import com.gqh.mystudio.fragment.XinPaiOnlineFragment;
import com.gqh.mystudio.fragment.ZhiNengshenghuoFragment;
import com.gqh.mystudio.fragment.ZuoLinYouSheFragment;
import com.gqh.mystudio.jpush.ExampleUtil;

import cn.jpush.android.api.JPushInterface;
import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.entity.UserResult;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 主界面 （fragment容器）
 */
public class MainActivity extends BaseHttpActivity {
    public static boolean isForeground = false;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg) {
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//        }
    }


    Fragment[] fragmentArray = new Fragment[5];
    Button[] buttonArray = new Button[5];
    int currentFragmentIndex = 2;
    int selectFragmentIndex = 2;
    private ShouYeFragment shouyeFragment;
    private ZuoLinYouSheFragment zuolinyousheFragment;
    private WoDeFangjianFragment wodeFragment;
    private ZhiNengshenghuoFragment zhiNengFragement;
    private XinPaiOnlineFragment xinPaiOnlineFragment;
    private FragmentTransaction transaction;
    private String phone;
    private ACache aCache;
    private String userStyle;

    @Override
    protected void onPause() {
        isForeground = false;
        JPushInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        aCache = ACache.get(context);
        phone = getIntent().getStringExtra("phone");
        userStyle = getIntent().getStringExtra("quanxian");
        BamsApplication.getInstance().setUserStyle(userStyle);//存全局
        setupview();
        addListener();
        registerMessageReceiver();  // used for receive msg
        init();

        int index = BamsApplication.getInstance().getBackIndex();
        if (index == 4) {
            selectFragmentIndex = 4;
            change();
            BamsApplication.getInstance().setBackIndex(0);
        }
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init() {
        JPushInterface.init(getApplicationContext());
    }


    public void toStartFragment(int k) {
        selectFragmentIndex = k;
        change();
    }

    private void setupview() {
        shouyeFragment = new ShouYeFragment();
        zuolinyousheFragment = new ZuoLinYouSheFragment();
        wodeFragment = new WoDeFangjianFragment();
        zhiNengFragement = new ZhiNengshenghuoFragment();
        xinPaiOnlineFragment = new XinPaiOnlineFragment();
        fragmentArray[0] = zuolinyousheFragment;
        fragmentArray[1] = xinPaiOnlineFragment;
        fragmentArray[2] = shouyeFragment;
        fragmentArray[3] = zhiNengFragement;
        fragmentArray[4] = wodeFragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, shouyeFragment);
        transaction.show(shouyeFragment);
        transaction.commit();

        buttonArray[0] = (Button) findViewById(R.id.btn_main_fragment_zuoyou);
        buttonArray[1] = (Button) findViewById(R.id.btn_main_fragment_online);
        buttonArray[2] = (Button) findViewById(R.id.btn_main_fragment_shouye);
        buttonArray[3] = (Button) findViewById(R.id.btn_main_fragment_zhineng);
        buttonArray[4] = (Button) findViewById(R.id.btn_main_fragment_wode);

        buttonArray[currentFragmentIndex].setSelected(true);
    }


    public void addListener() {
        for (int i = 0; i < buttonArray.length; i++) {
            MyListener myListener = new MyListener();
            buttonArray[i].setOnClickListener(myListener);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode == 2) {
                UserResult result = GsonUtils.getSingleBean(jsonStr, UserResult.class);
                if (result.isSuccess()) {

                    BamsApplication.getInstance().setUser(result.data);//存全局
                    aCache.put("user", result.data);//存缓存

                    MyLog.i("wg", "全局 帐号 ： " + BamsApplication.getInstance().getUser().getCustID());

                    if (aCache.getAsObject("user") != null) {
                        User use = (User) aCache.getAsObject("user");
                        MyLog.i("wg", "缓存 帐号 ： " + use.getCustID());
                    }
                }
            }
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        if (!userStyle.equals(Constant.STYLE_FANGKE)) {
            //发送获取用户信息请求
            RequestParams params2 = new RequestParams();
            params2.put("phone", phone);
            postDialog(Constant.GET_USER_INFO, params2, 2, false);
        }
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.btn_main_fragment_zuoyou:
                        if (userStyle.equals(Constant.STYLE_FANGKE)) {
                            showToast("入住新派公寓，方可点击查看");
                        } else {
                            selectFragmentIndex = 0;
                        }
                        break;

                    case R.id.btn_main_fragment_online:

                        if (userStyle.equals(Constant.STYLE_FANGKE)) {
                            showToast("入住新派公寓，方可点击查看");
                            break;
                        } else {
                            selectFragmentIndex = 1;
                            break;
                        }


                    case R.id.btn_main_fragment_shouye:
                        selectFragmentIndex = 2;
                        break;


                    case R.id.btn_main_fragment_zhineng:
                        if (userStyle.equals(Constant.STYLE_FANGKE)) {
                            showToast("入住新派公寓，方可点击查看");
                            break;
                        } else {
                            selectFragmentIndex = 3;
                            break;
                        }


                    case R.id.btn_main_fragment_wode:
                        if (userStyle.equals(Constant.STYLE_FANGKE)) {
                            showToast("入住新派公寓，方可点击查看");
                            break;
                        } else {
                            selectFragmentIndex = 4;
                            break;
                        }
                }

                change();
            } catch (Exception e) {
                ExceptionUtil.handleException(e);
            }
        }
    }

    public void change() {
        if (selectFragmentIndex != currentFragmentIndex) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //单击了别的按钮，显示别的fragment
            fragmentTransaction.hide(fragmentArray[currentFragmentIndex]);
            if (!fragmentArray[selectFragmentIndex].isAdded()) {
                //没有添加过
                fragmentTransaction.add(R.id.fragment_container, fragmentArray[selectFragmentIndex]);
            }
            fragmentTransaction.show(fragmentArray[selectFragmentIndex]);
            fragmentTransaction.commit();
            buttonArray[currentFragmentIndex].setSelected(false);
            buttonArray[selectFragmentIndex].setSelected(true);
            currentFragmentIndex = selectFragmentIndex;
        }
    }

    long temptime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getAction() == KeyEvent.ACTION_DOWN)) {
            if (System.currentTimeMillis() - temptime > 2000) // 2s内再次选择back键有效
            {
                showToast("再按一下将退出程序");
                temptime = System.currentTimeMillis();
            } else {
                finish();
                BamsApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        isForeground = true;
        JPushInterface.onResume(this);
        int index = BamsApplication.getInstance().getBackIndex();
        if (index == 1) {
            selectFragmentIndex = 1;
            change();
            BamsApplication.getInstance().setBackIndex(0);
        }

        super.onResume();
    }

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     * <p>
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        xinPaiOnlineFragment.UpdateUI(data);
    }
}
