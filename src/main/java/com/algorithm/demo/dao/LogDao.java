package com.algorithm.demo.dao;

import com.algorithm.demo.entity.Log;
import com.algorithm.demo.entity.Visit;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 系统日志表(Log)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-25 16:02:28
 */
public interface LogDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    Log queryFuncLog();

    List<Visit> queryVisit();
    /**
     * 查询指定行数据
     *
     * @param log 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Log> queryAllByLimit(Log log, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param log 查询条件
     * @return 总行数
     */
    long count(Log log);

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int insert(Log log);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Log> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Log> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Log> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Log> entities);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int update(Log log);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

