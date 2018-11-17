package com.wiser.mframe.wiser.manage.permission;

import android.app.Activity;

import com.wiser.library.manager.permission.IWISERPermissionCallBack;
import com.wiser.library.manager.permission.IWISERPermissionManage;

/**
 * @author Wiser
 * 
 *         权限管理
 */
public interface IMPermissionManage extends IWISERPermissionManage {

	int	DEFAULT_PERMISSION	= 1000;

	int	LOCATION_PERMISSION	= 1001;

	void initDefaultPermission(Activity activity, IWISERPermissionCallBack iwiserPermissionCallBack);

	/**
	 * 请求定位权限
	 *
	 * @param activity
	 * @param iwiserPermissionCallBack
	 */
	void requestLocationPermission(Activity activity, IWISERPermissionCallBack iwiserPermissionCallBack);

}
