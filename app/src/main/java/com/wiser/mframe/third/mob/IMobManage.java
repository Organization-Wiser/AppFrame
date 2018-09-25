package com.wiser.mframe.third.mob;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;

/**
 * @author Wiser
 * 
 *         ShareSDK Mob
 */
public interface IMobManage {

	/**
	 * 初始化 ShareSDK mob
	 */
	void initMob();

	/**
	 * 分享新浪微博
	 */
	int shareSinaWeiBo(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享微信朋友
	 */
	int shareWeChat(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享微信朋友圈
	 */
	int shareWeChatMoments(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享QQ
	 */
	int shareQQ(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享QQ空间
	 */
	int shareQZone(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享公共
	 */
	int share(String platform, String title, String content, String imgUrl, String linkUrl);

	/**
	 * 分享默认UI
	 */
	void shareDFUi(String title, String content, String imgUrl, String linkUrl);

	/**
	 * 新浪微博授权登陆
	 */
	void sinaWeiBoAuthorize();

	/**
	 * 微信授权登陆
	 */
	void wXAuthorize();

	/**
	 * QQ授权登陆
	 */
	void qqAuthorize();

	/**
	 * 清除授权
	 */
	void clearAuthorize();

	/**
	 * 获取新浪微博平台对象
	 * 
	 * @return
	 */
	Platform sinaPlatform();

	/**
	 * 获取微信平台对象
	 * 
	 * @return
	 */
	Platform weChatPlatform();

	/**
	 * 获取qq平台对象
	 * 
	 * @return
	 */
	Platform qqPlatform();

	/**
	 * 获取新浪微博平台信息数据库
	 * 
	 * @return
	 */
	PlatformDb sinaPlatformDb();

	/**
	 * 获取微信平台信息数据库
	 * 
	 * @return
	 */
	PlatformDb weChatPlatformDb();

	/**
	 * 获取qq平台信息数据库
	 * 
	 * @return
	 */
	PlatformDb qqPlatformDb();

	/**
	 * 是否新浪客户端安装
	 * 
	 * @return
	 */
	boolean isSinaWeiBoInstall();

	/**
	 * 是否微信客户端安装
	 * 
	 * @return
	 */
	boolean isWXInstall();

	/**
	 * 是否QQ客户端安装
	 * 
	 * @return
	 */
	boolean isQQInstall();

	/**
	 * 设置分享外部回调监听
	 * 
	 * @param shareListener
	 */
	void setMobShareListener(MobShareListener shareListener);

	/**
	 * 清理缓存
	 */
	void clearCache();
}
