package com.wiser.mframe.third.alipay;

import com.alipay.sdk.app.PayTask;
import com.wiser.mframe.http.IHttp;
import com.wiser.mframe.wiser.MHelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import retrofit2.Call;

/**
 * @author Wiser
 * 
 *         支付宝支付
 */
public class AliPayManage implements IAliPayManage {

	private Context context;

	public AliPayManage(Context context) {
		this.context = context;
	}

	/**
	 * 支付宝支付
	 * 
	 * @param orderNo
	 * @return
	 */
	@Override public boolean pay(final String orderNo) {
		if (!isAliInstall()) {
			MHelper.toast().show("未安装支付宝客户端");
			return false;
		}
		Call<AliPayModel> call = MHelper.http(IHttp.class).getAliPay(orderNo);
		AliPayModel model = MHelper.httpBody(call);
		if (model != null && model.data != null && model.status != null) {
			if (model.status.code != 0) {
				MHelper.toast().show(model.status.message);
				return false;
			}
			if (model.data.alreadyPaid == 1) { // 如果标记为1 已支付
				return true;
			}
		} else {
			return false;
		}
		// 调用支付宝支付
		PayTask aliPay = new PayTask(MHelper.getActivityManage().getCurrentActivity());
		String result = aliPay.pay(model.data.payResult, true);
		AliPayResult resultObj = new AliPayResult(result);
		String resultStatus = resultObj.resultStatus;
		// 判断resultStatus
		// 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
		if (TextUtils.equals(resultStatus, AliPayConstants.ALIPAY_STATUS_SUCCESS) || TextUtils.equals(resultStatus, AliPayConstants.ALIPAY_STATUS_PROGRESS)) {
			return true;
		} else {
			MHelper.toast().show(resultObj.memo);
			return false;
		}
	}

	/**
	 * 是否支付宝安装
	 * 
	 * @return
	 */
	@Override public boolean isAliInstall() {
		Uri uri = Uri.parse("alipays://platformapi/startApp");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		ComponentName componentName = intent.resolveActivity(context.getPackageManager());
		return componentName != null;
	}

	/**
	 * 清理缓存
	 */
	@Override public void clearCache() {
		context = null;
	}
}
