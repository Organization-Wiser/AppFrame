package com.wiser.mframe.web.action;

import com.wiser.library.util.WISERGson;
import com.wiser.mframe.web.model.MJsModel;
import com.wiser.mframe.web.tools.CallBackJs;
import com.wiser.mframe.web.tools.JsToAction;

/**
 * @author Wiser
 * 
 *         测试js 与 Android 交互  MJsAction是交互定义的健
 */
public class MJsAction extends JsToAction<MJsModel> {

	@Override public void execute(MJsModel param, CallBackJs callBackJs) {

	}

	@Override public MJsModel fromJson(String params) {
		return WISERGson.getData(params, MJsModel.class);
	}
}
