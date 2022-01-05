package com.algorithm.demo.controller;

import com.algorithm.demo.entity.Dataset;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.DatasetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据集表，数据集信息描述(Dataset)表控制层
 *
 * @author makejava
 * @since 2021-12-25 21:53:52
 */
@RestController
@RequestMapping("/skyline")
public class DatasetController {
    /**
     * 服务对象
     */
    @Resource
    private DatasetService datasetService;

    /**
     *获取全部数据集信息
     */
    @GetMapping("/getDataset")
    public Resp<Object> getDataset() {
        List<Dataset> datasetList = datasetService.queryDataset();
        Resp<Object> rsp = new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), datasetList);
        return rsp;
    }
}

