package com.smg.taskplatform.task.dao.model;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author justincai
 */
public class TpTaskChild extends TpTask {

    private static final Log log = LogFactory.getLog(TpTaskChild.class);

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date showStarttime;

    /**
     * 截止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date showEndtime;

    public Date getShowStarttime() {
        return showStarttime;
    }

    public void setShowStarttime(Date showStarttime) {
        this.showStarttime = showStarttime;
        //设置毫秒数
        setStarttime(showStarttime.getTime());
    }

    public Date getShowEndtime() {
        return showEndtime;
    }

    public void setShowEndtime(Date showEndtime) {
        this.showEndtime = showEndtime;
        setEndtime(showEndtime.getTime());
    }

    @Override
    public void setSummary(String summary) {
        super.setSummary(summary); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSummary() {
        return super.getSummary(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReportingCycle(Byte reportingCycle) {
        super.setReportingCycle(reportingCycle); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getReportingCycle() {
        return super.getReportingCycle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setServerAssociation(String serverAssociation) {
        super.setServerAssociation(serverAssociation); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getServerAssociation() {
        return super.getServerAssociation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSystemAssociation(String systemAssociation) {
        super.setSystemAssociation(systemAssociation); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSystemAssociation() {
        return super.getSystemAssociation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskAssociation(String taskAssociation) {
        super.setTaskAssociation(taskAssociation); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTaskAssociation() {
        return super.getTaskAssociation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndtime(Long endtime) {
        super.setEndtime(endtime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getEndtime() {
        return super.getEndtime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskStatus(Byte taskStatus) {
        super.setTaskStatus(taskStatus); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getTaskStatus() {
        return super.getTaskStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStarttime(Long starttime) {
        super.setStarttime(starttime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getStarttime() {
        return super.getStarttime(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPriority(Byte priority) {
        super.setPriority(priority); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getPriority() {
        return super.getPriority(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskSource(Byte taskSource) {
        super.setTaskSource(taskSource); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getTaskSource() {
        return super.getTaskSource(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskType(Byte taskType) {
        super.setTaskType(taskType); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Byte getTaskType() {
        return super.getTaskType(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOperatorlist(String operatorlist) {
        super.setOperatorlist(operatorlist); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOperatorlist() {
        return super.getOperatorlist(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCc(String cc) {
        super.setCc(cc); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCc() {
        return super.getCc(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setExecutor(String executor) {
        super.setExecutor(executor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getExecutor() {
        return super.getExecutor(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setResponsibleman(String responsibleman) {
        super.setResponsibleman(responsibleman); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getResponsibleman() {
        return super.getResponsibleman(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInitiator(String initiator) {
        super.setInitiator(initiator); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInitiator() {
        return super.getInitiator(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return super.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskId(Integer taskId) {
        super.setTaskId(taskId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getTaskId() {
        return super.getTaskId(); //To change body of generated methods, choose Tools | Templates.
    }

}
