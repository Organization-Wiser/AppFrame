package com.wiser.mframe.wiser.manage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.wiser.library.manager.permission.IWISERPermissionCallBack;
import com.wiser.library.manager.permission.WISERPermissionManage;

import java.util.ArrayList;

/**
 * @author Wiser
 * 
 *         权限管理
 */
public class MPermissionManage extends WISERPermissionManage implements IMPermissionManage {

	@Override public void initDefaultPermission(Activity activity, IWISERPermissionCallBack iwiserPermissionCallBack) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			if (iwiserPermissionCallBack != null) {
				iwiserPermissionCallBack.hadPermissionResult();
			}
		} else {
			ArrayList<String> arrayList = new ArrayList<>();
			// 外部存储权限
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				arrayList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
			}
			// 手机状态权限
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				arrayList.add(Manifest.permission.READ_PHONE_STATE);
			}
			// 读写外部存储器权限
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				arrayList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
			}
			// 发送短信权限
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
				arrayList.add(Manifest.permission.SEND_SMS);
			}
			// 定位权限1
			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				arrayList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
			}

			if (arrayList.size() > 0) {
				String[] permissions = arrayList.toArray(new String[arrayList.size()]);
				hashMap.put(DEFAULT_PERMISSION, iwiserPermissionCallBack);
				activity.requestPermissions(permissions, DEFAULT_PERMISSION);
			} else {
				if (iwiserPermissionCallBack != null) {
					iwiserPermissionCallBack.hadPermissionResult();
				}
			}
		}
	}

	/**
	 * 请求定位权限
	 * 
	 * @param activity
	 * @param iwiserPermissionCallBack
	 */
	@Override public void requestLocationPermission(Activity activity, IWISERPermissionCallBack iwiserPermissionCallBack) {
		permission(activity, iwiserPermissionCallBack, IMPermissionManage.LOCATION_PERMISSION, Manifest.permission.ACCESS_COARSE_LOCATION);
	}
}
