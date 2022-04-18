package com.algorithm.demo.service.impl;

import com.algorithm.demo.entity.Log;
import com.algorithm.demo.dao.LogDao;
import com.algorithm.demo.entity.Visit;
import com.algorithm.demo.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表(Log)表服务实现类
 *
 * @author makejava
 * @since 2022-03-25 15:37:10
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;


    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    @Override
    public Log queryFuncLog() {
        return this.logDao.queryFuncLog();
    }

    @Override
    public Map<String, List<Object>> queryVisit() {
        List<Visit> result=this.logDao.queryVisit();
        List<Object> dateList=new ArrayList<>();
        List<Object> timesList=new ArrayList<>();
        for(Visit visit:result){
            dateList.add(visit.getDate());
            timesList.add(visit.getTimes());
        }
        Map<String, List<Object>> resultMap=new HashMap<>();
        resultMap.put("date",dateList);
        resultMap.put("times",timesList);
        return resultMap;
    }

    /**
     * 分页查询
     *
     * @param log 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Log> queryByPage(Log log, PageRequest pageRequest) {
        long total = this.logDao.count(log);
        return new PageImpl<>(this.logDao.queryAllByLimit(log, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log insert(Log log) {
        this.logDao.insert(log);
        return log;
    }

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Log log) {
        return this.logDao.update(log);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logDao.deleteById(id) > 0;
    }
}
