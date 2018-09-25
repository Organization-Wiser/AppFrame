package com.wiser.mframe.third.alipay;

/**
 * @author Wiser
 * 
 *         支付宝支付
 */
public interface IAliPayManage {

	/**
	 * 支付宝支付
	 * 
	 * @param orderNo
	 *            订单号
	 * @return
	 */
	boolean pay(String orderNo);

	/**
	 * 是否支付宝安装
	 *
	 * @return
	 */
	boolean isAliInstall();

	/**
	 * 清理缓存
	 */
	void clearCache();

}
