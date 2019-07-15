package com.ggg.taskplatform.task.dao.mapper;

import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuser;
import com.ggg.taskplatform.task.dao.model.TpTaskOperWeiyiuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TpTaskOperWeiyiuserMapper {
    long countByExample(TpTaskOperWeiyiuserExample example);

    int deleteByExample(TpTaskOperWeiyiuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TpTaskOperWeiyiuser record);

    int insertSelective(TpTaskOperWeiyiuser record);

    List<TpTaskOperWeiyiuser> selectByExample(TpTaskOperWeiyiuserExample example);

    TpTaskOperWeiyiuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TpTaskOperWeiyiuser record, @Param("example") TpTaskOperWeiyiuserExample example);

    int updateByExample(@Param("record") TpTaskOperWeiyiuser record, @Param("example") TpTaskOperWeiyiuserExample example);

    int updateByPrimaryKeySelective(TpTaskOperWeiyiuser record);

    int updateByPrimaryKey(TpTaskOperWeiyiuser record);
}