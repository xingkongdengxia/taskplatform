package com.magicube.framework.upms.rpc.api;

import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsUser;
import com.magicube.framework.upms.dao.model.UpmsUserExample;

/**
 * UpmsUserService接口
 *
 * @author justincai
 */
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}
