package com.wiser.mframe.third.getui.service;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.wiser.mframe.wiser.MHelper;

import android.content.Context;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明,
 * 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class GeTuiIntentService extends GTIntentService {

	public GeTuiIntentService() {

	}

	// 个推服务pid
	@Override public void onReceiveServicePid(Context context, int pid) {}

	// 处理透传消息 处理json数据
	@Override public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
		// 通知获取json数据
		MHelper.third().geTui().showGTCustomNotification(context, msg.getPayload());
	}

	// 个推客户端ID
	@Override public void onReceiveClientId(Context context, String clientid) {}

	// 个推服务在线状态
	@Override public void onReceiveOnlineState(Context context, boolean online) {}

	// 各种事件处理回执
	@Override public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {}

	// 个推不是透传消息送达 消息数据
	@Override public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
		// 获取消息内容
	}

	// 个推不是透传消息的通知点击
	@Override public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
		// 点击通知执行操作 GTNotificationMessage 消息内容
	}
}
