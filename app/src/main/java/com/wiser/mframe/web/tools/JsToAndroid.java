package com.wiser.mframe.web.tools;

import com.wiser.mframe.wiser.MHelper;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * @author Wiser
 * 
 *         Js通知Android
 */
public class JsToAndroid {

	private WebView			webView;

	private AnalysisJson	analysisJson;

	public JsToAndroid(WebView webView) {
		this.webView = webView;
		this.analysisJson = new AnalysisJson(webView);
	}

	/**
	 * js 回调Android 返回Web 两端定义back action 为返回上一页web
	 */
	@JavascriptInterface public void back() {
		webView.post(new Runnable() {

			@Override public void run() {
				if (webView.canGoBack()) {
					webView.goBack();
				}
			}
		});
	}

	/**
	 * js 回调Android 关闭Activity 两端定义closeActivity action为关闭Activity
	 */
	@JavascriptInterface public void closeActivity() {
		MHelper.getActivityManage().getCurrentIsRunningActivity().finish();
	}

	@JavascriptInterface public void execute(String json) {
		analysisJson.convert(json);
	}

	public void clean() {
		webView = null;
		analysisJson = null;
	}

	public void executeJS(String callBack, String value) {
		analysisJson.callBack(callBack, value);
	}

}
