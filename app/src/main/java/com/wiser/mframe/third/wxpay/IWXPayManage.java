package com.wiser.mframe.third.wxpay;

/**
 * @author Wiser
 * 
 *         微信支付
 */
public interface IWXPayManage {

	/**
	 * 微信支付
	 * 
	 * @param orderNo
	 *            订单号
	 * @return 成功失败
	 */
	boolean pay(String orderNo);

	/**
	 * 清理缓存
	 */
	void clearCache();
}
