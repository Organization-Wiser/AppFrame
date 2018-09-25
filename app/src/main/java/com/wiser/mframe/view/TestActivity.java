package com.wiser.mframe.view;

import java.util.HashMap;

import com.wiser.library.base.WISERActivity;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.helper.WISERHelper;
import com.wiser.library.manager.permission.IWISERPermissionCallBack;
import com.wiser.mframe.R;
import com.wiser.mframe.third.mob.MobShareListener;
import com.wiser.mframe.wiser.MHelper;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import cn.sharesdk.framework.Platform;

/**
 * @author Wiser
 * 
 *         测试
 */
public class TestActivity extends WISERActivity implements MobShareListener, IWISERPermissionCallBack {

	// 跳转
	public static void intent() {
		WISERHelper.display().intent(TestActivity.class);
	}

	@Override protected WISERBuilder build(WISERBuilder wiserBuilder) {
		wiserBuilder.layoutId(R.layout.test_act);
		wiserBuilder.layoutEmptyId(R.layout.view_empty);
		wiserBuilder.layoutErrorId(R.layout.view_error);
		wiserBuilder.layoutLoadingId(R.layout.view_loading);
		// wiserBuilder.tintFitsSystem(true);
		// wiserBuilder.tintIs(true);
		// wiserBuilder.tintColor(getResources().getColor(R.color.crimson));
		return wiserBuilder;
	}

	@Override protected void initData(Intent intent) {
		MHelper.third().mob().setMobShareListener(this);
		MHelper.permission().initDefaultPermission(this, this);
	}

	@OnClick(value = { R.id.btn_wx_pay, R.id.btn_qq_share, R.id.btn_qzone_share, R.id.btn_sina_weibo_share, R.id.btn_df_ui_share, R.id.btn_authorize, R.id.btn_baidu }) public void onClickView(
			View view) {
		switch (view.getId()) {
			case R.id.btn_wx_pay:// 微信支付
				new Thread(new Runnable() {

					@Override public void run() {
						// MHelper.third().wxPay().pay("");
						MHelper.third().aliPay().pay("");
					}
				}).start();
				break;
			case R.id.btn_qq_share:
				MHelper.third().mob().shareQQ("标题", "我是分享文本", "http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg", "http://sharesdk.cn");
				break;
			case R.id.btn_qzone_share:
				MHelper.third().mob().shareQZone("标题", "我是分享文本", "http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg", "http://sharesdk.cn");
				break;
			case R.id.btn_sina_weibo_share:
				MHelper.third().mob().shareSinaWeiBo("标题", "我是分享文本", "http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg", "http://sharesdk.cn");
				break;
			case R.id.btn_df_ui_share:
				MHelper.third().mob().shareDFUi("标题", "我是分享文本", "http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg", "http://sharesdk.cn");
				break;
			case R.id.btn_authorize:
				MHelper.third().mob().qqAuthorize();
				MHelper.toast().show(MHelper.third().mob().qqPlatformDb().getUserName());
				break;
			case R.id.btn_baidu:
				MHelper.display().intent(TestBaiDuActivity.class);
				break;
		}
	}

	@Override public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
		MHelper.toast().show("分享成功了");
	}

	@Override public void onError(Platform platform, int i, Throwable throwable) {
		MHelper.toast().show("分享失败了");
	}

	@Override public void onCancel(Platform platform, int i) {
		MHelper.toast().show("取消分享了");
	}

	@Override public void hadPermissionResult() {

	}
}
