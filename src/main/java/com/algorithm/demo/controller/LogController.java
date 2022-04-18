package com.algorithm.demo.controller;

import com.algorithm.demo.entity.Dataset;
import com.algorithm.demo.entity.Log;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2022/3/25 下午3:40
 */
@RestController
@RequestMapping("/skyline")
public class LogController {
    @Resource
    private LogService logService;

    /**
     * 获取日志
     * @return 返回日志
     */
    @GetMapping("/getHotLog")
    public Resp<Object> getHotLog() {
        Map<String, List<Object>> datasetMap = logService.queryVisit();
        Resp<Object> rsp = new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), datasetMap);
        return rsp;
    }

    /**
     * 近一个月热点各功能使用
     * @return 结果信息
     */
    @GetMapping("/getFuncLog")
    public Resp<Object> getDataset() {
        Log log = logService.queryFuncLog();
        Resp<Object> rsp = new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), log);
        return rsp;
    }

    /**
     * 更新日志
     * @return 更新状态
     */
    @PostMapping("/updateLog")
    public Resp<Object> updateLog(@RequestBody Log log){
        int returnLog=logService.update(log);
        Resp<Object> rsp = new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), returnLog);
        return rsp;
    }

    @PostMapping("/insertLog")
    public Resp<Object> insertLog(@RequestBody Map<String,String> logMap){
        Log log = new Log();
        log.setUserId(logMap.get("userId"));
        log.setLoginTime(logMap.get("loginTime"));
        Log returnLog =logService.insert(log);
        Resp<Object> rsp = new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), returnLog);
        return rsp;
    }

}
