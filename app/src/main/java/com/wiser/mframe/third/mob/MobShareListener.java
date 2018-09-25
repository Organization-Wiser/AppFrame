package com.wiser.mframe.third.mob;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;

/**
 * @author Wiser
 * 
 *         分享外部回调监听
 */
public interface MobShareListener {

	// 分享成功
	void onComplete(Platform platform, int i, HashMap<String, Object> hashMap);

	// 分享失败
	void onError(Platform platform, int i, Throwable throwable);

	// 取消分享
	void onCancel(Platform platform, int i);
}
