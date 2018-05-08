package com.magicube.framework.config.dao.mapper;

import com.magicube.framework.config.dao.model.ParamConfig;
import com.magicube.framework.config.dao.model.ParamConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParamConfigMapper {
    long countByExample(ParamConfigExample example);

    int deleteByExample(ParamConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParamConfig record);

    int insertSelective(ParamConfig record);

    List<ParamConfig> selectByExample(ParamConfigExample example);

    ParamConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ParamConfig record, @Param("example") ParamConfigExample example);

    int updateByExample(@Param("record") ParamConfig record, @Param("example") ParamConfigExample example);

    int updateByPrimaryKeySelective(ParamConfig record);

    int updateByPrimaryKey(ParamConfig record);
}