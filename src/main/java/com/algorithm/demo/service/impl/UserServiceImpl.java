package com.algorithm.demo.service.impl;

import com.algorithm.demo.dao.UserDao;
import com.algorithm.demo.entity.User;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.Math.random;

/**
 * 算法演示系统用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2021-12-22 13:30:11
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public List<User> queryById(String id) {
        return this.userDao.queryById(id);
    }

    @Override
    public Resp<Object> queryUser(String userId, String password) {
        List<User> userList = userDao.queryById(userId);
        if (userList.size()==0) {
            //查无此人
            return new Resp<>(StatusEnum.USER_NOT_EXIST.getStatusCode(),
                    StatusEnum.USER_NOT_EXIST.getStatusMsg());
        } else {
            int res = userDao.queryUserByUidAndPwd(userId, password);
            if (res == 0) {
                //密码错误
                return new Resp<>(StatusEnum.USER_ID_PWD_ERROR.getStatusCode(),
                        StatusEnum.USER_ID_PWD_ERROR.getStatusMsg());
            } else {
                //登录成功
                return new Resp<>(StatusEnum.LOGIN_SUCCESS.getStatusCode(),
                        StatusEnum.LOGIN_SUCCESS.getStatusMsg(), userList.get(0));
            }
        }
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    @Override
    public Boolean userExistVerify(User user) {
        return this.userDao.queryById(user.getUserId()).size() != 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(String.valueOf(user.getId())).get(0);
    }

    @Override
    public int updateState(User user) {
        return this.userDao.updateState(user);
    }

    @Override
    public int deleteUser(User user) {
        return this.userDao.deleteByUserId(user.getUserId());
    }

    @Override
    public String resetPassword(User user) {
        //生成随机密码
        String pwd = generatePwd();
        user.setPassword(pwd);
        if (this.userDao.resetPassword(user) != 0) {
            return pwd;
        } else {
            return null;
        }

    }

    private String generatePwd() {
        String[] letter = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            double r = Math.random();
            if (r > 0.4) {
                sb.append(letter[(Double.valueOf(Math.random() * letter.length).intValue())]);
            } else {
                sb.append(num[(Double.valueOf(Math.random() * num.length).intValue())]);
            }
        }
        return sb.toString();
    }

    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        int result = userDao.updatePwdByUidAndPwd(userId, password, newPassword);
        return result != 0;
    }

}
