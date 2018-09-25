package com.wiser.mframe.wxapi;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wiser.mframe.wiser.MHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Wiser
 * 
 * @see WXPayEntryActivity 微信支付回调
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String	TAG	= "WXPayEntryActivity";

	private IWXAPI				api;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = MHelper.third().tencent().wxApi();
		api.handleIntent(getIntent(), this);
	}

	@Override protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override public void onReq(BaseReq req) {}

	@Override public void onResp(BaseResp resp) {
		MHelper.log().d(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode) {
				case 0:// 成功
					MHelper.toast().show("支付成功");
					break;
				case -1:// 失败
					MHelper.toast().show("支付失败");
					break;
				case -2:// 取消
					MHelper.toast().show("用户取消支付");
					break;
			}
			finish();
		}
	}
}