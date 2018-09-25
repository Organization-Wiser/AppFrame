package com.wiser.mframe.view;

import java.lang.ref.WeakReference;
import java.text.MessageFormat;

import com.wiser.library.base.WISERActivity;
import com.wiser.library.base.WISERBuilder;
import com.wiser.mframe.R;
import com.wiser.mframe.view.home.HomeActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Wiser
 *
 *         欢迎页
 */
public class WelcomeActivity extends WISERActivity{

	private final int							TASK	= 1001;

	@BindView(R.id.tv_welcome_skip) TextView	tvSkip;

	private int									count	= 3;

	private WelcomeHandler						handler;

	@SuppressLint("HandlerLeak")
	private class WelcomeHandler extends Handler {

		WeakReference<WelcomeActivity> reference;

		WelcomeHandler(WelcomeActivity activity) {
			reference = new WeakReference<>(activity);
		}

		@Override public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (reference != null && reference.get() != null) {
				switch (msg.what) {
					case TASK:
						count--;
						if (count == 0) {
							skip();
						} else {
							tvSkip.setText(MessageFormat.format("跳过：{0}s", count));
							handler.sendEmptyMessageDelayed(TASK, 1000);
						}
						break;
				}
			}
		}
	}

	@Override protected WISERBuilder build(WISERBuilder wiserBuilder) {
		wiserBuilder.layoutId(R.layout.welcome_act);
		wiserBuilder.tintFitsSystem(true);
		return wiserBuilder;
	}

	@Override protected void initData(Intent intent) {
        handler = new WelcomeHandler(this);
        handler.sendEmptyMessageDelayed(TASK, 1000);
	}

	@OnClick(R.id.tv_welcome_skip) public void onClickView(View view) {
		switch (view.getId()) {
			case R.id.tv_welcome_skip:// 跳过
				skip();
				break;
		}
	}

	// 跳转
	private void skip() {
		HomeActivity.intent();
		WelcomeActivity.this.finish();
	}

	@Override protected void onPause() {
		if (isFinishing()) {
			clearCache();
		}
		super.onPause();
	}

	// 清缓存
	private void clearCache() {
		if (handler != null) {
			handler.removeMessages(TASK);
			handler = null;
		}
		count = 3;
	}
}
