package com.wiser.mframe.third.getui.service;

import com.igexin.sdk.GTServiceManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Wiser
 * 
 *         个推服务
 */
public class GeTuiPushService extends Service {

	@Override public void onCreate() {
		super.onCreate();
		GTServiceManager.getInstance().onCreate(this);
	}

	@Override public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
	}

	@Override public IBinder onBind(Intent intent) {
		return GTServiceManager.getInstance().onBind(intent);
	}

	@Override public void onDestroy() {
		super.onDestroy();
		GTServiceManager.getInstance().onDestroy();
	}

	@Override public void onLowMemory() {
		super.onLowMemory();
		GTServiceManager.getInstance().onLowMemory();
	}
}