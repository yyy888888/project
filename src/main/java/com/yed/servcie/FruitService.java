package com.yed.servcie;

import com.github.pagehelper.PageInfo;
import com.yed.bean.Area;
import com.yed.bean.Fruits;
import com.yed.bean.Ftype;

import java.util.List;

public interface FruitService {
    PageInfo<Fruits> getFruList(Fruits fruits,Integer pageNum,Integer pageSize);

    int saveFruit(Fruits fruits);

    int delFruits(String ids);

    List<Area> getAreaByPid(Integer pid);

    List<Ftype> getTypes();

    Fruits getFruitByid(Integer id);

    List<Integer> getTidByFid(Integer id);

    List<Area> getAllArea();
}
