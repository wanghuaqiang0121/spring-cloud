package com.mongodb.file.util;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeUtil {

	 
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月24日
	 * @param file
	 * @return
	 * @description: 根据文件后缀名得到contentType
	 * 		 如  .png  :  image/png
	 */
	public static final String getContentType(String file){
		
		Map<String,String> map = new HashMap<>();
		map.put(".asf" , "video/x-ms-asf");
		map.put(".avi" , "video/avi");
		map.put(".doc" , "application/msword");
		map.put(".xls" , "application/vnd.ms-excel");
		map.put(".pdf" , "application/pdf");
		map.put(".ppt" , "application/vnd.ms-powerpoint");
		map.put(".gif" , "image/gif");
		map.put(".jpg" , "image/jpeg");
		map.put(".jpe" , "image/jpeg");
		map.put("jpeg" , "image/jpeg");
		map.put(".bmp" , "image/bmp");
		map.put(".png" , "image/png");
		map.put(".txt" , "application/text");
		map.put(".txt" , "application/zip");
		map.put(".zip" , "application/zip");
		map.put(".wav" , "audio/wav");
		map.put(".mp3" , "audio/mpeg3");
		map.put(".mpg" , "video/mpeg");
		map.put(".mpeg", "video/mpeg");
		map.put(".rtf" , "application/rtf");
		map.put(".htm" , "text/html");
		map.put(".html", "text/html");
		map.put(".asp" , "text/asp");
		map.put("other", "application/octet-stream");
		
		return map.get(file)!=null ? map.get(file).toString() : map.get("other").toString();
	}
}
