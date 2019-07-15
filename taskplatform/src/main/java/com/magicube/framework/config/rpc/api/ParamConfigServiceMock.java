package com.magicube.framework.config.rpc.api;

import com.magicube.framework.common.base.BaseServiceMock;
import com.magicube.framework.config.dao.mapper.ParamConfigMapper;
import com.magicube.framework.config.dao.model.ParamConfig;
import com.magicube.framework.config.dao.model.ParamConfigExample;

/**
* 降级实现ParamConfigService接口
* Created by justincai on 2017/12/24.
*/
public class ParamConfigServiceMock extends BaseServiceMock<ParamConfigMapper, ParamConfig, ParamConfigExample> implements ParamConfigService {

}
