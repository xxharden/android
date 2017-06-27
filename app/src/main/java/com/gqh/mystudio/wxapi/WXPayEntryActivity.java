package com.gqh.mystudio.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;

/**
 * Created by LinYi.
 * <p/>微信客户端回调给我们支付结果接收
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 0    成功  展示成功页面<br/>
     * -1   错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。<br/>
     * -2   用户取消    无需处理。发生场景：用户不支付了，点击取消，返回APP。<br/>
     * <p/>
     * 支付结果回调
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        MyLog.i("微信支付结果回调结果码：",""+baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //将回调结果发布出去(在PayHelper里面调起支付后接收)
//            EventBus.getDefault().post(new WechatClientResponseEvent(baseResp));
        }
        finish();
    }
}
