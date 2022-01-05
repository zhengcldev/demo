package com.algorithm.demo.service;

import com.algorithm.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 算法演示系统用户表(User)表服务接口
 *
 * @author makejava
 * @since 2021-12-22 13:30:11
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 根据用户id和密码查询，用于登陆
     *
     * @param  userId 用户id
     * @param  password 用户密码
     * @return 返回bool值
     */
    Boolean queryUser(String userId, String password);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     *
     * @param userId 用户id
     * @param password 原始密码
     * @param newPassword 新密码
     * @return 布尔值
     */
    boolean updatePassword(String userId, String password, String newPassword);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
