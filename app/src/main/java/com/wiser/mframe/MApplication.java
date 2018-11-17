package com.wiser.mframe;

import com.wiser.mframe.wiser.MBind;
import com.wiser.mframe.wiser.MHelper;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @author Wiser
 */
public class MApplication extends Application {

	@Override protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		// 分包
		MultiDex.install(base);
	}

	@Override public void onCreate() {
		super.onCreate();
		// WiserFrame
		MHelper.newBind().setWiserBind(new MBind()).Inject(this, false);
		// 初始化文件路径
		MHelper.file().initConfigureFile(MHelper.file().FILE_DIR);
		// 捕获异常
		MHelper.setCrashHandler(this, "log", false,false);
		// 个推
		MHelper.third().geTui().initGeTui();
		// 百度
		MHelper.third().baiDu().initBaiDuSDK(this);
	}

}
