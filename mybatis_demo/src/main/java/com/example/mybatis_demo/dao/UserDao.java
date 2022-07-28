package com.example.mybatis_demo.dao;

import com.example.mybatis_demo.entity.User;

import java.util.List;

//@Mapper
public interface UserDao {
    public User findUserById(Integer id);
    List<User> findAllUsers();

    int insertUser(User User);

    int updUser(User User);

    int delUser(Integer id);
}
