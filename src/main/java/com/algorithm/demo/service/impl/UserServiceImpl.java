package com.algorithm.demo.service.impl;

import com.algorithm.demo.entity.UserEntity;
import com.algorithm.demo.dao.UserDao;
import com.algorithm.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

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
    public UserEntity queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    @Override
    public Boolean queryUser(String userId, String password) {
        int res=userDao.queryUserByUidAndPwd(userId,password);
        return res != 0;
    }

    /**
     * 分页查询
     *
     * @param userEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<UserEntity> queryByPage(UserEntity userEntity, PageRequest pageRequest) {
        long total = this.userDao.count(userEntity);
        return new PageImpl<>(this.userDao.queryAllByLimit(userEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public UserEntity insert(UserEntity userEntity) {
        this.userDao.insert(userEntity);
        return userEntity;
    }

    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public UserEntity update(UserEntity userEntity) {
        this.userDao.update(userEntity);
        return this.queryById(userEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }
}
