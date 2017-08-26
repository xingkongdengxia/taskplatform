package com.smg.framework.upms.dao.mapper;

import com.smg.framework.upms.dao.model.UpmsUserOrganization;
import com.smg.framework.upms.dao.model.UpmsUserOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author justincai
 */
public interface UpmsUserOrganizationMapper {
    
    long countByExample(UpmsUserOrganizationExample example);

    int deleteByExample(UpmsUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(UpmsUserOrganization record);

    int insertSelective(UpmsUserOrganization record);

    List<UpmsUserOrganization> selectByExample(UpmsUserOrganizationExample example);

    UpmsUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") UpmsUserOrganization record, @Param("example") UpmsUserOrganizationExample example);

    int updateByExample(@Param("record") UpmsUserOrganization record, @Param("example") UpmsUserOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsUserOrganization record);

    int updateByPrimaryKey(UpmsUserOrganization record);
    
}
