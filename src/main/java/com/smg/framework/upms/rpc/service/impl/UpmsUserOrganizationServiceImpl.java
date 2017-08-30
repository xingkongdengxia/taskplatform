package com.smg.framework.upms.rpc.service.impl;

import com.smg.framework.common.annotation.BaseService;
import com.smg.framework.common.base.BaseServiceImpl;
import com.smg.framework.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.smg.framework.upms.dao.model.UpmsUserOrganization;
import com.smg.framework.upms.dao.model.UpmsUserOrganizationExample;
import com.smg.framework.upms.rpc.api.UpmsUserOrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsUserOrganizationService实现
 *
 * @author justincai
 */
@Service
@Transactional
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static final Log log = LogFactory.getLog(UpmsUserOrganizationServiceImpl.class);

    private UpmsUserOrganizationMapper upmsUserOrganizationMapper;

    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        upmsUserOrganizationMapper.deleteByExample(upmsUserOrganizationExample);
        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
                upmsUserOrganization.setUserId(id);
                upmsUserOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = upmsUserOrganizationMapper.insertSelective(upmsUserOrganization);
            }
        }
        return result;
    }

    public UpmsUserOrganizationMapper getUpmsUserOrganizationMapper() {
        return upmsUserOrganizationMapper;
    }

    public void setUpmsUserOrganizationMapper(UpmsUserOrganizationMapper upmsUserOrganizationMapper) {
        this.upmsUserOrganizationMapper = upmsUserOrganizationMapper;
    }

}
