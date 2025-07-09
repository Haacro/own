package com.jk2102.service;

import com.jk2102.pojo.PageBean;
import com.jk2102.pojo.Relics;

import java.time.LocalDate;
import java.util.List;

public interface RelicsService {

    //查询全部文物
    /*List<Relics> getAll();*/

    //根据id查询单个文物
    Relics getById(Integer id);

    //条件分页查询
    PageBean page(Integer page, Integer pageSize, String name, String category, String era);

    List<Relics> getSimilar(Integer id);
}
