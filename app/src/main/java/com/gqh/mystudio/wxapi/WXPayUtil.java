package com.gqh.mystudio.wxapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.myapp.util.Constant;

public class WXPayUtil {
	
	   private PayReq req;
	   private IWXAPI msgApi;
	   private Map<String, String> resultunifiedorder;
	   private Context context;

	    public WXPayUtil(Context context) {
	        this.context = context;
	        this.msgApi = WXAPIFactory.createWXAPI(context, null);
	        this.msgApi.registerApp(Constant.APP_ID);
	        this.req = new PayReq();
	    }

	    public void startPay(String body,String price) {
			Toast.makeText(context, "去微信付款...", Toast.LENGTH_SHORT).show();
	       // this.dialog = DialogUtils.createLoadingDialog(context, "加载中......");
	        new GetPrepayIdTask(body, price).execute();
	    }

	    public class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String, String>> {
	        private String body;
	       // private String outTradeNo;
	        private String price;

	        public GetPrepayIdTask(String body, String price) {
	            super();
	            this.body = body;
	            this.price = price;
	        }

	        @Override
	        protected Map<String, String> doInBackground(Void... params) {
	            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
	            String entity = genProductArgs(body, price);
	            Log.e("orion", entity);
	            byte[] buf = wxUtil.httpPost(url, entity);
	            String content = new String(buf);
	            Log.e("orion", content);
	            Map<String, String> xml = decodeXml(content);
	            return xml;
	        }

	        @Override
	        protected void onPostExecute(Map<String, String> result) {
	            resultunifiedorder = result;
	            genPayReq();
	        }

	        @Override
	        protected void onCancelled() {
	            super.onCancelled();
	        }

	    }

	    /*
	     * 生成订单参数
	     */
	    private String genProductArgs(String body, String price) {
	        StringBuffer xml = new StringBuffer();	       
	        try {
	           // price = Integer.parseInt(price)* 100 + "";
	        	 price =(int)(Double.valueOf(price)* 100) + "";
	        	
	            String nonceStr = genNonceStr();
	            xml.append("</xml>");
	            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
	            packageParams.add(new BasicNameValuePair("appid",Constant.APP_ID));
	            // 商品或支付单简要描述
	            packageParams.add(new BasicNameValuePair("body", body));
	            packageParams.add(new BasicNameValuePair("mch_id",Constant.MCH_ID));
	            // 随机字符串
	            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
	            // 接收微信支付异步通知回调地址
	            packageParams.add(new BasicNameValuePair("notify_url",Constant.NOTIFY_URL));
	            // 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	            packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo()));
	            // 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	            packageParams.add(new BasicNameValuePair("spbill_create_ip","127.0.0.1"));
	            // 总金额 订单总金额，只能为整数，详见支付金额
	            packageParams.add(new BasicNameValuePair("total_fee",price));
	            // 交易类型
	            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
	            String sign = genPackageSign(packageParams);
	            packageParams.add(new BasicNameValuePair("sign", sign));
	            String xmlstring = toXml(packageParams);
	            return new String(xmlstring.toString().getBytes(), "ISO8859-1");
	            //return new String(xmlstring.toString().getBytes(), "utf-8");
	        } catch (Exception e) {
	            return null;
	        }

	    }

	    /*
	     * 支付接口请求参数
	     */
	    public void genPayReq() {
	        req.appId = Constant.APP_ID;
	        req.partnerId = Constant.MCH_ID;
	        req.prepayId = resultunifiedorder.get("prepay_id");
	        req.packageValue = "Sign=WXPay";
	        req.nonceStr = genNonceStr();
	        req.timeStamp = String.valueOf(genTimeStamp());
	        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
	        signParams.add(new BasicNameValuePair("appid", req.appId));
	        // 随机字符串
	        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
	        // 扩展字段 暂填写固定值Sign=WXPay
	        signParams.add(new BasicNameValuePair("package", req.packageValue));
	        // 商户号
	        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
	        // 预支付交易会话ID 微信返回的支付交易会话ID
	        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
	        // 时间戳
	        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
	        req.sign = genAppSign(signParams);
			sendPayReq();
	    }

	    /*
	     * 调起微信支付
	     */
	    public void sendPayReq() {
	        msgApi.registerApp(Constant.APP_ID);
	        msgApi.sendReq(req);
	    }

	    /**
	     * 生成签名
	     */

	    @SuppressLint("DefaultLocale")
	    private String genPackageSign(List<NameValuePair> params) {
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < params.size(); i++) {
	            sb.append(params.get(i).getName());
	            sb.append('=');
	            sb.append(params.get(i).getValue());
	            sb.append('&');
	        }
	        sb.append("key=");
	        sb.append(Constant.API_KEY);
	        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
	        Log.e("orion1", packageSign);
	        return packageSign;
	    }

	    private String genAppSign(List<NameValuePair> params) {
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < params.size(); i++) {
	            sb.append(params.get(i).getName());
	            sb.append('=');
	            sb.append(params.get(i).getValue());
	            sb.append('&');
	        }
	        sb.append("key=");
	        sb.append(Constant.API_KEY);

	        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
	        Log.e("orion", appSign);
	        return appSign;
	    }

	    private String toXml(List<NameValuePair> params) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("<xml>");
	        for (int i = 0; i < params.size(); i++) {
	            sb.append("<" + params.get(i).getName() + ">");

	            sb.append(params.get(i).getValue());
	            sb.append("</" + params.get(i).getName() + ">");
	        }
	        sb.append("</xml>");

	        Log.e("orion", sb.toString());
	        return sb.toString();
	    }

	    public Map<String, String> decodeXml(String content) {

	        try {
	            Map<String, String> xml = new HashMap<String, String>();
	            XmlPullParser parser = Xml.newPullParser();
	            parser.setInput(new StringReader(content));
	            int event = parser.getEventType();
	            while (event != XmlPullParser.END_DOCUMENT) {

	                String nodeName = parser.getName();
	                switch (event) {
	                case XmlPullParser.START_DOCUMENT:

	                    break;
	                case XmlPullParser.START_TAG:

	                    if ("xml".equals(nodeName) == false) {
	                        // 实例化student对象
	                        xml.put(nodeName, parser.nextText());
	                    }
	                    break;
	                case XmlPullParser.END_TAG:
	                    break;
	                }
	                event = parser.next();
	            }

	            return xml;
	        } catch (Exception e) {
	            Log.e("orion", e.toString());
	        }
	        return null;

	    }

	    private String genNonceStr() {
	        Random random = new Random();
	        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
	                .getBytes());
	    }

	    private long genTimeStamp() {
	        return System.currentTimeMillis() / 1000;
	    }
	    
	    public String genOutTradNo() {
			Random random = new Random();
			return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
		}

}
