package com.wiser.mframe.third.wxpay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wiser.mframe.http.response.BaseModel;

/**
 * @author Wiser
 * 
 *         微信支付所需数据
 */
public class WXPayModel extends BaseModel {

	@SerializedName("data") @Expose public Data data;

	public static class Data {

		@SerializedName("wxPayData") @Expose public WXPay wxPay;

	}

	public static class WXPay {

		@SerializedName("appId") @Expose public String		appId;

		@SerializedName("partnerId") @Expose public String	partnerId;

		@SerializedName("prepayId") @Expose public String	prepayId;

		@SerializedName("package") @Expose public String	packageValue;

		@SerializedName("nonceStr") @Expose public String	nonceStr;

		@SerializedName("timeStamp") @Expose public String	timeStamp;

		@SerializedName("sign") @Expose public String		sign;
	}

}
