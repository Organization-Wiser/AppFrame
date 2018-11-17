package com.wiser.mframe.view;

import com.wiser.library.annotation.impl.Impl;
import com.wiser.library.annotation.thread.Background;
import com.wiser.library.annotation.thread.BackgroundType;
import com.wiser.library.base.IWISERBiz;
import com.wiser.library.base.WISERBiz;
import com.wiser.mframe.http.IHttp;

import retrofit2.Call;

public class TestBiz extends WISERBiz<TestActivity> implements ITestBiz {

	@Override public void netMethod() {
		Call<String> call = http(IHttp.class).getData();
		String s = httpBody(call);
		ui().show(s);
	}
}

@Impl(TestBiz.class)
interface ITestBiz extends IWISERBiz {

	@Background(BackgroundType.HTTP) void netMethod();

}
