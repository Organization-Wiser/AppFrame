package com.wiser.mframe.db.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Wiser
 *
 *         测试greenDao数据库
 */
@Entity
public class TestModel {

	@Id(autoincrement = true) private Long	id;

	public String							test1;

	public int								test2;

	@Generated(hash = 1313094265)
	public TestModel(Long id, String test1, int test2) {
					this.id = id;
					this.test1 = test1;
					this.test2 = test2;
	}

	@Generated(hash = 1568142977)
	public TestModel() {
	}

	public Long getId() {
					return this.id;
	}

	public void setId(Long id) {
					this.id = id;
	}

	public String getTest1() {
					return this.test1;
	}

	public void setTest1(String test1) {
					this.test1 = test1;
	}

	public int getTest2() {
					return this.test2;
	}

	public void setTest2(int test2) {
					this.test2 = test2;
	}

}
