package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {   //抽象方法
    @Insert("insert into user (username,password) values (#{username},#{password})")
    void adduser(User user);

    @Select("select * from user where username=#{username}")
    User getuser(String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(String username,String password);
}
