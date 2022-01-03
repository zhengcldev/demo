package com.algorithm.demo.service;

import com.algorithm.demo.entity.Algorithm;
import com.algorithm.demo.entity.Dataset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 轮廓模式挖掘算法表(Algorithm)表服务接口
 *
 * @author makejava
 * @since 2021-12-25 21:51:19
 */
public interface AlgorithmService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Algorithm queryById(Integer id);
    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    List<Algorithm> queryAlgorithm(Integer isSource,String algoName);

    /**
     * 分页查询
     *
     * @param algorithm 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Algorithm> queryByPage(Algorithm algorithm, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param algorithm 实例对象
     * @return 实例对象
     */
    Algorithm insert(Algorithm algorithm);

    /**
     * 修改数据
     *
     * @param algorithm 实例对象
     * @return 实例对象
     */
    Algorithm update(Algorithm algorithm);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
