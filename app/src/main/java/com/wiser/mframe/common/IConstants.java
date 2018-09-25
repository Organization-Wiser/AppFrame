package com.wiser.mframe.common;

import com.wiser.mframe.R;
import com.wiser.mframe.wiser.MHelper;

/**
 * @author Wiser
 * 
 *         常量
 */
public interface IConstants {

	// 项目域名地址
	String	BASE_URL		= "https://www.baidu.com";

	// 微信支付APP_ID
	String	WX_APP_ID		= MHelper.getInstance().getResources().getString(R.string.WX_APP_ID);

	// ShareSDK Mob 分享APP_KEY
	String	MOB_APP_KEY		= MHelper.getInstance().getResources().getString(R.string.MOB_APP_KEY);

	// ShareSDK Mob 分享APP_SECRET
	String	MOB_APP_SECRET	= MHelper.getInstance().getResources().getString(R.string.MOB_APP_SECRET);
}
