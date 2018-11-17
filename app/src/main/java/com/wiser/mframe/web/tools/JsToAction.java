package com.wiser.mframe.web.tools;

/**
 * 
 * @author Wiser
 * 
 *         Js 调用 Android 所需Action
 * 
 * @param <T>
 */
public abstract class JsToAction<T> {

	public abstract void execute(T param, CallBackJs callBackJs);

	public abstract T fromJson(String params);

}
