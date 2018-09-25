package com.wiser.mframe.view.home;

import com.wiser.library.base.WISERActivity;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.helper.WISERHelper;
import com.wiser.mframe.R;
import com.wiser.mframe.view.TestActivity;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;

/**
 * @author Wiser
 * 
 *         首页
 */
public class HomeActivity extends WISERActivity {

	// 跳转
	public static void intent() {
		WISERHelper.display().intent(HomeActivity.class);
	}

	@Override protected WISERBuilder build(WISERBuilder wiserBuilder) {
		wiserBuilder.layoutId(R.layout.home_act);
		wiserBuilder.layoutEmptyId(R.layout.view_empty);
		wiserBuilder.layoutErrorId(R.layout.view_error);
		wiserBuilder.layoutLoadingId(R.layout.view_loading);
		// wiserBuilder.tintFitsSystem(true);
		wiserBuilder.tintIs(true);
		wiserBuilder.tintColor(getResources().getColor(R.color.crimson));
		return wiserBuilder;
	}

	@Override protected void initData(Intent intent) {}

	@OnClick(value = { R.id.btn_test }) public void onClickView(View view) {
		switch (view.getId()) {
			case R.id.btn_test:// 测试
				TestActivity.intent();
				break;
		}
	}
}
