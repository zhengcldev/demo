package com.algorithm.demo.controller;

import com.algorithm.demo.algorithm.SFUI_UF.SFUI_UF;
import com.algorithm.demo.algorithm.SFU_Miner.AlgoSFUPMinerUemax;
import com.algorithm.demo.entity.AlgoResult;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.algorithm.demo.configuration.Constant.Algo_SFUI_UF;
import static com.algorithm.demo.configuration.Constant.Algo_SFU_Miner;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/25 上午11:27
 */
@RestController
@RequestMapping("/skyline")
public class RunAlgoController {

    @PostMapping(value = "/runAlgorithm")
    public List<AlgoResult> runAlgorithm(@RequestBody Map<String, String[]> dataMap) throws IOException {


        //store the algorithm results
        List<AlgoResult> algoResultList = new ArrayList<>();
        //input file
        for(String data:dataMap.get("data")) {
            File file = ResourceUtils.getFile("classpath:dataset/" + data);

            String[] algo = ArrayUtils.addAll(dataMap.get("source"), dataMap.get("target"));
            for (String algoName : algo) {
                if (algoName.equals(Algo_SFUI_UF)) {
                    //SFUI_UF
                    SFUI_UF uf = new SFUI_UF();
                    uf.runSFUI_UF(file);
                    AlgoResult algoSFUI_UFResult = uf.getResult();
                    algoSFUI_UFResult.setDataName(data);
                    algoResultList.add(algoSFUI_UFResult);
                } else if (algoName.equals(Algo_SFU_Miner)) {

                    //SFU_Miner
                    AlgoSFUPMinerUemax sfupMine = new AlgoSFUPMinerUemax();
                    sfupMine.runAlgorithm(file);
                    AlgoResult algoSFU_MinerResult = sfupMine.getResult();
                    algoSFU_MinerResult.setDataName(data);
                    algoResultList.add(algoSFU_MinerResult);
                }
            }
        }

        return algoResultList;
    }

}
