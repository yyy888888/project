package com.yed.servcie;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yed.bean.Area;
import com.yed.bean.Fruits;
import com.yed.bean.FruitsType;
import com.yed.bean.Ftype;
import com.yed.dao.AreaDao;
import com.yed.dao.FruTypesDao;
import com.yed.dao.FruitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Repository
@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitDao fruitDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private FruTypesDao fruTypesDao;

    @Override
    public PageInfo<Fruits> getFruList(Fruits fruits, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Fruits> fList = fruitDao.getFList(fruits);
        List<Area> allArea = areaDao.getAllArea();
        List<Ftype> types = fruTypesDao.getTypes();
        fList.forEach(fruit -> {
            allArea.forEach(area -> {
                if (fruit.getSheng().intValue()==area.getId().intValue()){
                    fruit.setAddress(area.getName());
                }
                if (fruit.getShi().intValue()==area.getId().intValue()){
                    fruit.setAddress(fruit.getAddress()+area.getName());
                }
            });
            List<Integer> tidList = fruTypesDao.getTidByFid(fruit.getId());
            StringBuffer tNames = new StringBuffer();
            /*System.out.println(tidList);*/
            types.forEach(type -> {
                tidList.forEach(tid->{
                    if (type.getId()==tid){
                        if ("".equals(tNames.toString())){
                            tNames.append(type.getName());
                        }else{
                            tNames.append("/"+type.getName());
                        }
                    }

                });
            });
                    fruit.setChuan(tNames.toString());
        });

        return new PageInfo<>(fList);
    }

    @Override
    public int saveFruit(Fruits fruits) {
        if (fruits.getId()==null){
            System.out.println("添加*****************************************");
            fruitDao.addFruit(fruits);
            Integer[] cxids = fruits.getCxids();
            if (cxids!=null){
                for (Integer tid:cxids){
                    FruitsType fruitsType = new FruitsType(fruits.getId(), tid);
                    fruTypesDao.addFruType(fruitsType);
                }

            }
            return 1;
        }else {
            System.out.println("修改*****************************************");
            fruTypesDao.delFruType(fruits.getId().toString());
            Integer[] cxids = fruits.getCxids();
            if (cxids!=null){
                for (Integer tid:cxids){
                    FruitsType fruitsType = new FruitsType(fruits.getId(), tid);
                    fruTypesDao.addFruType(fruitsType);
                }

            }
            return fruitDao.updateFruit(fruits);
        }
    }

    @Override
    public int delFruits(String ids) {
        String[] idss = ids.split(",");
        if (idss!=null){
            for (String id:idss) {
                Fruits f = fruitDao.getFruitByid(Integer.parseInt(id));
                String fileName = f.getFileName();
                File file = new File("e:/pic/" + fileName);
                file.delete();
            }
        }
        fruTypesDao.delFruType(ids);
        return fruitDao.delFruits(ids);
    }

    @Override
    public List<Area> getAreaByPid(Integer pid) {
        return areaDao.getAreaByPid(pid);
    }

    @Override
    public List<Ftype> getTypes() {
        return fruTypesDao.getTypes();
    }

    @Override
    public Fruits getFruitByid(Integer id) {
        return fruitDao.getFruitByid(id);
    }

    @Override
    public List<Integer> getTidByFid(Integer id) {
        return fruTypesDao.getTidByFid(id);
    }

    @Override
    public List<Area> getAllArea() {
        return areaDao.getAllArea();
    }
}
