package com.wiser.mframe.http.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Wiser
 * 
 *         公共返回参数
 */
public class BaseModel {

	@SerializedName("status") @Expose public Status		status;

	@SerializedName("systemDate") @Expose public long	systemDate;

	public static class Status {

		@SerializedName("code") @Expose public int			code;

		@SerializedName("message") @Expose public String	message;
	}

}
