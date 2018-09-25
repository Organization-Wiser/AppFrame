package com.wiser.mframe.third.getui;

import android.content.Context;

/**
 * @author Wiser
 * 
 *         个推
 */
public interface IGeTuiManage {

	/**
	 * 初始化个推
	 */
	void initGeTui();

	/**
	 * 显示自定义通知 获取个推推送的数据消息
	 */
	void showGTCustomNotification(Context context, byte[] payload);

}
