package com.smg.taskplatform.task.constant;

import com.magicube.framework.common.base.BaseConstants;

/**
 * Task常量类
 *
 * @author justincai
 */
public class TaskConstant extends BaseConstants {

    //任务来源
    public static final Byte TASK_SOURCE_SELF = 0;   //自建任务
    public static final Byte TASK_SOURCE_OA = 1;   //源自OA系统的任务

    //任务类型
    public static final Byte TASK_TYPE_SYSMAINTENANCE = 0;   //系统运维
    public static final Byte TASK_TYPE_SYSUPGRADE = 1;   //系统升级
    public static final Byte TASK_TYPE_EMERGENCY = 2;   //应急演练
    public static final Byte TASK_TYPE_OTHER = 3;   //其他

    //任务状态
    public static final Byte TASK_STATUS_INPROGRESS = 0;   //进行中
    public static final Byte TASK_STATUS_COMPLETED = 1;   //已完成
    public static final Byte TASK_STATUS_SUSPENDED = 2;   //已暂停
    public static final Byte TASK_STATUS_ABANDONED = 3;   //已作废
    public static final Byte TASK_STATUS_CLOSED = 4;   //已关闭

    //汇报周期
    public static final Byte REPORTING_CYCLE_EVERYDAY = 0;   //每天
    public static final Byte REPORTING_CYCLE_EVERY3DAY = 1;   //每3天
    public static final Byte REPORTING_CYCLE_EVERYWEEK = 2;   //每周
    public static final Byte REPORTING_CYCLE_EVERYMONTH = 3;   //每月

    //优先级
    public static final Byte PRIORITY_LOW = 0;   //低
    public static final Byte PRIORITY_MIDDLE = 1;   //中
    public static final Byte PRIORITY_HIGH = 2;   //高

    /**
     * 得到任务来源名称
     *
     * @param taskSource
     * @return
     */
    public static String getTaskSourceName(Byte taskSource) {

        if (taskSource.compareTo(TASK_SOURCE_SELF) == 0) {     //自建任务
            return "自建任务";
        } else if (taskSource.compareTo(TASK_SOURCE_OA) == 0) {    //源自OA系统的任务
            return "源自OA";
        } else {
            return "未知";
        }

    }

    /**
     * 得到任务类型名称
     *
     * @param taskType
     * @return
     */
    public static String getTaskTypeName(Byte taskType) {

        if (taskType.compareTo(TASK_TYPE_SYSMAINTENANCE) == 0) {     //系统运维
            return "系统运维";
        } else if (taskType.compareTo(TASK_TYPE_SYSUPGRADE) == 0) {    //系统升级
            return "系统升级";
        } else if (taskType.compareTo(TASK_TYPE_EMERGENCY) == 0) {    //应急演练
            return "应急演练";
        } else if (taskType.compareTo(TASK_TYPE_OTHER) == 0) {    //其他
            return "其他";
        } else {
            return "未知";
        }

    }

    /**
     * 得到任务状态名称
     *
     * @param taskStatus
     * @return
     */
    public static String getTaskStatusName(Byte taskStatus) {

        if (taskStatus.compareTo(TASK_STATUS_INPROGRESS) == 0) {     //进行中
            return "进行中";
        } else if (taskStatus.compareTo(TASK_STATUS_COMPLETED) == 0) {    //已完成
            return "已完成";
        } else if (taskStatus.compareTo(TASK_STATUS_SUSPENDED) == 0) {    //已暂停
            return "已暂停";
        } else if (taskStatus.compareTo(TASK_STATUS_ABANDONED) == 0) {    //已作废
            return "已作废";
        } else if (taskStatus.compareTo(TASK_STATUS_CLOSED) == 0) {    //已关闭
            return "已关闭";
        } else {
            return "未知";
        }

    }

    /**
     * 得到优先级名称
     *
     * @param priority
     * @return
     */
    public static String getPriorityName(Byte priority) {

        if (priority.compareTo(PRIORITY_LOW) == 0) {     //低
            return "低";
        } else if (priority.compareTo(PRIORITY_MIDDLE) == 0) {    //中
            return "中";
        } else if (priority.compareTo(PRIORITY_HIGH) == 0) {    //高
            return "高";
        } else {
            return "未知";
        }

    }

}
