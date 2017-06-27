package cn.myapp.util;

/**
 * @author : 红仔
 * @date : 2016/2/22
 * desc:  接口
 */
public final class Constant {



	//照片根路径
//	public final static String IMAGE_URL ="http://123.57.156.227:8088/AppImages/";//测试环境
		public final static String IMAGE_URL ="http://123.56.66.70:8088/AppImages/";//生产环境
	//根路径http://[ip]:[port]/odm/app,
//	public final static String SERVER_URL ="http://123.57.156.227:8088/odm/app";//测试环境
	public final static String SERVER_URL ="http://123.56.66.70:8088/odm/app";//生产环境

	// 2.2上传文件
	public final static String UP_LOAD="/common/upload";

	//公民认证登陆
	public final static String GONGMIN_RENZHENG_LOGION ="/user/authenticate";
	//获取公民信息
	public final static String GET_USER_INFO="/user/getuserinfo";

	//12.13获取公民信息二
	public final static String GET_SPEAK_USER_INFO="/user/getUserInfoByUID";
	//    获取验证码
	public final static String GET_CODE="/common/getCode";
	//    访客认证登陆
	public final static String FANGKE_RENZHENG_LOGION="/visitor/authenticate";

	//修改登录密码
	public final static String SET_LOIN_PASSWORD="/user/mpwd";

	//修改支付密码
	public final static String SET_PAY_PASSWORD="/user/mpaypwd";

	//3.3验证支付密码
	public final static String CHECK_PAY_PASSWORD="/user/checkPayPwd";


	//忘记登陆密码
	public final static String WANGJI_LOIN_PASSWORD="/user/forgetpwd";


	//忘记支付密码
	public final static String WANGJI_PAY_PASSWORD="/user/forgetpaypwd";

	//首页消息  6.2新派管家消息
	public final static String SHOUYE_XIAOXI="/workorder/getNumber";

	//管家 新增服务
	public final static String NEW_FUWU="/workorder/new";

	//管家 服务记录
	public final static String FUWU_JINDU="/workorder/getWorkOrder";
	//管家 服务列表
	public final static String FUWU_LIEBIAO="/workorder/getList";
	//管家 7.4评价
	public final static String FUWU_PINGJIA="/workorder/score";

	//左邻右舍 晒-公民动态
	public final static String SHAI_GONGMIN_DONGTAI="/user/getShowList";

	//左邻右舍 晒 我要晒
	public final static String WOYAO_SHAI="/user/show";

	//左邻右舍 敲 列表
	public final static String QIAO_LIEBIAO="/friend/getList";

	//左邻右舍 约 列表
	public final static String YUE_LIEBIAO="/group/getList";

	//左邻右舍 8.3晒-邻居动态
	public final static String SHAI_LINJU_DONGTAI="/user/getNeighborShowList";

	//左邻右舍 8.15约-列表
	public final static String YUE_LIST="/group/getList";

	//左邻右舍 8.20约-发布活动
	public final static String YUE_FABU="/group/new";


	//左邻右舍 8.7敲-敲门
	public final static String KNOCK="/friend/knock";

	//左邻右舍 8.11敲-发送信息
	public final static String SEND_MSG="/friend/newMsg";

	//左邻右舍 8.10敲-留言记录
	public final static String LIUYAN_JILU="/friend/getMsgList";

	//左邻右舍 8.23晒-公民晒详情
	public final static String SHAI_DETAIL="/user/getUserShow";

	//左邻右舍 8.22敲-是否好友
	public final static String IS_FRIEND="/friend/isFriend";


	//左邻右舍 8.21约-详情
	public final static String YUE_DETAIL="/group/detail";

	//左邻右舍 8.16约-加入活动
	public final static String YUE_JOIN="/group/join";

	//左邻右舍 8.17约-邀请好友列表
	public final static String YUE_FRIEND_LIST="/group/getFriendList";

	//左邻右舍 8.18约-邀请
	public final static String YUE_GROUP_INVITE="/group/invite";

	//个人中心 12.11个人设置-个人信息
	public final static String USER_EDIT="/user/edit";

	//10新派币  10.1余额
	public final static String XINPAIBI_YU_E="/rmb/balance";

	//10新派币  10.3待缴费列表
	public final static String DAI_JIAOFEI_LIST="/rmb/listUnPay";

	//10新派币  10.2充值
//	public final static String RMB_RECHARGE="/rmb/recharge";

	//10新派币  10.4缴费
	public final static String RMB_PAY="/rmb/pay";

	//10新派币  10.5历史记录
	public final static String RMB_LIST_BUSINESS="/rmb/listBusiness";

	//10新派币  10.6消费详情
	public final static String RMB_DETAIL_BUSINESS="/rmb/detailBusiness";

	//10新派币  10.7消费统计
	public final static String RMB_STAT_BUSINESS="/rmb/statBusiness";

	//10新派币  10.8获取订单号
	public final static String RMB_ORDER="/rmb/order";
	public final static String RMB_ORDER_THIRD_PAY_ALIPAY=SERVER_URL+"/rmb/third_pay/alipay";
	public final static String RMB_ORDER_THIRD_PAY_WEIXIN=SERVER_URL+"/rmb/third_pay/weixin";
	//	10.9检查订单支付情况
	public final static String RMB_ORDER_CHECKPAYED="/rmb/checkPayed";

	//11.1商家列表-首页
	public final static String SUPPLIER_LIST_INDEX="/supplier/listIndex";

	//11新派Link  11.2商家列表-类别
	public final static String SUPPLIER_LIST_BY_TYPE="/supplier/listByType";

	//11新派Link  11.3商家-新增
	public final static String SUPPLIER_NEW="/supplier/new";

	//12个人中心  12.2我的动态-敲
	public final static String MY_LIST_KNOCK="/friend/listMyKnock";

	//12个人中心  12.3我的动态-约
	public final static String MY_LIST_GROUP="/group/listMyGroup";

	//12个人中心  8.19约-接受（拒绝）邀请
	public final static String GROUP_ACCEPT="/group/accept";

	//12个人中心  8.8敲-接受（拒绝）
	public final static String FRIEND_ACCEPT="/friend/accept";

	//12个人中心  12.5我的消息
	public final static String MY_MESSAGE_LIST="/msg/listMySysMsg";

	//12个人中心  12.7社区公告
	public final static String MY_GROUP_MESSAGE_LIST="/msg/listSysMsg";

	//12个人中心  12.10我的合约
	public final static String MY_CONTRACT="/user/myContract";

	//12个人中心  8.12敲-礼物列表  商品列表
	public final static String LIST_PRODUCT="/order/listProduct";

	//12个人中心  8.13敲-送礼  商品支付结算
	public final static String ORDER_PRODUCT="/order/order";

	//12个人中心  12.9我的订单
	public final static String LIST_MY_ORDER="/order/listMyOrder";

	//12个人中心  12.9我的订单
	public final static String LIST_ORDER_PRODUCT="/order/listOrderProduct";

	//13.1开门历史   包含公寓门和房间门的记录
	public final static String LIST_UNLOCK_DOOR_HISTORY="/unlock_door/history";

	//13.2开门   包含开公寓门和房间门
	public final static String LIST_UNLOCK_DOOR_OPEN="/unlock_door/unlock";













	//声明一个独一无二的标识，来作为要显示DatePicker的Dialog的ID：
	public static final int DATE_DIALOG_ID = 100;



	//gridview 是否全显示
	public static final int STYLE_ADAPTER_OK = 1001;
	public static final int STYLE_ADAPTER_NO = 1002;

	//公民权限
	public static final String STYLE_GONGMIN ="STYLE_GONGMIN";
	public static final String STYLE_GONGMIN_OLD = "STYLE_GONGMIN_OLD";
	public static final String STYLE_FANGKE = "STYLE_FANGKE";



	public static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	public static final int PHOTO_REQUEST_CUT = 3;// 裁剪
	public static final String KEFU = "010-87212566";//CBD  //01065241837紫禁城


	public static final String imageUrl1="http://123.57.156.227:8088/odm/app/index/img/1.png";
	public static final String imageUrl2="http://123.57.156.227:8088/odm/app/index/img/2.png";
	public static final String imageUrl3="http://123.57.156.227:8088/odm/app/index/img/3.png";


	public static final String URL_XINGBAKE="https://www.starbucks.com.cn/";//星巴克
	public static final String URL_58DAOJIA="http://bj.daojia.com/";//--58到家
	public static final String URL_LVYE_XIANSONG="http://www.freshoutlets.com/index.htm";//绿野鲜送
	public static final String URL_ALIJIA="http://www.helijia.com/index.html";//河狸家
	public static final String URL_QINGKE="http://www.tsinova.com/";//轻客
	public static final String URL_ZHONGLIANG_WOMAIWANG="http://www.womai.com/";//中粮我买网
	public static final String URL_AIXIANFENG="http://weixin.beequick.cn/";//--爱鲜蜂
	public static final String URL_UBER="https://www.uber.com.cn/";//--优步


	public static final int STATUS_CONNECT_FAILURE=400;


	public static final String SEX_MAN = "00_011_1";//男
	public static final String SEX_WOMAN = "00_011_2";//女

	//微信
	public static final String APP_ID = "wx5018f99ee43d6442";//应用ID
	public static final String MCH_ID = "1288580901";//商户号
	public static final String NOTIFY_URL = RMB_ORDER_THIRD_PAY_WEIXIN;  // 接收微信支付异步通知回调地址
	public static final String API_KEY = "sczJpiMw6V3YO1lPOSL9VsrueXwrIEiK";  // API密钥
//	public static final String API_KEY = "d4624c36b6795d1d99dcf0547af5443d";  // API密钥

	//支付宝
	public static final String PARTNER = "2088311095912814";// 商户PID

	public static final String SELLER = "cypacypa@163.com";// 商户收款账号

	public static final String RSA_PRIVATE =// 商户私钥，pkcs8格式
			"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN35+ZhIvN/PBF8A\n" +
					"d1yQ6OkBUwbVD0d5rD5YD5HkKtuh1cd+Nrrsp6L79EeVvNcarezaLOlyB6b0drI+\n" +
					"DGg6mVzK5XzlZaxcIIo1KzLobKdYGZORWduBkIAdn52kvDlMIIWOJw7L0Dekqd9T\n" +
					"MK7GPIEbrmH/D3u5hrCqYK6AULu/AgMBAAECgYAYPNdYHXkiJwSfKvndjaUg7BgY\n" +
					"wuLsNwjrtcndcECNwtoI8msfdf/H+CLwPhVkl3EuT5Rf2Sekv0TGqafJKbzdBNEx\n" +
					"gmrUfghKUZyIXlxbXcFoF9boo6AJknFQ3guT+oiTxy8P/M3KOveCW9mbsUCpmY7L\n" +
					"voWXF+0nJDbfMisPwQJBAP3YoQgtx+LVyPiqAsidbAfgxJXa0hd97bphZHkY93xN\n" +
					"IrTO2v9/G8htMh2fbV0sS7VS5XWVIU1ejBS1ndNT+EcCQQDf3B9rcb4X0K5HkG2y\n" +
					"B0OJk3c4NHdddLF+eKGnZoL9Bj7wtfXXjtFsRe4ytOcoOlHwkxrBPOgtVBtgpoHT\n" +
					"+NTJAkA5GMHXyhxcvENxEyR/JVGIUBlHr6lz2UJgmslzp5b7IYp96s09jMSeB1mv\n" +
					"ag26WF1LErNECGAuO+b8PYGbaBa/AkEAyj+Lu3z163pEGq/oC3H4mLE8gvET7sFh\n" +
					"IwxFsIIzUfp0nvEmfWuw1YuQmwtJ1NRr91hQUcl/UoSsf3Ov1Bz50QJAe6CH+X4r\n" +
					"mCLKa+NaeMqn4Xleqvj3g1tnqfu3ZayWbClUsDzwn49jeD4GPfVamL6P/ggItPpI\n" +
					"ZHfX/hxKqQdP7A==";

	public static final String RSA_PUBLIC =// 支付宝公钥
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDd+fmYSLzfzwRfAHdckOjpAVMG" +
					"1Q9Heaw+WA+R5CrbodXHfja67Kei+/RHlbzXGq3s2izpcgem9HayPgxoOplcyuV8" +
					"5WWsXCCKNSsy6GynWBmTkVnbgZCAHZ+dpLw5TCCFjicOy9A3pKnfUzCuxjyBG65h" +
					"/w97uYawqmCugFC7vwIDAQAB";
}
