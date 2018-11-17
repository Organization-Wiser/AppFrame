package com.wiser.mframe.wiser.manage.file;

import com.wiser.library.manager.file.WISERFileCacheManage;
import com.wiser.mframe.wiser.MHelper;

/**
 * @author Wiser
 * 
 *         文件管理
 */
public class MFileManage extends WISERFileCacheManage {

	// 存储路径 ->/storage/emulated/0/Android/data/com.wiser.frame/files
	public String	FILE_DIR		= MHelper.fileCacheManage().configureFileDir(MHelper.getInstance());

	// APP路径 ->/storage/emulated/0文件夹 就是 我的文件/内部存储 的路径
	public String	APP_FILE_DIR	= MHelper.fileCacheManage().configureStorageDir();

}
