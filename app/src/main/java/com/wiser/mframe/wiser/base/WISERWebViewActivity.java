package com.wiser.mframe.wiser.base;

import android.content.Intent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebViewClient;

import com.wiser.library.base.WISERBuilder;
import com.wiser.library.base.WISERWebActivity;
import com.wiser.library.util.WISERWebChromeClient;
import com.wiser.mframe.R;

/**
 * @author Wiser
 * 
 *         WebView 网页加载
 */
public class WISERWebViewActivity extends WISERWebActivity {

	@Override protected WISERBuilder buildWeb(WISERBuilder wiserBuilder) {
		wiserBuilder.swipeBack(true);
		wiserBuilder.tintFitsSystem(false);
		wiserBuilder.layoutLoadingId(R.layout.view_loading);
		isHandleBack(true);
		isHaveProgress(true);
		progressDrawable(R.drawable.web_progress_line);
		return wiserBuilder;
	}

	@Override protected void initWebData(Intent intent) {

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
		return null;
	}

	@Override protected String loadUrl() {
		return "https://www.baidu.com";
	}
}
