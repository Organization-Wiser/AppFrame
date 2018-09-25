package com.wiser.mframe.third;

import com.wiser.mframe.third.alipay.IAliPayManage;
import com.wiser.mframe.third.baidu.IBaiDuManage;
import com.wiser.mframe.third.getui.IGeTuiManage;
import com.wiser.mframe.third.mob.IMobManage;
import com.wiser.mframe.third.tencent.ITencentManage;
import com.wiser.mframe.third.wxpay.IWXPayManage;

/**
 * @author Wiser
 * 
 *         第三方接口
 */
public interface IThirdManage {

	/**
	 * 微信支付
	 * 
	 * @return
	 */
	IWXPayManage wxPay();

	/**
	 * 腾讯
	 * 
	 * @return
	 */
	ITencentManage tencent();

	/**
	 * 支付宝
	 * 
	 * @return
	 */
	IAliPayManage aliPay();

	/**
	 * ShareSDK mob
	 * 
	 * @return
	 */
	IMobManage mob();

	/**
	 * 个推
	 * 
	 * @return
	 */
	IGeTuiManage geTui();

	/**
	 * 百度地图
	 * 
	 * @return
	 */
	IBaiDuManage baiDu();

	/**
	 * 清理三方缓存
	 */
	void clearThirdCache();

}
