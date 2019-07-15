package com.ggg.taskplatform.task.dao.mapper;

import com.ggg.taskplatform.task.dao.model.TpTask;
import com.ggg.taskplatform.task.dao.model.TpTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TpTaskMapper {
    long countByExample(TpTaskExample example);

    int deleteByExample(TpTaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TpTask record);

    int insertSelective(TpTask record);

    List<TpTask> selectByExample(TpTaskExample example);

    TpTask selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TpTask record, @Param("example") TpTaskExample example);

    int updateByExample(@Param("record") TpTask record, @Param("example") TpTaskExample example);

    int updateByPrimaryKeySelective(TpTask record);

    int updateByPrimaryKey(TpTask record);
}