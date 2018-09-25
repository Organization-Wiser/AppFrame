package com.wiser.mframe.third.channel;

import org.apache.commons.lang3.StringUtils;

import com.meituan.android.walle.BuildConfig;
import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.WalleChannelReader;
import com.wiser.mframe.wiser.MHelper;

import android.content.Context;

/**
 * @author Wiser
 * @version 1.0
 * @see ChannelManage 渠道管理
 */
@SuppressWarnings("ALL")
public class ChannelManage {

	// 渠道KEY
	private static final String	CHANNEL_KEY		= "hnachannelname";

	// 默认渠道
	private static final String	DEFAULT_CHANNEL	= "70000";			// 官方

	private String				channel;							// 动态

	public ChannelManage(Context context) {
		if (StringUtils.isNotBlank(channel)) {
			if (!BuildConfig.DEBUG) {
				MHelper.log().i("-----------------------------");
				MHelper.log().i("渠道号：" + channel + "");
				MHelper.log().i("-----------------------------");
			}
			return;
		}
		// 从文件里读取
		channel = getChannelFromApk(context);
		if (StringUtils.isEmpty(channel)) {
			channel = DEFAULT_CHANNEL;
		}
	}

	/**
	 * 获取渠道
	 * 
	 * @return 返回值
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 从apk中获取版本信息
	 *
	 * @param context
	 *            上下文
	 * @return 返回值
	 */
	private static String getChannelFromApk(Context context) {
		long startTime = System.currentTimeMillis();
		// 从apk包中获取
		ChannelInfo channelInfo = WalleChannelReader.getChannelInfo(context);
		if (channelInfo == null) {
			return null;
		}
		String channel;
		if (StringUtils.isNotBlank(channelInfo.getChannel())) {
			String[] split = channelInfo.getChannel().split("_");
			channel = split[1];

			if (!BuildConfig.DEBUG) {
				System.out.println("-----------------------------");
				System.out.println("渠道号：" + channel + "，解压获取渠道号耗时:" + (System.currentTimeMillis() - startTime) + "ms");
				System.out.println("-----------------------------");
			}
		} else {
			if (!BuildConfig.DEBUG) {
				System.out.println("未解析到相应的渠道号，使用默认内部渠道号");
			}
			channel = DEFAULT_CHANNEL;
		}
		return channel;
	}
}
