package com.wiser.mframe.wiser.manage;

import android.view.Gravity;
import android.view.View;

import com.wiser.library.manager.toast.WISERCustomToast;

/**
 * @author Wiser
 * 
 *         自定义Toast
 */
public class MToastManage extends WISERCustomToast {

	@Override protected int toastLayoutId() {
		return 0;
	}

	@Override protected int toastDrawableBackgroundId() {
		return 0;
	}

	@Override protected void initView(View view, String s) {

	}

	@Override protected int gravity() {
		return Gravity.CENTER;
	}
}
