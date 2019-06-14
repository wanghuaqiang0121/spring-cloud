package com.core.md5;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author: WangHuaQiang
 * @date: 2019年4月11日
 * @description: MD5加密解密
 */
public class MD5Util {
	private static Logger log = LoggerFactory.getLogger(MD5Util.class);

	private static MD5Util instance = new MD5Util();

	private MD5Util() {
	}

	public static MD5Util getInstance() {
		if (null == instance) {
			instance = new MD5Util();
		}
		return instance;
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param inStr
	 * @return
	 * @description: 得到MD5加密字符串
	 */
	public String getMD5Code(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5Utils Error:{}", e);
			return "";
		}
		byte[] md5Bytes = md5.digest(inStr.getBytes());
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param inStr
	 * @return
	 * @description: 解密MD5字符串
	 */
	public String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);
	}

	public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
		StringBuffer md5 = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] dataBytes = new byte[1024];

		int nread = 0;
		while ((nread = is.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		;
		byte[] mdbytes = md.digest();

		// convert the byte to hex format
		for (int i = 0; i < mdbytes.length; i++) {
			md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return md5.toString();
	}
	private String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX).substring(1);
	}

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param primary
	 * @return
	 * @description: 生成登录Token
	 */
	public String getSessionToken(Object primary) {
		return getMD5Code(UUID.randomUUID().toString()) + primary;
	}
	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @return
	 * @date 2018年9月14日
	 * @version 1.0
	 * @description 生成订单号
	 */
	public String getOrderNo() {
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(digits(uuid.getMostSignificantBits(), 4));
		sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString().toUpperCase();
	}
}
