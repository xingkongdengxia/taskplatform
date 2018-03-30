package com.magicube.framework.upms.rpc.api;

import com.magicube.framework.common.base.BaseService;
import com.magicube.framework.upms.dao.model.UpmsUserOrganization;
import com.magicube.framework.upms.dao.model.UpmsUserOrganizationExample;

/**
 * UpmsUserOrganizationService接口
 *
 * @author justincai
 */
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /**
     * 用户组织
     *
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}
