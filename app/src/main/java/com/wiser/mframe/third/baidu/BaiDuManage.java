package com.wiser.mframe.third.baidu;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.wiser.library.manager.permission.IWISERPermissionCallBack;
import com.wiser.mframe.third.baidu.service.LocationService;
import com.wiser.mframe.wiser.MHelper;

import android.content.Context;
import android.webkit.WebView;

/**
 * @author Wiser
 * 
 *         百度
 */
public class BaiDuManage implements IBaiDuManage, BDLocationListener {

	private Context					context;

	private LocationClient			mLocClient;

	private LocationService			mLocService;

	private ILocationResultCallBack	iLocationResultCallBack;

	public BaiDuManage(Context context) {
		this.context = context;
	}

	/**
	 * 初始化百度SDK
	 * 
	 * @param context
	 */
	@Override public void initBaiDuSDK(Context context) {
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		SDKInitializer.initialize(context);
		// 自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
		// 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
		SDKInitializer.setCoordType(CoordType.BD09LL);
	}

	/**
	 * 获取定位信息
	 * 
	 * @param isKeepGet
	 *            是否保持获取
	 * @param iLocationResultCallBack
	 */
	@Override public void getBDLocation(final boolean isKeepGet, final ILocationResultCallBack iLocationResultCallBack) {
		MHelper.permission().requestLocationPermission(MHelper.getActivityManage().getCurrentActivity(), new IWISERPermissionCallBack() {

			@Override public void hadPermissionResult() {
				BaiDuManage.this.iLocationResultCallBack = iLocationResultCallBack;
				if (isKeepGet) {
					mLocService = new LocationService(context);
					mLocService.setLocationOption(mLocService.getDefaultLocationClientOption());
					mLocService.registerListener(abstractLocationListener);
					mLocService.start();
				} else {
					// 定位初始化
					mLocClient = new LocationClient(MHelper.getActivityManage().getCurrentActivity());
					mLocClient.registerNotifyLocationListener(BaiDuManage.this);
					LocationClientOption option = new LocationClientOption();
					option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
					option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
					option.setIsNeedLocationDescribe(true);// 可选，是否需要位置描述信息，默认为不需要，即参数为false
					option.setOpenGps(true); // 可选，设置是否使用gps，默认false
					option.setCoorType("bd09ll"); // 设置坐标类型
					option.setScanSpan(1000);// 可选，设置发起定位请求的间隔，int类型，单位ms
					option.setLocationNotify(true);// 可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
					option.setIgnoreKillProcess(false);// 可选，定位SDK内部是一个service，并放到了独立进程。设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
					option.SetIgnoreCacheException(false);// 可选，设置是否收集Crash信息，默认收集，即参数为false
					// option.setWifiCacheTimeOut(1000);// 可选，V7.2版本新增能力
					// 如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
					option.setEnableSimulateGps(false);// 可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
					option.setIsNeedLocationPoiList(true);// 可选，是否需要周边POI信息，默认为不需要，即参数为false
					option.setAddrType("all");
					mLocClient.setLocOption(option);
					if (!mLocClient.isStarted()) {
						mLocClient.start();
					}
				}
			}
		});
	}

	/**
	 * 辅助H5定位
	 *
	 * @param webView
	 * @param iLocationResultCallBack
	 */
	@Override public void assistBDLocationH5(WebView webView, ILocationResultCallBack iLocationResultCallBack) {
		// 定位初始化
		mLocClient = new LocationClient(MHelper.getActivityManage().getCurrentActivity());
		mLocClient.start();
		mLocClient.enableAssistantLocation(webView);
	}

	/**
	 * 辅助h5定位关闭
	 */
	@Override public void assistBDLocationH5Close() {
		if (mLocClient != null) {
			mLocClient.disableAssistantLocation();
			mLocClient.stop();
			mLocClient = null;
		}
	}

	/**
	 * 清理缓存
	 */
	@Override public void clearCache() {
		if (mLocClient != null) {
			mLocClient.stop();
			mLocClient = null;
		}
		if (mLocService != null) {
			mLocService.unregisterListener(abstractLocationListener);
			mLocService.stop();
			mLocService = null;
		}
	}

	private BDAbstractLocationListener abstractLocationListener = new BDAbstractLocationListener() {

		@Override public void onReceiveLocation(BDLocation bdLocation) {
			receiveLocation(bdLocation);
		}
	};

	/**
	 * 定位回调结果
	 * 
	 * @param bdLocation
	 */
	@Override public void onReceiveLocation(BDLocation bdLocation) {
		receiveLocation(bdLocation);
	}

	/**
	 * 接受位置信息
	 * 
	 * @param bdLocation
	 */
	private void receiveLocation(BDLocation bdLocation) {
		// map view 销毁后不在处理新接收的位置
		if (bdLocation == null) {
			if (iLocationResultCallBack != null) {
				iLocationResultCallBack.onLocationFailed();
			}
			deactivate();
			return;
		}
		if (iLocationResultCallBack != null) {
			iLocationResultCallBack.onLocationSuccess(bdLocation);
		}
		deactivate();

		iLocationResultCallBack = null;
		// BDLocation.getLocationWhere()方法可获得当前定位点是否是国内，它的取值及含义如下：
		// BDLocation.LOCATION_WHERE_IN_CN：当前定位点在国内；
		// BDLocation.LOCATION_WHERE_OUT_CN：当前定位点在海外；
		// 其他：无法判定。
	}

	private void deactivate() {
		if (mLocClient != null) {
			mLocClient.stop();
		}
	}

	/**
	 * 接口
	 */
	public interface ILocationResultCallBack {

		/**
		 * 定位成功
		 *
		 * @param location
		 *            定位信息
		 */
		void onLocationSuccess(BDLocation location);

		/**
		 * 定位失败
		 */
		void onLocationFailed();
	}
}
