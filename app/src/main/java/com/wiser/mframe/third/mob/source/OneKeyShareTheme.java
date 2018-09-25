/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.wiser.mframe.third.mob.source;


/** 快捷分享的主题样式 */
public enum OneKeyShareTheme {
	/** 九格宫的主题样式 ,对应的实现类ClassicTheme */
	CLASSIC(0, new ClassicTheme());

	private final int					value;

	private final OneKeyShareThemeImpl impl;

	private OneKeyShareTheme(int value, OneKeyShareThemeImpl impl) {
		this.value = value;
		this.impl = impl;
	}

	public int getValue() {
		return value;
	}

	public OneKeyShareThemeImpl getImpl() {
		return impl;
	}

	public static OneKeyShareTheme fromValue(int value) {
		for (OneKeyShareTheme theme : OneKeyShareTheme.values()) {
			if (theme.value == value) {
				return theme;
			}
		}
		return CLASSIC;
	}

}
