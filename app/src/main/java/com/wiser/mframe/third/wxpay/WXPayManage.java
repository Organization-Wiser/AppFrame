package com.wiser.mframe.third.wxpay;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.wiser.mframe.http.IHttp;
import com.wiser.mframe.wiser.MHelper;

import android.content.Context;

import retrofit2.Call;

/**
 * @author Wiser
 * 
 *         微信管理
 */
public class WXPayManage implements IWXPayManage {

	private Context context;

	public WXPayManage(Context context) {
		this.context = context;
	}

	/**
	 * 支付
	 * 
	 * @param orderNo
	 *            订单号
	 * @return
	 */
	@Override public boolean pay(String orderNo) {
		if (!MHelper.third().tencent().isWXInstall()) {
			MHelper.toast().show("未安装微信客户端");
			return false;
		}
		IWXAPI api = MHelper.third().tencent().wxApi();
		// TODO 请求通过订单号获取微信支付数据
		Call<WXPayModel> call = MHelper.http(IHttp.class).getWXPay(orderNo);
		WXPayModel model = MHelper.httpBody(call);
		if (model != null && model.data != null && model.status != null && model.status.code == 0) {
			PayReq req = new PayReq();
			WXPayModel.WXPay wxPay = model.data.wxPay;
			if (wxPay == null) return false;
			req.appId = wxPay.appId == null ? "" : wxPay.appId;
			req.partnerId = wxPay.partnerId == null ? "" : wxPay.partnerId;
			req.prepayId = wxPay.prepayId == null ? "" : wxPay.prepayId;
			req.nonceStr = wxPay.nonceStr == null ? "" : wxPay.nonceStr;
			req.timeStamp = wxPay.timeStamp == null ? "" : wxPay.timeStamp;
			req.packageValue = wxPay.packageValue == null ? "" : wxPay.packageValue;
			req.sign = wxPay.sign == null ? "" : wxPay.sign;
			api.sendReq(req);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 清理缓存
	 */
	@Override public void clearCache() {
		context = null;
	}
}
