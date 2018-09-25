package com.wiser.mframe.third.tencent;

import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * @author Wiser
 * 
 *         腾讯
 */
public interface ITencentManage {

	/**
	 * 初始化微信API
	 */
	void initWXAPI();

	/**
	 * 获取微信API
	 * 
	 * @return
	 */
	IWXAPI wxApi();

	/**
	 * 是否微信安装了
	 *
	 * @return
	 */
	boolean isWXInstall();

	/**
	 * 清理缓存
	 */
	void clearCache();

}
