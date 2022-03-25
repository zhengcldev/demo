package com.algorithm.demo.dao;

import com.algorithm.demo.entity.Dataset;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 数据集表，数据集信息描述(Dataset)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-25 21:53:52
 */
public interface DatasetDao {

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    List<Dataset> queryAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dataset queryById(Integer id);

    int updateDataset(Dataset dataset);
    /**
     * 查询指定行数据
     *
     * @param dataset  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dataset> queryAllByLimit(Dataset dataset, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dataset 查询条件
     * @return 总行数
     */
    long count(Dataset dataset);

    /**
     * 新增数据
     *
     * @param dataset 实例对象
     * @return 影响行数
     */
    int insert(Dataset dataset);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dataset> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dataset> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dataset> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Dataset> entities);

    /**
     * 修改数据
     *
     * @param dataset 实例对象
     * @return 影响行数
     */
    int update(Dataset dataset);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

