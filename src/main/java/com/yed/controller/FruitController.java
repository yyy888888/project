package com.yed.controller;


import com.yed.bean.Area;
import com.yed.bean.Fruits;
import com.yed.bean.Ftype;
import com.yed.servcie.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("fruit")
@CrossOrigin
public class FruitController {

    @Autowired
    private FruitService fruitService;


    @GetMapping("flist")
    public Object getList(Fruits fruits, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "1")Integer pageSize){
            return fruitService.getFruList(fruits,pageNum,pageSize);
    }

    @PostMapping("savef")
    public int SaveFruit(@RequestBody Fruits fruits){
        System.out.println(fruits.getCxids());
        return fruitService.saveFruit(fruits);
    }

    @PostMapping("del")
    public int DelFs(String  ids){
        return fruitService.delFruits(ids);
    }

    @GetMapping("initdata")
    public Object DataInit(@RequestParam(defaultValue = "1") Integer pid){
        Map<String,Object> rmap = new HashMap<>();
        List<Ftype> types = fruitService.getTypes();
        List<Area> areaList = fruitService.getAreaByPid(pid);
        rmap.put("typeList",types);
        rmap.put("areaList",areaList);
        return rmap;
    }

    @GetMapping("getArea")
    public Object getArea(Integer pid){
        return fruitService.getAreaByPid(pid);
    }


    @GetMapping("getfbyid")
    public Object getByid(Integer id){
        Map<String,Object> resMap= new HashMap<>();
        Fruits fruit = fruitService.getFruitByid(id);
        String fileName = fruit.getFileName();
        List<Area> shiList = fruitService.getAreaByPid(fruit.getSheng());
        List<Integer> Ftids = fruitService.getTidByFid(fruit.getId());
        List<Ftype> types = fruitService.getTypes();
        List<Area> allArea = fruitService.getAllArea();

        allArea.forEach(area -> {

            if (fruit.getSheng().intValue()==area.getId().intValue()){
               fruit.setAddress(area.getName());
            }
            if (fruit.getShi().intValue()==area.getId().intValue()){
                fruit.setAddress(fruit.getAddress()+area.getName());
            }

        });


        StringBuffer chuan = new StringBuffer();
        types.forEach(type->{
            Ftids.forEach(tid->{
                if (tid==type.getId()){
                    if ("".equals(chuan.toString())){
                        chuan.append(type.getName());
                    }else{
                        chuan.append("/"+type.getName());
                    }
                }
            });

        });
        fruit.setChuan(chuan.toString());
        resMap.put("fruit",fruit);
        resMap.put("shiList",shiList);
        resMap.put("fileName",fileName);
        resMap.put("Ftids",Ftids);
        return resMap;
    }




}
