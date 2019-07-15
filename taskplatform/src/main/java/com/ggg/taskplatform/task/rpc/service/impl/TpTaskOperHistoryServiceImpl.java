package com.ggg.taskplatform.task.rpc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggg.taskplatform.task.dao.mapper.TpTaskOperHistoryMapper;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.ggg.taskplatform.task.dao.model.TpTaskOperHistoryExample;
import com.ggg.taskplatform.task.rpc.api.TpTaskOperHistoryService;
import com.magicube.framework.common.annotation.BaseService;
import com.magicube.framework.common.base.BaseServiceImpl;

/**
* TpTaskOperHistoryService实现
* Created by justincai on 2018/6/11.
*/
@Service
@Transactional
@BaseService
public class TpTaskOperHistoryServiceImpl extends BaseServiceImpl<TpTaskOperHistoryMapper, TpTaskOperHistory, TpTaskOperHistoryExample> implements TpTaskOperHistoryService {

    private static final Log log = LogFactory.getLog(TpTaskOperHistoryServiceImpl.class);

    @Autowired
    TpTaskOperHistoryMapper tpTaskOperHistoryMapper;
    
    /**
     * 新增任务历史记录
     * @param taskId		任务ID
     * @param remark		操作描述
     * @param actionType	操作内容
     */
    @Override
    public void addTpTaskOperHistory(Integer taskId,String remark,Byte actionType) {
    	log.info("新增任务历史记录          任务ID = "+taskId+"，操作描述 = "+remark+"，操作内容 = "+actionType);
    	
    	TpTaskOperHistory tpTaskOperHistory = new TpTaskOperHistory();
    	
    	tpTaskOperHistory.setTaskId(taskId);//任务id
    	tpTaskOperHistory.setRemark(remark);//任务备注
    	
    	//得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        tpTaskOperHistory.setOperator(username);
        
        tpTaskOperHistory.setActionType(actionType);//操作内容
        
        long time = System.currentTimeMillis();
        tpTaskOperHistory.setCtime(time);//任务创建时间
        
    	tpTaskOperHistoryMapper.insertSelective(tpTaskOperHistory);
	}
    

}