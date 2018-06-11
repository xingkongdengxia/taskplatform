package com.smg.taskplatform.task.dao.mapper;

import com.smg.taskplatform.task.dao.model.TpTaskOperHistory;
import com.smg.taskplatform.task.dao.model.TpTaskOperHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TpTaskOperHistoryMapper {
    long countByExample(TpTaskOperHistoryExample example);

    int deleteByExample(TpTaskOperHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TpTaskOperHistory record);

    int insertSelective(TpTaskOperHistory record);

    List<TpTaskOperHistory> selectByExample(TpTaskOperHistoryExample example);

    TpTaskOperHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TpTaskOperHistory record, @Param("example") TpTaskOperHistoryExample example);

    int updateByExample(@Param("record") TpTaskOperHistory record, @Param("example") TpTaskOperHistoryExample example);

    int updateByPrimaryKeySelective(TpTaskOperHistory record);

    int updateByPrimaryKey(TpTaskOperHistory record);
}