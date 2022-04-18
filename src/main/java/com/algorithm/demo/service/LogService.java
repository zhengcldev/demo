package com.algorithm.demo.service;

import com.algorithm.demo.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表(Log)表服务接口
 *
 * @author makejava
 * @since 2022-03-25 15:37:10
 */
public interface LogService {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    Log queryFuncLog();

    Map<String, List<Object>> queryVisit();

    /**
     * 分页查询
     *
     * @param log 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Log> queryByPage(Log log, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    Log insert(Log log);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 数量
     */
    int update(Log log);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
