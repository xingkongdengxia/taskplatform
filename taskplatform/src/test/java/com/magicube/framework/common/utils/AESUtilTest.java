package com.magicube.framework.common.utils;

import org.junit.Test;

public class AESUtilTest {
	public AESUtilTest() {
    }
	
	/**
	 * SQL密码加解密
	 */
	@Test
	public void AESSqltest() {
		
		String content = "srhXCv1iaQriQEiS283WIw==";
		String string = AESUtil.AESDecode(content);
		System.out.println(string);//测试加密方式
		
		//加密
		String contentsql = "chen92649264";
		String aesEncode = AESUtil.AESEncode(contentsql);
		System.out.println(aesEncode);
	}
}
