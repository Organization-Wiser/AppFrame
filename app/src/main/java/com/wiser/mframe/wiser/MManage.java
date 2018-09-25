package com.wiser.mframe.wiser;

import android.app.Application;

import com.wiser.library.base.IWISERBind;
import com.wiser.library.manager.WISERManage;
import com.wiser.mframe.common.MConfig;
import com.wiser.mframe.db.DBManage;
import com.wiser.mframe.third.IThirdManage;
import com.wiser.mframe.third.ThirdManage;
import com.wiser.mframe.wiser.manage.IMPermissionManage;
import com.wiser.mframe.wiser.manage.MFileManage;
import com.wiser.mframe.wiser.manage.MPermissionManage;
import com.wiser.mframe.wiser.manage.MToastManage;

/**
 * @author Wiser
 * 
 *         扩展管理器
 */
public class MManage extends WISERManage {

	private DBManage			dbManage;			// 数据库

	private ThirdManage			thirdManage;		// 三方

	private MPermissionManage	permissionManage;	// 权限管理

	private MToastManage		toastManage;		// 自定义布局Toast

	private MConfig				config;				// properties缓存配置

	private MFileManage			fileManage;

	@Override public void init(IWISERBind iwiserBind, Application application) {
		super.init(iwiserBind, application);
	}

	/**
	 * 获取数据库管理
	 * 
	 * @return
	 */
	public DBManage getDbManage() {
		if (dbManage == null) synchronized (DBManage.class) {
			if (dbManage == null) dbManage = new DBManage(getApplication());
		}
		return dbManage;
	}

	/**
	 * 获取三方管理
	 * 
	 * @return
	 */
	public ThirdManage getThirdManage() {
		if (thirdManage == null) synchronized (IThirdManage.class) {
			if (thirdManage == null) thirdManage = new ThirdManage(getApplication());
		}
		return thirdManage;
	}

	/**
	 * 获取权限管理
	 *
	 * @return
	 */
	public MPermissionManage permission() {
		if (permissionManage == null) synchronized (IMPermissionManage.class) {
			if (permissionManage == null) permissionManage = new MPermissionManage();
		}
		return permissionManage;
	}

	/**
	 * 获取Toast管理
	 *
	 * @return
	 */
	public MToastManage toast() {
		if (toastManage == null) synchronized (MToastManage.class) {
			if (toastManage == null) toastManage = new MToastManage();
		}
		return toastManage;
	}

	/**
	 * 获取Config properties缓存管理
	 *
	 * @return
	 */
	public MConfig config() {
		if (config == null) synchronized (MConfig.class) {
			if (config == null) config = new MConfig(getApplication());
		}
		return config;
	}

	/**
	 * 获取Config properties缓存管理
	 *
	 * @return
	 */
	public MFileManage fileManage() {
		if (fileManage == null) synchronized (MFileManage.class) {
			if (fileManage == null) fileManage = new MFileManage();
		}
		return fileManage;
	}
}
