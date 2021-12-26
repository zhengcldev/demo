package com.algorithm.demo.service.impl;

import com.algorithm.demo.dao.AlgorithmDao;
import com.algorithm.demo.entity.Algorithm;
import com.algorithm.demo.service.AlgorithmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮廓模式挖掘算法表(Algorithm)表服务实现类
 *
 * @author makejava
 * @since 2021-12-25 21:51:19
 */
@Service("algorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {
    @Resource
    private AlgorithmDao algorithmDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Algorithm queryById(Integer id) {
        return this.algorithmDao.queryById(id);
    }

    @Override
    public List<Algorithm> queryAlgorithm(Integer isSource) {
        return algorithmDao.queryAlgoBySource(isSource);
    }

    /**
     * 分页查询
     *
     * @param algorithm   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Algorithm> queryByPage(Algorithm algorithm, PageRequest pageRequest) {
        long total = this.algorithmDao.count(algorithm);
        return new PageImpl<>(this.algorithmDao.queryAllByLimit(algorithm, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param algorithm 实例对象
     * @return 实例对象
     */
    @Override
    public Algorithm insert(Algorithm algorithm) {
        this.algorithmDao.insert(algorithm);
        return algorithm;
    }

    /**
     * 修改数据
     *
     * @param algorithm 实例对象
     * @return 实例对象
     */
    @Override
    public Algorithm update(Algorithm algorithm) {
        this.algorithmDao.update(algorithm);
        return this.queryById(algorithm.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.algorithmDao.deleteById(id) > 0;
    }
}
