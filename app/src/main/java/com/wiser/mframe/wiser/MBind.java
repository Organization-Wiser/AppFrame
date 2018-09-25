package com.wiser.mframe.wiser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wiser.library.base.IWISERBind;
import com.wiser.library.manager.WISERManage;
import com.wiser.mframe.common.IConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Wiser
 * 
 *         扩展绑定类
 */
public class MBind implements IWISERBind {

	@Override public Retrofit getRetrofit(Retrofit.Builder builder) {
		OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

		// 超时
		okHttpBuilder.connectTimeout(180, TimeUnit.SECONDS);
		okHttpBuilder.readTimeout(180, TimeUnit.SECONDS);
		okHttpBuilder.writeTimeout(180, TimeUnit.SECONDS);
		// CommonParamsInterceptor commonParamsInterceptor = new
		// CommonParamsInterceptor.Builder()// 创建
		// .addHeaderParam("Accept-Encoding", "gzip, deflate")// 头信息
		// .addQueryParam(IConstants.WEBSITE_CODE,
		// "4").addQueryParam(IConstants.VERSION,
		// "3").addQueryParam(IConstants.RESPONSE_TYPE,
		// "2").addQueryParam(IConstants.COMP_ID, "ATSLCS")
		// .addQueryParam(IConstants.CHANNEL, "ADM").addQueryParam(IConstants.ACCOUNT,
		// "ATSLCSEBANK").addQueryParam(IConstants.MKBH,
		// "").addQueryParam(IConstants.GNBH, "")
		// .addQueryParam(IConstants.ADSBID,
		// "154701a5aff5293c").addQueryParam(IConstants.BBH,
		// "2").addQueryParam(IConstants.ZCID, "").build();
		// okHttpBuilder.addInterceptor(commonParamsInterceptor);
		// LogggingInterceptor interceptor = new LogggingInterceptor();
		// interceptor.setLevel(LogggingInterceptor.Level.BODY);
		// okHttpBuilder.addInterceptor(interceptor);
		// builder.client(okHttpBuilder.build());
		builder.baseUrl(IConstants.BASE_URL);
		Gson gson = new GsonBuilder().setLenient().create();
		builder.addConverterFactory(GsonConverterFactory.create(gson));
		return builder.build();
	}

	@Override public WISERManage getManage() {
		return new MManage();
	}
}
