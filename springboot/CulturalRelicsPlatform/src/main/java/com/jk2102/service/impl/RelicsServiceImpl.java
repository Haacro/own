package com.jk2102.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jk2102.mapper.RelicsMapper;
import com.jk2102.pojo.PageBean;
import com.jk2102.pojo.Relics;
import com.jk2102.service.RelicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class RelicsServiceImpl implements RelicsService {

    @Autowired
    private RelicsMapper relicsMapper;
    /*@Override
    public List<Relics> getAll() {
        log.info("getAll");
        return relicsMapper.getAll();
    }*/

    @Override
    public Relics getById(Integer id) {
        return relicsMapper.getById(id);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, String category, String era) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);

        //执行查询
        List<Relics> empList = relicsMapper.list(name, category, era);
        Page<Relics> p = (Page<Relics>) empList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public List<Relics> getSimilar(Integer id) {
        return relicsMapper.getByThisId(id);
    }
}
