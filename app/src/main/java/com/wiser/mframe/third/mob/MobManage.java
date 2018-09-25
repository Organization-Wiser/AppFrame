package com.wiser.mframe.third.mob;

import java.util.HashMap;

import com.mob.MobSDK;
import com.wiser.library.util.WISERCheck;
import com.wiser.mframe.R;
import com.wiser.mframe.common.IConstants;
import com.wiser.mframe.third.mob.source.OneKeyShare;
import com.wiser.mframe.wiser.MHelper;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author Wiser
 * 
 *         ShareSDK mob
 */
public class MobManage implements IMobManage, PlatformActionListener {

	private int					SHARE_STATE;			// 分享状态

	private Context				context;

	private MobShareListener	mobShareListener;

	private OneKeyShare			oks	= new OneKeyShare();

	public MobManage(Context context) {
		this.context = context;
	}

	/**
	 * 初始化
	 */
	@Override public void initMob() {
		MobSDK.init(context, IConstants.MOB_APP_KEY, IConstants.MOB_APP_SECRET);
	}

	/**
	 * 分享新浪微博
	 *
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @param linkUrl
	 */
	@Override public int shareSinaWeiBo(String title, String content, String imgUrl, String linkUrl) {
		// 判断客户端是否安装来进行是否网页分享 TODO 由于ShareSDK 中自己处理来未安装情况所有不需要做此判断
		// HashMap<String, Object> hashMap = new HashMap<>();
		// if (isSinaWeiBoInstall()) {// 已安装
		// hashMap.put("ShareByAppClient", "true");
		// } else {// 未安装
		// hashMap.put("ShareByAppClient", "false");
		// }
		// ShareSDK.setPlatformDevInfo(SinaWeibo.NAME, hashMap);

		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		oks.setPlatform(SinaWeibo.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享微信朋友
	 */
	@Override public int shareWeChat(String title, String content, String imgUrl, String linkUrl) {
		if (!isWXInstall()) {// 未安装
			MHelper.toast().show("微信客户端未安装");
			return IMobConstants.NO_INSTALL;
		}

		oks = new OneKeyShare();
		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		oks.setPlatform(Wechat.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享微信朋友圈
	 * 
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @param linkUrl
	 */
	@Override public int shareWeChatMoments(String title, String content, String imgUrl, String linkUrl) {
		if (!isWXInstall()) {// 未安装
			MHelper.toast().show("微信客户端未安装");
			return IMobConstants.NO_INSTALL;
		}

		oks = new OneKeyShare();
		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		oks.setPlatform(WechatMoments.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享QQ
	 */
	@Override public int shareQQ(String title, String content, String imgUrl, String linkUrl) {
		oks = new OneKeyShare();
		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		oks.setPlatform(QQ.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享QQ空间
	 */
	@Override public int shareQZone(String title, String content, String imgUrl, String linkUrl) {
		oks = new OneKeyShare();
		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		oks.setPlatform(QZone.NAME);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享 公共
	 * 
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @param linkUrl
	 */
	@Override public int share(String platform, String title, String content, String imgUrl, String linkUrl) {
		oks = new OneKeyShare();
		// 指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
		if (!WISERCheck.isEmpty(platform)) oks.setPlatform(platform);
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 分享外部回调
		oks.setCallback(this);
		// 启动分享
		oks.show(context);

		return SHARE_STATE;
	}

	/**
	 * 分享 默认UI
	 *
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @param linkUrl
	 */
	@Override public void shareDFUi(String title, String content, String imgUrl, String linkUrl) {
		oks = new OneKeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(linkUrl);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(content);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(imgUrl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(linkUrl);
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getResources().getString(R.string.app_name));

		// 启动分享
		oks.show(context);
	}

	/**
	 * 新浪微博授权登陆
	 */
	@Override public void sinaWeiBoAuthorize() {
		clearAuthorize();
		Platform sinaPf = sinaPlatform();
		sinaPf.setPlatformActionListener(this);
		sinaPf.authorize();
	}

	/**
	 * 微信授权登陆
	 */
	@Override public void wXAuthorize() {
		clearAuthorize();
		Platform wxPf = weChatPlatform();
		wxPf.setPlatformActionListener(this);
		wxPf.authorize();
	}

	/**
	 * QQ授权登陆
	 */
	@Override public void qqAuthorize() {
		clearAuthorize();
		Platform qqPf = qqPlatform();
		qqPf.setPlatformActionListener(this);
		qqPf.authorize();
	}

	/**
	 * 清除授权
	 */
	@Override public void clearAuthorize() {
		Platform sinaPf = sinaPlatform();
		Platform wxPf = weChatPlatform();
		Platform qqPf = qqPlatform();
		if (sinaPf.isAuthValid()) {// 新浪判断是否授权了 如果授权就移除
			sinaPf.removeAccount(true);
		} else if (wxPf.isAuthValid()) {
			wxPf.removeAccount(true);
		} else if (qqPf.isAuthValid()) {
			qqPf.removeAccount(true);
		}
	}

	/**
	 * 新浪平台对象 可用于获取授权登陆成功之后等平台信息 也可做其他之用
	 * 
	 * @return
	 */
	@Override public Platform sinaPlatform() {
		return ShareSDK.getPlatform(SinaWeibo.NAME);
	}

	/**
	 * 微信平台对象 可用于获取授权登陆成功之后等平台信息 也可做其他之用
	 * 
	 * @return
	 */
	@Override public Platform weChatPlatform() {
		return ShareSDK.getPlatform(Wechat.NAME);
	}

	/**
	 * qq平台对象 可用于获取授权登陆成功之后等平台信息 也可做其他之用 例：qqPlatform().getDb().getUserName();
	 * 就是QQ登陆信息等用户名
	 * 
	 * @return
	 */
	@Override public Platform qqPlatform() {
		return ShareSDK.getPlatform(QQ.NAME);
	}

	/**
	 * 获取新浪微博平台信息数据库
	 *
	 * @return
	 */
	@Override public PlatformDb sinaPlatformDb() {
		return sinaPlatform().getDb();
	}

	/**
	 * 获取微信平台信息数据库
	 *
	 * @return
	 */
	@Override public PlatformDb weChatPlatformDb() {
		return weChatPlatform().getDb();
	}

	/**
	 * 获取qq平台信息数据库
	 *
	 * @return
	 */
	@Override public PlatformDb qqPlatformDb() {
		return qqPlatform().getDb();
	}

	/**
	 * 是否新浪微博安装了
	 * 
	 * @return
	 */
	@Override public boolean isSinaWeiBoInstall() {
		Platform pf = ShareSDK.getPlatform(SinaWeibo.NAME);
		return pf.isClientValid();
	}

	/**
	 * 是否微信安装了
	 *
	 * @return
	 */
	@Override public boolean isWXInstall() {
		Platform pf = ShareSDK.getPlatform(Wechat.NAME);
		return pf.isClientValid();
	}

	/**
	 * 是否QQ安装了
	 *
	 * @return
	 */
	@Override public boolean isQQInstall() {
		Platform pf = ShareSDK.getPlatform(QQ.NAME);
		return pf.isClientValid();
	}

	/**
	 * 分享外部回调监听
	 * 
	 * @param shareListener
	 * @return
	 */
	@Override public void setMobShareListener(MobShareListener shareListener) {
		this.mobShareListener = shareListener;
	}

	/**
	 * 清理缓存
	 */
	@Override public void clearCache() {
		oks = null;
		mobShareListener = null;
		context = null;
	}

	@Override public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
		SHARE_STATE = IMobConstants.SUCCESS;
		if (this.mobShareListener != null) this.mobShareListener.onComplete(platform, i, hashMap);
	}

	@Override public void onError(Platform platform, int i, Throwable throwable) {
		SHARE_STATE = IMobConstants.FAIL;
		if (this.mobShareListener != null) this.mobShareListener.onError(platform, i, throwable);
	}

	@Override public void onCancel(Platform platform, int i) {
		SHARE_STATE = IMobConstants.CANCEL;
		if (this.mobShareListener != null) this.mobShareListener.onCancel(platform, i);
	}
}
