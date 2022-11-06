package com.separate.fruit.service;


import com.separate.fruit.Dao.UsersDao;
import com.separate.fruit.entity.Users;
import com.separate.fruit.entity.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersDao usersDao;

    //删除用户
    @Transactional
    public boolean deleteUser(Integer userId) {
        return this.usersDao.deleteByPrimaryKey(userId);
    }

    //查询出用户
    @Transactional
    public Page<Users> selectUsersAll(int num, int size, Users users) {
        Page<Users> page = new Page<>();
        page.setList(this.usersDao.selectAllUsers(num, size, users));
        page.setNum(this.usersDao.UsersCount(users));
        return page;
    }

    //更新用户
    @Transactional
    public boolean editUser(Users users) {
        return this.usersDao.editUsers(users);
    }

    //手机号码进行验证用户是否能登录
    @Transactional
    public Users verifyPhoneLogin(String phone, String password) {
        return this.usersDao.UsePhoneLoginFindUser(phone, password);
    }

    //利用邮箱辅助登录
    @Transactional
    public Users verifyEmailLogin(String email) {
        return this.usersDao.UseEmailLoginFindUser(email);
    }

    //查询出用户id和用户姓名
    @Transactional
    public List<Users> whereListUser() {
        return this.usersDao.WhereUsers();
    }

    //利用用户id精确查询某个用户
    @Transactional
    public Users selectByUserId(Integer userId) {
        return this.usersDao.selectByPrimaryKey(userId);
    }

    //修改用户信息
    @Transactional
    public boolean editUsers(Users users) {
        return this.usersDao.editUsers(users);
    }

    //添加用户
    @Transactional
    public boolean insertUser(Users users) {
        return this.usersDao.insertUser(users);
    }
}