package com.algorithm.demo.controller;

import com.algorithm.demo.algorithm.SFUI_UF.SFUI_UF;
import com.algorithm.demo.algorithm.SFU_Miner.AlgoSFUPMinerUemax;
import com.algorithm.demo.entity.AlgoResult;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/25 上午11:27
 */
@RestController
@RequestMapping("/skyline")
public class RunAlgoController {
    @GetMapping(value = "/runAlgorithm")
    public List<AlgoResult> runAlgorithm() throws IOException {


        //store the algorithm results
        List<AlgoResult> algoResultList=new ArrayList<>();
        //input file
        String input = "input.txt";
        File file=ResourceUtils.getFile("classpath:dataset/" + input);


        //SFUI_UF
        SFUI_UF uf = new SFUI_UF();
        uf.runSFUI_UF(file);
        AlgoResult algoSFUI_UFResult = uf.getResult();
        algoSFUI_UFResult.setDataName(input);
        algoResultList.add(algoSFUI_UFResult);

        //SFU_Miner
        AlgoSFUPMinerUemax sfupMine=new AlgoSFUPMinerUemax();
        sfupMine.runAlgorithm(file);
        AlgoResult algoSFU_MinerResult = sfupMine.getResult();
        algoSFU_MinerResult.setDataName(input);
        algoResultList.add(algoSFU_MinerResult);

        return algoResultList;
    }

}
