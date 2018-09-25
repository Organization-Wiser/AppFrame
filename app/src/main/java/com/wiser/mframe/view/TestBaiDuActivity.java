package com.wiser.mframe.view;

import org.apache.commons.lang3.StringUtils;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapView;
import com.wiser.library.base.WISERBuilder;
import com.wiser.library.helper.WISERHelper;
import com.wiser.mframe.R;
import com.wiser.mframe.third.baidu.BaiDuManage;
import com.wiser.mframe.wiser.MHelper;
import com.wiser.mframe.wiser.base.WISERBaiDuActivity;
import com.wiser.mframe.wiser.manage.IMPermissionManage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import butterknife.BindView;

public class TestBaiDuActivity extends WISERBaiDuActivity {

	@BindView(R.id.map_view) MapView mMapView;

	@Override protected MapView mapView() {
		return mMapView;
	}

	@Override protected WISERBuilder buildMap(WISERBuilder wiserBuilder) {
		wiserBuilder.layoutId(R.layout.test_baidu_act);
		return wiserBuilder;
	}

	@Override protected void initDataMap(Intent intent) {
		MHelper.third().baiDu().getBDLocation(true, new BaiDuManage.ILocationResultCallBack() {

			@Override public void onLocationSuccess(BDLocation location) {
				if (location != null) {
					MHelper.toast().show(location.getAddrStr());
					setLocationCursor(location, R.mipmap.baidu_location_icon);
					setLocationPop(location, mInflater.inflate(R.layout.test_baidu_location_pop, null));
					if (!StringUtils.isBlank(location.getCity())) {
						MHelper.third().baiDu().clearCache();
					}
				}
			}

			@Override public void onLocationFailed() {

			}
		});
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case IMPermissionManage.LOCATION_PERMISSION:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					MHelper.permission().onPermission(requestCode);
				} else {
					WISERHelper.toast().show("请进行位置权限设定");
				}
				break;
		}
	}

	@Override protected void onResume() {
		super.onResume();
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		MHelper.third().baiDu().clearCache();
	}
}
