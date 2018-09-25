package com.wiser.mframe.third.baidu;

import android.content.Context;
import android.webkit.WebView;

/**
 * @author Wiser
 * 
 *         百度
 */
public interface IBaiDuManage {

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	void initBaiDuSDK(Context context);

	/**
	 * 获取百度定位
	 * 
	 * @param isKeepGet
	 *            是否保持获取
	 * @param iLocationResultCallBack
	 */
	void getBDLocation(boolean isKeepGet, BaiDuManage.ILocationResultCallBack iLocationResultCallBack);

	/**
	 * 获取百度定位 辅助H5定位
	 *
	 * @param iLocationResultCallBack
	 */
	void assistBDLocationH5(WebView webView, BaiDuManage.ILocationResultCallBack iLocationResultCallBack);

	/**
	 * 辅助H5定位关闭
	 */
	void assistBDLocationH5Close();

	/**
	 * 清除缓存
	 */
	void clearCache();

}
