package com.wiser.mframe.wiser;

import com.wiser.library.helper.WISERHelper;
import com.wiser.mframe.common.MConfig;
import com.wiser.mframe.db.DBManage;
import com.wiser.mframe.third.IThirdManage;
import com.wiser.mframe.wiser.manage.IMPermissionManage;
import com.wiser.mframe.wiser.manage.MFileManage;

/**
 * @author Wiser
 * 
 *         扩展帮助类
 */
public class MHelper extends WISERHelper {

	/**
	 * 数据库
	 * 
	 * @return
	 */
	public static DBManage db() {
		MManage mManage = getManage();
		return mManage.getDbManage();
	}

	/**
	 * 三方管理
	 * 
	 * @return
	 */
	public static IThirdManage third() {
		MManage mManage = getManage();
		return mManage.getThirdManage();
	}

	/**
	 * 权限管理
	 *
	 * @return
	 */
	public static IMPermissionManage permission() {
		MManage mManage = getManage();
		return mManage.permission();
	}

	/**
	 * properties 缓存配置
	 * 
	 * @return
	 */
	public static MConfig config() {
		MManage mManage = getManage();
		return mManage.config();
	}

	/**
	 * 文件存储管理
	 * 
	 * @return
	 */
	public static MFileManage file() {
		MManage mManage = getManage();
		return mManage.fileManage();
	}
}
