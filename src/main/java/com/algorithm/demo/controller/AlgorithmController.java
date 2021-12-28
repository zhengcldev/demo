package com.algorithm.demo.controller;

import com.algorithm.demo.entity.Algorithm;
import com.algorithm.demo.entity.Dataset;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.AlgorithmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮廓模式挖掘算法表(Algorithm)表控制层
 *
 * @author makejava
 * @since 2021-12-25 21:51:19
 */
@RestController
@RequestMapping("/skyline")
public class AlgorithmController {
    /**
     * 服务对象
     */
    @Resource
    private AlgorithmService algorithmService;

    /**
     *获取全部数据集信息
     */
    @GetMapping("/getAlgorithm")
    public Resp<Object> getDataset(@RequestParam(name = "isSource",required = false,defaultValue = "0")Integer isSource) {
        List<Algorithm> algorithmList = algorithmService.queryAlgorithm(isSource);
        Resp<Object> rsp = new Resp<>(StatusEnum.LOGIN_SUCCESS.getStatusCode(), StatusEnum.LOGIN_SUCCESS.getStatusMsg(), algorithmList);
        return rsp;
    }

}

