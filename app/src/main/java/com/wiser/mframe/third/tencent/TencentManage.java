package com.wiser.mframe.third.tencent;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wiser.mframe.common.IConstants;

import android.content.Context;

/**
 * @author Wiser
 * 
 *         腾讯管理器
 */
public class TencentManage implements ITencentManage {

	private Context	context;

	/** 微信 **/
	private IWXAPI	wxapi;	// 微信API

	public TencentManage(Context context) {
		this.context = context;
	}

	/**
	 * 初始化微信API
	 */
	@Override public void initWXAPI() {
		wxapi = WXAPIFactory.createWXAPI(context, IConstants.WX_APP_ID, true);
		wxapi.registerApp(IConstants.WX_APP_ID);
	}

	/**
	 * 获取微信API
	 * 
	 * @return
	 */
	@Override public IWXAPI wxApi() {
		initWXAPI();
		return wxapi;
	}

	/**
	 * 是否安装微信
	 *
	 * @return
	 */
	@Override public boolean isWXInstall() {
		IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
		msgApi.registerApp(IConstants.WX_APP_ID);
		return msgApi.isWXAppInstalled();
	}

	/**
	 * 清理腾讯缓存
	 */
	@Override public void clearCache() {
		// 注销微信
		if (wxapi != null) {
			wxapi.unregisterApp();
			wxapi = null;
		} else { // 杀死App后重新进入，清理缓存，退出登录
			initWXAPI();
			if (wxapi != null) {
				wxapi.unregisterApp();
				wxapi = null;
			}
		}
		context = null;
	}

}
