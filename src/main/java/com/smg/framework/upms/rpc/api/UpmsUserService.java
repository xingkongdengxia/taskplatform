package com.smg.framework.upms.rpc.api;

import com.smg.framework.common.base.BaseService;
import com.smg.framework.upms.dao.model.UpmsUser;
import com.smg.framework.upms.dao.model.UpmsUserExample;

/**
 * UpmsUserService接口
 *
 * @author justincai
 */
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}
