package com.magicube.framework.common.utils;

import org.apache.log4j.jdbc.JDBCAppender;

public class ConnectionFactory extends JDBCAppender {
	

	@Override
	public void setPassword(String password) {
		try {
			password = AESUtil.AESDecode(password);// 数据库解密
		} catch (Exception e) {
			e.printStackTrace();
			}
		this.databasePassword = password;
	}

}
