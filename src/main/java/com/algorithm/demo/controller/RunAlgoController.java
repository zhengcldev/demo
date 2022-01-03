package com.algorithm.demo.controller;

import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.RunAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/25 上午11:27
 */
@RestController
@RequestMapping("/skyline")
@Log4j2
public class RunAlgoController {
    @Resource
    private RunAlgorithm runAlgorithm;
    @PostMapping(value = "/runAlgorithm")
    public Resp<Object> runAlgorithm(@RequestBody Map<String, String[]> dataMap) {
        return runAlgorithm.runAlgo(dataMap);
    }

}
