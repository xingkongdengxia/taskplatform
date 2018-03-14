package com.magicube.framework.common.constant;

import com.magicube.framework.common.base.BaseResult;

/**
 * upms系统常量枚举类
 *
 * @author justincai
 */
public class UpmsResult extends BaseResult {

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

}
