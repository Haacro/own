package com.jk2102.controller;


import com.jk2102.pojo.PageBean;
import com.jk2102.pojo.Relics;
import com.jk2102.pojo.Result;
import com.jk2102.service.RelicsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/browse")
@CrossOrigin
public class RelicsController {
    @Autowired
    private RelicsService relicsService;
//    @GetMapping
//    public Result getAll(){
////        return relicsService.getAll();
//        log.info("getAll");
//
//        List<Relics> relicsList = relicsService.getAll();
//        return Result.success(relicsList);
//    }

    //根据id查询文物信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("getById");
        Relics relics = relicsService.getById(id);
        return Result.success(relics);
    }

    //分页条件查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10000") Integer pageSize,
                       String name, String category, String era) {
        log.info("分页查询，参数:{},{},{},{},{}", page, pageSize, name, category, era);
        //调用service分页查询
        PageBean pageBean = relicsService.page(page, pageSize, name, category, era);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}/similar")
    public Result getSimilar(@PathVariable Integer id) {
        log.info("getSimilar");
        List<Relics> relicsList = relicsService.getSimilar(id);
        return Result.success(relicsList);
    }
}
