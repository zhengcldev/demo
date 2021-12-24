package com.algorithm.demo.service;

import com.algorithm.demo.entity.UserEntity;
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
    UserEntity queryById(Integer id);

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
     * @param userEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UserEntity> queryByPage(UserEntity userEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    UserEntity insert(UserEntity userEntity);

    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    UserEntity update(UserEntity userEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
