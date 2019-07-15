package com.magicube.framework.operator.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicube.framework.operator.UpmsLogOperator;
import com.magicube.framework.upms.dao.model.UpmsLog;
import com.magicube.framework.upms.rpc.api.UpmsLogService;
@Service
public class UpmsLogOperatorImpl implements UpmsLogOperator{
	private static final Log log = LogFactory.getLog(UpmsLogOperatorImpl.class);

    @Autowired
    private UpmsLogService upmsLogService;

    /**
     * 添加日志
     */
	@Override
	public int addUpmsLog(UpmsLog upmsLog) {
		
		
		return 0;
	}
    
    
}
