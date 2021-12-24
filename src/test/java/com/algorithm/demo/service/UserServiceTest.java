package com.algorithm.demo.service;

import com.algorithm.demo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/22 下午2:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserDao userDao;
    @Test
    void queryUser() {
        int num=userDao.queryUserByUidAndPwd("abc123","abc123");
        assertEquals(num,0);
    }
}