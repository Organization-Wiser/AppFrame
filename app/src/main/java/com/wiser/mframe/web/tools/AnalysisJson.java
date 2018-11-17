package com.wiser.mframe.web.tools;

import java.lang.reflect.Constructor;

import org.json.JSONException;
import org.json.JSONObject;

import com.wiser.mframe.wiser.MHelper;

import android.webkit.WebView;

/**
 * @author Wiser
 * 
 *         解析Js Json
 */
@SuppressWarnings("unchecked")
public class AnalysisJson implements CallBackJs {

	private WebView webView;

	public AnalysisJson(WebView webView) {
		this.webView = webView;
	}

	public void convert(String param) {
		String action = null;
		String paramContent = null;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(param);
			action = jsonObject.getString("action");
			paramContent = jsonObject.getString("params");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			Class<?> aClass = Class.forName(MHelper.getInstance().getPackageName() + ".web.action." + action);
			Constructor constructor = aClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			// 创建类
			JsToAction actionParam = (JsToAction) constructor.newInstance();
			actionParam.execute(actionParam.fromJson(paramContent), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override public void callBack(final String callBack, final String json) {
		webView.post(new Runnable() {

			@Override public void run() {
				String stringBuilder = "javascript:" + callBack + "('" + json + "')";
				webView.loadUrl(stringBuilder);
			}
		});
	}
}
