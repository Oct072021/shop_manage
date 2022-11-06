package com.separate.fruit.Dao;

import com.separate.fruit.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDao {
    //查询出指定Id
    Users selectByPrimaryKey(Integer userId);

    //查询所有用户
    List<Users> selectAllUsers(@Param("num") Integer num, @Param("size") Integer size,
                               @Param("users") Users users);

    //删除指定用户
    boolean deleteByPrimaryKey(Integer userId);

    //使用手机登录找到用户
    Users UsePhoneLoginFindUser(String phone, String password);

    //使用邮箱找到用户并进行验证登录
    Users UseEmailLoginFindUser(String email);

    //查询数据库中有多少数据
    int UsersCount(@Param("users") Users users);

    //添加用户数据
    boolean insertUser(Users users);

    //修改用户数据
    boolean editUsers(Users users);

    //只返回用户id和用户名字
    List<Users> WhereUsers();
}