package com.yed.dao;

import com.yed.bean.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaDao {
    @Select("select id,name,pid from t_area")
    List<Area> getAllArea();

    @Select("select id,name,pid from t_area where pid=${value}")
    List<Area> getAreaByPid(Integer pid);
}
