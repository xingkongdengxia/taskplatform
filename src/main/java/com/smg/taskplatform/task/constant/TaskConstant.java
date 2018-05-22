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

}
