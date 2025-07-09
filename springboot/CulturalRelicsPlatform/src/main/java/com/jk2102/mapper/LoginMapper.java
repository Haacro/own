package com.jk2102.mapper;

import com.jk2102.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    /**
     * 根据用户名和密码查询
     * @param users
     * @return
     */
    @Select("select * from museum.users where usersname = #{usersname} and password = #{password}")
    Users getByUsernameAndPassword(Users users);
}
