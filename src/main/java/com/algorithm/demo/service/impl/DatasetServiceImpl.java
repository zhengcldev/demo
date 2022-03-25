package com.algorithm.demo.service.impl;

import com.algorithm.demo.entity.Dataset;
import com.algorithm.demo.dao.DatasetDao;
import com.algorithm.demo.service.DatasetService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据集表，数据集信息描述(Dataset)表服务实现类
 *
 * @author makejava
 * @since 2021-12-25 21:53:52
 */
@Service("datasetService")
public class DatasetServiceImpl implements DatasetService {
    @Resource
    private DatasetDao datasetDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dataset queryById(Integer id) {
        return this.datasetDao.queryById(id);
    }

    @Override
    public List<Dataset> queryDataset() {
        return datasetDao.queryAll();
    }

    @Override
    public int updateDataset(Dataset dataset) {
        return this.datasetDao.updateDataset(dataset);
    }

    /**
     * 分页查询
     *
     * @param dataset     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Dataset> queryByPage(Dataset dataset, PageRequest pageRequest) {
        long total = this.datasetDao.count(dataset);
        return new PageImpl<>(this.datasetDao.queryAllByLimit(dataset, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dataset 实例对象
     * @return 实例对象
     */
    @Override
    public Dataset insert(Dataset dataset) {
        this.datasetDao.insert(dataset);
        return dataset;
    }

    /**
     * 修改数据
     *
     * @param dataset 实例对象
     * @return 实例对象
     */
    @Override
    public Dataset update(Dataset dataset) {
        this.datasetDao.update(dataset);
        return this.queryById(dataset.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.datasetDao.deleteById(id) > 0;
    }
}
