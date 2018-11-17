package com.wiser.mframe.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wiser.library.base.WISERBuilder;
import com.wiser.library.base.WISERWebActivity;
import com.wiser.library.util.WISERWebChromeClient;
import com.wiser.mframe.R;
import com.wiser.mframe.web.tools.JsToAndroid;

/**
 * @author Wiser
 * 
 *         WebView 网页加载
 */
public class MWebViewActivity extends WISERWebActivity {

	private JsToAndroid jsToAndroid;

	@Override protected WISERBuilder buildWeb(WISERBuilder wiserBuilder) {
		wiserBuilder.swipeBack(true);
		wiserBuilder.tintFitsSystem(false);
		wiserBuilder.layoutLoadingId(R.layout.view_loading);
		isHandleBack(true);
		isHaveProgress(true);
		// progressDrawable(R.drawable.web_progress_line);
		return wiserBuilder;
	}

	@SuppressLint("AddJavascriptInterface") @Override protected void initWebData(Intent intent) {
		jsToAndroid = new JsToAndroid(webView());
		webView().addJavascriptInterface(jsToAndroid, "LocalNative");
		webView().getSettings().setUserAgentString(webView().getSettings().getUserAgentString() + "com.hna.hi");
	}

	@Override protected WISERWebChromeClient setWebChromeClient() {
		return new WISERWebChromeClient(this) {

			@Override public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
				// 辅助h5定位
				callback.invoke(origin, true, false);
				super.onGeolocationPermissionsShowPrompt(origin, callback);
			}
		};
	}

	@Override protected WebViewClient setWebViewClient() {
		return new WebViewClient();
	}

	@Override protected String loadUrl() {
		return "http://dev-1.m.hitrips.com/trip/views/index.html?app_version=3.1.0&channel=70000#tab_0";
	}

	@Override protected boolean isCustomWebSetting() {
		return false;
	}

	@Override protected WebView customWebSetting(WebView webView) {
		return webView;
	}

	@Override protected void onDestroy() {
		if (jsToAndroid != null) {
			jsToAndroid.clean();
			jsToAndroid = null;
		}
		super.onDestroy();
	}
}
