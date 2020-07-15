package com.yed.dao;

import com.yed.bean.Fruits;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FruitDao {

    List<Fruits> getFList(@Param("f") Fruits fruits);

    int addFruit(Fruits fruits);

    int updateFruit(Fruits fruits);

    @Delete("delete from fruit where id in(${value})")
    int delFruits(String ids);

    @Select("select * from fruit where id=${value}")
    Fruits getFruitByid(Integer id);
}
