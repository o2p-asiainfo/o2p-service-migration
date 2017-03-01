package com.asiainfo.integretion.o2p.servicemigration.common.util;

public class FileUtil {
	/**
	 * 获取文件扩展名
	 * @param file
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}
}
