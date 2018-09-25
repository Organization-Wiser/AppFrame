package com.wiser.mframe.wiser.base;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.wiser.library.base.WISERActivity;
import com.wiser.library.base.WISERBuilder;
import com.wiser.mframe.R;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author Wiser
 *
 *         百度地图
 */
public abstract class WISERBaiDuActivity extends WISERActivity {

	protected BaiduMap mBaiDuMap;

	protected abstract MapView mapView();

	protected abstract WISERBuilder buildMap(WISERBuilder wiserBuilder);

	protected abstract void initDataMap(Intent intent);

	@Override protected WISERBuilder build(WISERBuilder wiserBuilder) {
		return buildMap(wiserBuilder);
	}

	@Override protected void initData(Intent intent) {
		mapView();
		initDataMap(intent);
		if (mapView() != null) mBaiDuMap = mapView().getMap();
	}

	/**
	 * 设置定位光标
	 */
	protected void setLocationCursor(BDLocation location, int cursorIcon) {
		if (mapView() == null || mBaiDuMap == null) return;
		// 先清除图层
		mBaiDuMap.clear();
		// 定义Maker坐标点
		LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
		// 构建MarkerOption，用于在地图上添加Marker
		MarkerOptions options = new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(cursorIcon));
		// 在地图上添加Marker，并显示
		mBaiDuMap.addOverlay(options);
		LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
		MapStatus.Builder builder = new MapStatus.Builder();
		builder.target(ll).zoom(18.0f);
		mBaiDuMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
	}

	/**
	 * 显示自定义位置框
	 * 
	 * @param location
	 * @param popView
	 */
	protected void setLocationPop(BDLocation location, View popView) {
		if (mapView() == null || mBaiDuMap == null) return;
		// 定义Maker坐标点
		LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
		// 构建MarkerOption，用于在地图上添加Marker
		MarkerOptions options = new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.mipmap.baidu_location_icon));
		// 在地图上添加Marker，并显示
		Marker marker = (Marker) mBaiDuMap.addOverlay(options);
		LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
		MapStatus.Builder builder = new MapStatus.Builder();
		builder.target(ll).zoom(18.0f);
		mBaiDuMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
		showLocationPop(marker, popView);
	}

	/**
	 * 显示自定义位置框
	 * 
	 * @param marker
	 * @param popView
	 */
	private void showLocationPop(Marker marker, View popView) {
		if (mapView() == null || mBaiDuMap == null) return;
		InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {

			@Override public void onInfoWindowClick() {}
		};
		InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(popView), marker.getPosition(), -100, listener);
		mBaiDuMap.showInfoWindow(mInfoWindow);
	}

	/**
	 * 设置显示类型
	 * 
	 * @link BaiduMap.MAP_TYPE_NORMAL 普通地图
	 * @link BaiduMap.MAP_TYPE_SATELLITE 卫星地图
	 * @link BaiduMap.MAP_TYPE_NONE 空白地图
	 */
	protected void setMapType(int type) {
		if (mBaiDuMap != null) {
			if (type == BaiduMap.MAP_TYPE_NORMAL || type == BaiduMap.MAP_TYPE_SATELLITE || type == BaiduMap.MAP_TYPE_NONE) mBaiDuMap.setMapType(type);
		}
	}

	/**
	 * 实时路况图
	 */
	protected void setTraffic() {
		// 开启交通图
		if (mBaiDuMap != null) mBaiDuMap.setTrafficEnabled(true);
	}

	/**
	 * 百度城市热力图
	 */
	protected void setBaiduHeatMap() {
		// 开启交通图
		if (mBaiDuMap != null) mBaiDuMap.setBaiduHeatMapEnabled(true);
	}

	@Override protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		if (mapView() != null) mapView().onResume();
	}

	@Override protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		if (mapView() != null) mapView().onPause();
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		if (mapView() != null) mapView().onDestroy();
	}
}
