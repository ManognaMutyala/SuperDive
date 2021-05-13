package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from users where username=#{username}")
    User getUserDetails(String username);

    @Select("select userid from users where username=#{username}")
    int getUserId(String username);

    @Insert("insert into users(username, salt, password, firstname, lastname) values (#{username},#{salt}, #{password}, #{firstname}, #{lastname})" )
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    int insert (User user);

    @Select("select password from users where username= #{username}")
    String getPassword(String username);
}
