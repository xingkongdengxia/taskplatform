package com.smg.taskplatform.task.dao.model;

import java.io.Serializable;

public class TpTask implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer taskId;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 任务描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 发起人
     *
     * @mbg.generated
     */
    private String initiator;

    /**
     * 责任人
     *
     * @mbg.generated
     */
    private String responsibleman;

    /**
     * 执行人
     *
     * @mbg.generated
     */
    private String executor;

    /**
     * 抄送人
     *
     * @mbg.generated
     */
    private String cc;

    /**
     * 经办人历史序列
     *
     * @mbg.generated
     */
    private String operatorlist;

    /**
     * 任务类型
            0 -- 系统运维
            1 -- 系统升级
            2 -- 应急演练
            3 -- 其他
     *
     * @mbg.generated
     */
    private Byte taskType;

    /**
     * 任务来源
            0 -- 自建任务
            1 -- 源自OA系统的任务
     *
     * @mbg.generated
     */
    private Byte taskSource;

    /**
     * 优先级
            0 -- 低
            1 -- 中
            2 -- 高
     *
     * @mbg.generated
     */
    private Byte priority;

    /**
     * 开始时间
     *
     * @mbg.generated
     */
    private Long starttime;

    /**
     * 任务状态
            0 -- 进行中
            1 -- 已完成
            2 -- 已暂停
            3 -- 已作废
            4 -- 已关闭
     *
     * @mbg.generated
     */
    private Byte taskStatus;

    /**
     * 截止日期
     *
     * @mbg.generated
     */
    private Long endtime;

    /**
     * 任务关联
     *
     * @mbg.generated
     */
    private String taskAssociation;

    /**
     * 系统关联
     *
     * @mbg.generated
     */
    private String systemAssociation;

    /**
     * 服务器关联
     *
     * @mbg.generated
     */
    private String serverAssociation;

    /**
     * 汇报周期
            0 -- 每天
            1 -- 每3天
            2 -- 每周
            3 -- 每月
     *
     * @mbg.generated
     */
    private Byte reportingCycle;

    /**
     * 情况总结
     *
     * @mbg.generated
     */
    private String summary;

    private Long ctime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getResponsibleman() {
        return responsibleman;
    }

    public void setResponsibleman(String responsibleman) {
        this.responsibleman = responsibleman;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getOperatorlist() {
        return operatorlist;
    }

    public void setOperatorlist(String operatorlist) {
        this.operatorlist = operatorlist;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public Byte getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(Byte taskSource) {
        this.taskSource = taskSource;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getTaskAssociation() {
        return taskAssociation;
    }

    public void setTaskAssociation(String taskAssociation) {
        this.taskAssociation = taskAssociation;
    }

    public String getSystemAssociation() {
        return systemAssociation;
    }

    public void setSystemAssociation(String systemAssociation) {
        this.systemAssociation = systemAssociation;
    }

    public String getServerAssociation() {
        return serverAssociation;
    }

    public void setServerAssociation(String serverAssociation) {
        this.serverAssociation = serverAssociation;
    }

    public Byte getReportingCycle() {
        return reportingCycle;
    }

    public void setReportingCycle(Byte reportingCycle) {
        this.reportingCycle = reportingCycle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", initiator=").append(initiator);
        sb.append(", responsibleman=").append(responsibleman);
        sb.append(", executor=").append(executor);
        sb.append(", cc=").append(cc);
        sb.append(", operatorlist=").append(operatorlist);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskSource=").append(taskSource);
        sb.append(", priority=").append(priority);
        sb.append(", starttime=").append(starttime);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", endtime=").append(endtime);
        sb.append(", taskAssociation=").append(taskAssociation);
        sb.append(", systemAssociation=").append(systemAssociation);
        sb.append(", serverAssociation=").append(serverAssociation);
        sb.append(", reportingCycle=").append(reportingCycle);
        sb.append(", summary=").append(summary);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TpTask other = (TpTask) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getInitiator() == null ? other.getInitiator() == null : this.getInitiator().equals(other.getInitiator()))
            && (this.getResponsibleman() == null ? other.getResponsibleman() == null : this.getResponsibleman().equals(other.getResponsibleman()))
            && (this.getExecutor() == null ? other.getExecutor() == null : this.getExecutor().equals(other.getExecutor()))
            && (this.getCc() == null ? other.getCc() == null : this.getCc().equals(other.getCc()))
            && (this.getOperatorlist() == null ? other.getOperatorlist() == null : this.getOperatorlist().equals(other.getOperatorlist()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getTaskSource() == null ? other.getTaskSource() == null : this.getTaskSource().equals(other.getTaskSource()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getTaskStatus() == null ? other.getTaskStatus() == null : this.getTaskStatus().equals(other.getTaskStatus()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getTaskAssociation() == null ? other.getTaskAssociation() == null : this.getTaskAssociation().equals(other.getTaskAssociation()))
            && (this.getSystemAssociation() == null ? other.getSystemAssociation() == null : this.getSystemAssociation().equals(other.getSystemAssociation()))
            && (this.getServerAssociation() == null ? other.getServerAssociation() == null : this.getServerAssociation().equals(other.getServerAssociation()))
            && (this.getReportingCycle() == null ? other.getReportingCycle() == null : this.getReportingCycle().equals(other.getReportingCycle()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getInitiator() == null) ? 0 : getInitiator().hashCode());
        result = prime * result + ((getResponsibleman() == null) ? 0 : getResponsibleman().hashCode());
        result = prime * result + ((getExecutor() == null) ? 0 : getExecutor().hashCode());
        result = prime * result + ((getCc() == null) ? 0 : getCc().hashCode());
        result = prime * result + ((getOperatorlist() == null) ? 0 : getOperatorlist().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getTaskSource() == null) ? 0 : getTaskSource().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getTaskAssociation() == null) ? 0 : getTaskAssociation().hashCode());
        result = prime * result + ((getSystemAssociation() == null) ? 0 : getSystemAssociation().hashCode());
        result = prime * result + ((getServerAssociation() == null) ? 0 : getServerAssociation().hashCode());
        result = prime * result + ((getReportingCycle() == null) ? 0 : getReportingCycle().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }
}