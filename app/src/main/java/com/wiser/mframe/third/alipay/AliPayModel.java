package com.wiser.mframe.third.alipay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wiser.mframe.http.response.BaseModel;

/**
 * @author Wiser
 * 
 *         支付宝
 */
public class AliPayModel extends BaseModel {

	@SerializedName("data") @Expose public Data data;

	public static class Data {

		@SerializedName("payResult") @Expose public String	payResult;

		@SerializedName("alreadyPaid") @Expose public int	alreadyPaid;// (1已经支付过，0未支付过)
	}

}
