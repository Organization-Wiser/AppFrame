package com.wiser.mframe.wiser;

import com.wiser.library.helper.WISERHelper;
import com.wiser.mframe.common.MConfigManage;
import com.wiser.mframe.db.DBManage;
import com.wiser.mframe.third.IThirdManage;
import com.wiser.mframe.wiser.manage.MManage;
import com.wiser.mframe.wiser.manage.permission.IMPermissionManage;
import com.wiser.mframe.wiser.manage.file.MFileManage;
import com.wiser.mframe.wiser.manage.toast.MToastManage;

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
	public static MConfigManage config() {
		MManage mManage = getManage();
		return mManage.configManage();
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

	public static MToastManage toast() {
		MManage mManage = getManage();
		return mManage.toastManage();
	}
}
