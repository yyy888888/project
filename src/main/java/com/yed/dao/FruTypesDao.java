package com.yed.dao;

import com.yed.bean.FruitsType;
import com.yed.bean.Ftype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FruTypesDao {

    @Select("select * from clx")
    List<Ftype> getTypes();

    @Insert("insert into fruit_type(fid,tid) values(#{fid},#{tid})")
    int addFruType(FruitsType fruitsType);

    @Delete("delete from fruit_type where fid in(#{value})")
    int delFruType(String ids);


    @Select("select tid from fruit_type where fid=${value}")
    List<Integer> getTidByFid(Integer id);


}
