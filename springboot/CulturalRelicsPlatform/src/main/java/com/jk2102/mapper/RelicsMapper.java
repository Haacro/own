package com.jk2102.mapper;

import com.jk2102.pojo.Relics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RelicsMapper {


    /*
    * 查询全部文物信息
    * */
    /*@Select("select * from museum.museum")
    List<Relics> getAll();*/

    @Select("select * from museum.museum where id = #{id}")
    Relics getById(Integer id);

    public List<Relics> list(String name, String category, String era);

    @Select("select * from museum.museum where category = (select category from museum.museum where id = #{id})")
    public List<Relics> getByThisId(Integer id);
}
