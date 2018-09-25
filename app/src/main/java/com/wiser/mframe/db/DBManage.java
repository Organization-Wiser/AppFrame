package com.wiser.mframe.db;

import android.content.Context;

import com.wiser.mframe.db.dao.DaoMaster;
import com.wiser.mframe.db.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author Wiser
 * @version 1.0
 * @see DBManage 数据库管理器
 */
@SuppressWarnings("ALL")
public class DBManage {

	/**
	 * 数据库名称
	 */
	private static final String	DB_NAME				= "wiser-db";

	/**
	 * 数据库加密名称
	 */
	private static final String	DB_NAME_ENCRYPTED	= "wiser-db-encrypted";

	/**
	 * session
	 */
	private DaoSession			daoSession;

	private Context				context;

	public DBManage(Context context) {
		this.context = context;
	}

	public void initDB() {
		// noinspection ConstantConditions
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
		// noinspection ConstantConditions
		Database db = helper.getWritableDb();
		daoSession = new DaoMaster(db).newSession();
	}

	public void clean() {
		daoSession = null;
	}

	/**
	 * 获取session
	 *
	 * @return 返回值
	 */
	public DaoSession getDaoSession() {
		if (daoSession == null) {
			synchronized (this) {
				if (daoSession == null) {
					initDB();
				}
			}
		}
		return daoSession;
	}
}
