package com.wiser.mframe.db.manage;

import com.wiser.mframe.db.dao.TestModel;
import com.wiser.mframe.db.dao.TestModelDao;
import com.wiser.mframe.wiser.MHelper;

import java.util.List;

/**
 * @author Wiser
 *
 *         数据管理
 */
public class TestManage {

	/**
	 * 单条保存
	 *
	 * @param model
	 */
	public static boolean save(TestModel model) {
		if (model != null) {
			List<TestModel> modelList = queryWhereAll(model.test1, model.test2);
			if (modelList != null && modelList.size() > 0) return false;
			MHelper.db().getDaoSession().getTestModelDao().insertOrReplace(model);
			return true;
		} else return false;
	}

	/**
	 * 整表查询
	 *
	 * @return
	 */
	public static List<TestModel> queryAll() {
		List<TestModel> list = MHelper.db().getDaoSession().getTestModelDao().loadAll();
		// Collections.reverse(list);//倒叙查
		return list;
	}

	/**
	 * 根据条件查询
	 *
	 * @param test1
	 *            通过条件查询
	 * 
	 * @return 返回所有属于该条件集合
	 */
	public static List<TestModel> queryWhereAll(String test1) {
		List<TestModel> list = MHelper.db().getDaoSession().getTestModelDao().queryBuilder().where(TestModelDao.Properties.Test1.eq(test1)).list();
		return list;
	}

	/**
	 * 根据条件查询
	 *
	 * @param test1
	 *            通过条件1查询
	 * @param test2
	 *            通过条件2查询
	 * 
	 * @return 返回所有属于该条件集合
	 */
	public static List<TestModel> queryWhereAll(String test1, int test2) {
		return MHelper.db().getDaoSession().getTestModelDao().queryBuilder().where(TestModelDao.Properties.Test1.eq(test1)).where(TestModelDao.Properties.Test2.eq(test2)).list();
	}

	/**
	 * 根据条件查询
	 *
	 * @param test1
	 *            通过条件1查询
	 * @param test2
	 *            通过条件2查询
	 * 
	 * @return 返回具体属于该条件的数据
	 */
	public static TestModel queryWhere(String test1, int test2) {
		return MHelper.db().getDaoSession().getTestModelDao().queryBuilder().where(TestModelDao.Properties.Test1.eq(test1)).where(TestModelDao.Properties.Test2.eq(test2)).build().unique();
	}

	/**
	 * 删除单条数据
	 *
	 * @param model
	 */
	public static void del(TestModel model) {
		if (model != null) {
			MHelper.db().getDaoSession().getTestModelDao().delete(model);
		}
	}

	/**
	 * 清空表数据
	 */
	public static void delAll() {
		MHelper.db().getDaoSession().getTestModelDao().deleteAll();
	}

	/**
	 * 更新单条
	 *
	 * @param model
	 */
	public static void update(TestModel model) {
		if (model != null) {
			MHelper.db().getDaoSession().getTestModelDao().update(model);
		}
	}
}
