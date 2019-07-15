package com.ggg.taskplatform.task.dao.model;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TpTaskOperHistoryChild extends TpTaskOperHistory implements Serializable {
	private static final Log log = LogFactory.getLog(TpTaskOperHistoryChild.class);
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 任务名称
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 操作人名称
     *
     * @mbg.generated
     */
    private String operatorName;
    
    /**
     * 操作内容
     *
     * @mbg.generated
     */
    private String actionTypeName;
    
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private String cTimeName;
    

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getActionTypeName() {
		return actionTypeName;
	}

	public void setActionTypeName(String actionTypeName) {
		this.actionTypeName = actionTypeName;
	}

	public String getcTimeName() {
		return cTimeName;
	}

	public void setcTimeName(String cTimeName) {
		this.cTimeName = cTimeName;
	}

	
	
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public Integer getTaskId() {
		// TODO Auto-generated method stub
		return super.getTaskId();
	}

	@Override
	public void setTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		super.setTaskId(taskId);
	}

	@Override
	public String getOperator() {
		// TODO Auto-generated method stub
		return super.getOperator();
	}

	@Override
	public void setOperator(String operator) {
		// TODO Auto-generated method stub
		super.setOperator(operator);
	}

	@Override
	public Byte getActionType() {
		// TODO Auto-generated method stub
		return super.getActionType();
	}

	@Override
	public void setActionType(Byte actionType) {
		// TODO Auto-generated method stub
		super.setActionType(actionType);
	}

	@Override
	public String getRemark() {
		// TODO Auto-generated method stub
		return super.getRemark();
	}

	@Override
	public void setRemark(String remark) {
		// TODO Auto-generated method stub
		super.setRemark(remark);
	}

	@Override
	public Long getCtime() {
		// TODO Auto-generated method stub
		return super.getCtime();
	}

	@Override
	public void setCtime(Long ctime) {
		// TODO Auto-generated method stub
		super.setCtime(ctime);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public boolean equals(Object that) {
		// TODO Auto-generated method stub
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	
}