package com.wiser.mframe.third.getui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wiser.mframe.third.getui.GeTuiManage;
import com.wiser.mframe.view.WelcomeActivity;
import com.wiser.mframe.view.home.HomeActivity;

import org.json.JSONObject;

/**
 * @author Wiser
 * 
 *         个推自定义通知点击通知广播
 */
public class GTNotificationClickReceiver extends BroadcastReceiver {

	@Override public void onReceive(Context context, Intent intent) {
		// 获取个推的Json数据
		byte[] payload = new byte[0];
		if (intent != null) {
			payload = intent.getByteArrayExtra(GeTuiManage.GT_JSON);
		}
		JSONObject jsonObject = getJsonData(payload);

		// 跳转
		Intent newIntent = new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(newIntent);
	}

	/**
	 * 获取Json对象
	 *
	 * @param payload
	 * @return
	 */
	private static JSONObject getJsonData(byte[] payload) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(new String(payload));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
