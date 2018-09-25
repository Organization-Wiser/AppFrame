package com.wiser.mframe.third;

import android.content.Context;

import com.wiser.mframe.third.alipay.AliPayManage;
import com.wiser.mframe.third.alipay.IAliPayManage;
import com.wiser.mframe.third.baidu.BaiDuManage;
import com.wiser.mframe.third.baidu.IBaiDuManage;
import com.wiser.mframe.third.getui.GeTuiManage;
import com.wiser.mframe.third.getui.IGeTuiManage;
import com.wiser.mframe.third.mob.IMobManage;
import com.wiser.mframe.third.mob.MobManage;
import com.wiser.mframe.third.tencent.ITencentManage;
import com.wiser.mframe.third.tencent.TencentManage;
import com.wiser.mframe.third.wxpay.IWXPayManage;
import com.wiser.mframe.third.wxpay.WXPayManage;
import com.wiser.mframe.wiser.MHelper;

/**
 * @author Wiser
 * 
 *         三方管理
 */
public class ThirdManage implements IThirdManage {

	private IWXPayManage	iwxManage;		// 微信

	private ITencentManage	iTencentManage;	// 腾讯

	private IAliPayManage	iAliPayManage;	// 支付宝

	private IMobManage		iMobManage;		// ShareSDK mob

	private IGeTuiManage	iGeTuiManage;	// 个推

	private IBaiDuManage	iBaiDuManage;	// 百度地图

	public Context			context;

	public ThirdManage(Context context) {
		this.context = context;
	}

	/**
	 * 微信管理
	 * 
	 * @return
	 */
	@Override public IWXPayManage wxPay() {
		if (iwxManage == null) {
			synchronized (IWXPayManage.class) {
				if (iwxManage == null) {
					iwxManage = new WXPayManage(context);
				}
			}
		}
		return iwxManage;
	}

	/**
	 * 腾讯管理
	 * 
	 * @return
	 */
	@Override public ITencentManage tencent() {
		if (iTencentManage == null) {
			synchronized (ITencentManage.class) {
				if (iTencentManage == null) {
					iTencentManage = new TencentManage(context);
				}
			}
		}
		return iTencentManage;
	}

	/**
	 * 支付宝管理
	 * 
	 * @return
	 */
	@Override public IAliPayManage aliPay() {
		if (iAliPayManage == null) {
			synchronized (IAliPayManage.class) {
				if (iAliPayManage == null) {
					iAliPayManage = new AliPayManage(context);
				}
			}
		}
		return iAliPayManage;
	}

	/**
	 * ShareSDK mob
	 * 
	 * @return
	 */
	@Override public IMobManage mob() {
		if (iMobManage == null) {
			synchronized (IMobManage.class) {
				if (iMobManage == null) {
					iMobManage = new MobManage(context);
				}
			}
		}
		return iMobManage;
	}

	/**
	 * 个推
	 * 
	 * @return
	 */
	@Override public IGeTuiManage geTui() {
		if (iGeTuiManage == null) {
			synchronized (IGeTuiManage.class) {
				if (iGeTuiManage == null) {
					iGeTuiManage = new GeTuiManage(context);
				}
			}
		}
		return iGeTuiManage;
	}

	@Override public IBaiDuManage baiDu() {
		if (iBaiDuManage == null) {
			synchronized (IBaiDuManage.class) {
				if (iBaiDuManage == null) {
					iBaiDuManage = new BaiDuManage(context);
				}
			}
		}
		return iBaiDuManage;
	}

	/**
	 * 清理三方缓存
	 */
	@Override public void clearThirdCache() {
		if (iTencentManage != null) {
			iTencentManage.clearCache();
		} else {
			// 杀死App后再次启动，退出第三方登录微信，QQ
			MHelper.third().tencent().clearCache();
		}
		if (iwxManage != null) {
			iwxManage.clearCache();
		} else {
			MHelper.third().wxPay().clearCache();
		}
		if (iAliPayManage != null) {
			iAliPayManage.clearCache();
		} else {
			MHelper.third().aliPay().clearCache();
		}
		if (iMobManage != null) {
			iMobManage.clearCache();
		} else {
			MHelper.third().mob().clearCache();
		}
	}
}
