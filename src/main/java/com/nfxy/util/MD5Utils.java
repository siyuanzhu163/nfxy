package com.nfxy.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * MD5工具类
 * 将MD5加密后的密文进行反转
 * @author SIYUAN
 *
 */
public class MD5Utils {
	
	public static String md5Hex(byte[] data) {
		return StringUtils.reverse(DigestUtils.md5Hex(data));
	}
	
	public static String md5Hex(InputStream data) throws IOException {
		return StringUtils.reverse(DigestUtils.md5Hex(data));
	}
	
	public static String md5Hex(String data) {
		return StringUtils.reverse(DigestUtils.md5Hex(data));
	}
	
}
