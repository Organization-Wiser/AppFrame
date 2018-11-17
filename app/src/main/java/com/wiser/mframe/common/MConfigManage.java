package com.wiser.mframe.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wiser.library.annotation.property.Property;
import com.wiser.library.config.property.WISERProperties;

/**
 * @author Wiser
 * 
 *         配置 数据存储
 */
public class MConfigManage extends WISERProperties {

	public MConfigManage(@NonNull Context context) {
		super(context);
	}

	@Override public int initType() {
		return OPEN_TYPE_DATA;
	}

	// 账户名称 Config.accountName = "测试账户"; Config.commit();
	@Property("accountName") public String accountName;
}
