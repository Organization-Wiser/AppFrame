package com.wiser.mframe.http;

import com.wiser.mframe.third.alipay.AliPayModel;
import com.wiser.mframe.third.wxpay.WXPayModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Wiser
 * 
 *         请求接口方法
 */
public interface IHttp {

	/**
	 * 微信支付接口
	 * 
	 * @param orderNo
	 * @return
	 */
	@GET("微信支付接口") Call<WXPayModel> getWXPay(@Query("orderNo") String orderNo);

	/**
	 * 支付宝支付接口
	 * 
	 * @param orderNo
	 * @return
	 */
	@GET("支付宝支付接口") Call<AliPayModel> getAliPay(@Query("orderNo") String orderNo);

}
