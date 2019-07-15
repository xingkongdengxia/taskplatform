package com.magicube.framework.operator;

import com.magicube.framework.upms.dao.model.UpmsLog;

public interface UpmsLogOperator {

	/**
	 * 添加日志
	 * @param upmsLog
	 * @return
	 */
	int addUpmsLog(UpmsLog upmsLog);
}
