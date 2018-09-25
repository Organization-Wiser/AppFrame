package com.wiser.mframe.third.getui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.igexin.sdk.PushManager;
import com.wiser.mframe.R;
import com.wiser.mframe.third.getui.receiver.GTNotificationClickReceiver;
import com.wiser.mframe.third.getui.service.GeTuiIntentService;
import com.wiser.mframe.third.getui.service.GeTuiPushService;

import java.util.Random;

/**
 * @author Wiser
 * 
 *         个推
 */
public class GeTuiManage implements IGeTuiManage {

	public static final String	GT_JSON	= "GT_JSON";

	private Context				context;

	public GeTuiManage(Context context) {
		this.context = context;
	}

	/**
	 * 初始化
	 */
	@Override public void initGeTui() {
		PushManager.getInstance().initialize(context, GeTuiPushService.class);
		PushManager.getInstance().registerPushIntentService(context, GeTuiIntentService.class);
	}

	/**
	 * 显示自定义通知 获取个推推送的数据消息
	 * 
	 * @param context
	 * @param payload
	 */
	@Override public void showGTCustomNotification(Context context, byte[] payload) {
		// 通知请求码
		int id = new Random(666).nextInt(9999) + 1;

		/**
		 * 创建通知栏管理工具
		 */
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		/**
		 * 实例化通知栏构造器
		 */
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

		Intent intent = new Intent(context, GTNotificationClickReceiver.class);
		intent.putExtra(GT_JSON, payload);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 在Android O及以上版本报Developer warning for package Failed to post notification on
		// channel “null” 错误原因是因为在8.0以上需要增加渠道名称和渠道ID
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			// 可自定义通知布局layout
			// RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
			// R.layout.layout_notification);
			// NotificationChannel channel = new NotificationChannel("channelId",
			// "channelName", NotificationManager.IMPORTANCE_DEFAULT);
			// mBuilder.setChannelId("channelId");
			// assert notificationManager != null;
			// notificationManager.createNotificationChannel(channel);
			//
			// mBuilder.setSmallIcon(R.mipmap.ic_launcher);
			// mBuilder.setContent(remoteViews);
			// mBuilder.setDefaults(Notification.DEFAULT_SOUND);
			// remoteViews.setImageViewResource(R.id.image, R.mipmap.ic_launcher_round);
			// remoteViews.setTextViewText(R.id.title, "我是标题");
			// remoteViews.setTextViewText(R.id.content, "我是内容");
			// //跳转
			// Intent intent = new Intent(context, WelcomeActivity.class);
			// PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
			// 0);
			// mBuilder.setContentIntent(pendingIntent);
			// notificationManager.notify(id, mBuilder.build());
		} else { // 同样也可自定义通知布局layout 只不过这里为了测试 使用的系统通知布局
			// 设置标题
			mBuilder.setContentTitle("我是标题")
					// 设置内容
					.setContentText("我是内容")
					// 设置大图标
					.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
					// 设置小图标
					.setSmallIcon(R.mipmap.ic_launcher_round)
					// 设置通知时间
					.setWhen(System.currentTimeMillis())
					// 设置跳转
					.setContentIntent(pendingIntent)
					// 首次进入时显示效果
					.setTicker("我是测试内容")
					// 设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
					.setDefaults(Notification.DEFAULT_ALL)
					// 通知点击取消
					.setAutoCancel(true);
			// 发送通知请求
			assert notificationManager != null;
			notificationManager.notify(id, mBuilder.build());
		}
	}
}
