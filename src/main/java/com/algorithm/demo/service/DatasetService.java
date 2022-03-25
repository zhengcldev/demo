package com.algorithm.demo.service;

import com.algorithm.demo.entity.Dataset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 数据集表，数据集信息描述(Dataset)表服务接口
 *
 * @author makejava
 * @since 2021-12-25 21:53:52
 */
public interface DatasetService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dataset queryById(Integer id);

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    List<Dataset> queryDataset();

    int updateDataset(Dataset dataset);

    /**
     * 分页查询
     *
     * @param dataset     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Dataset> queryByPage(Dataset dataset, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dataset 实例对象
     * @return 实例对象
     */
    Dataset insert(Dataset dataset);

    /**
     * 修改数据
     *
     * @param dataset 实例对象
     * @return 实例对象
     */
    Dataset update(Dataset dataset);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
