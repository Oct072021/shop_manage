package com.separate.fruit.cotroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.separate.fruit.entity.Users;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.service.UsersService;
import com.separate.fruit.utils.JwtUtil;
import com.separate.fruit.utils.RandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {
    /**
     * 自动注入业务层
     */
    @Autowired
    private UsersService usersService;
    /**
     * 自动注入工具类
     */
    @Autowired
    private RandomCode randomCode;

    /**
     * 前端传值后端查出指定的值,然后返回前端
     *
     * @param userId
     * @return
     */
    @RequestMapping("findById")
    public Users findByIdUsers(Integer userId) {
        return this.usersService.selectByUserId(userId);
    }

    /**
     * 修改用户的数据
     *
     * @param users
     * @return
     */

    @PutMapping("editUserFindById")
    public boolean editUser(@RequestBody Users users) {
        return this.usersService.editUsers(users);
    }

    /**
     * 添加用户
     */
    @PutMapping("insertUser")
    public boolean insertUseers(@RequestBody Users users) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());

        return this.usersService.insertUser(users);
    }

    /**
     * 查询出所有值,并将这些全传回前端
     *
     * @return
     */
    @RequestMapping("findUserAll/{page}/{size}")
    public Page<Users> findAllUsers(@PathVariable("page") Integer page,
                                    @PathVariable("size") Integer size, @RequestBody Users user) {
        Integer num = page * size;
        return this.usersService.selectUsersAll(num, size, user);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("deleteUserById")
    public boolean deleteUsers(Integer userId) {
        return this.usersService.deleteUser(userId);
    }

    /**
     * 使用手机号码和密码进行登录
     *
     * @param users
     * @return
     */
    @GetMapping("UsePhoneLogin")
    public Users PhoneLogin(Users users) {
        Users getUser = this.usersService.verifyPhoneLogin(users.getPhone(), users.getPassword());
        if (getUser != null) {
            getUser.setToken(JwtUtil.createToken(getUser));
            return getUser;
        }
        return null;
    }

    /**
     * 前端请求发送邮箱
     */
    @GetMapping("sendMail")
    public String SendMailCode(String mail) {
        return String.valueOf(this.randomCode.verifyCode());
    }


    /**
     * 使用邮箱进行辅助登录
     *
     * @param mail
     * @return
     */
    @GetMapping("UseEmailLogin")
    public Users EmailLogin(String mail) {
        return this.usersService.verifyEmailLogin(mail);
    }

    /**
     * 验证Token,用户是否合法
     *
     * @return
     */
    @GetMapping("verifyToke")
    public Boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }

    /**
     * 传值给前端,但只传id和用户名
     */
    @GetMapping("whereUser")
    public List<Users> whereListUser() {
        return this.usersService.whereListUser();
    }

}
