package com.nfxy.util;

import org.junit.Assert;
import org.junit.Test;

public class MD5UtilsTest {
	
	@Test
	public void testMd5Hex() {
		String data = "123456789";
		Assert.assertArrayEquals(new String[]{"b0d426b1f1815f588354b323497e9f52"}, 
				new String[]{MD5Utils.md5Hex(data)});
	}
	
}
