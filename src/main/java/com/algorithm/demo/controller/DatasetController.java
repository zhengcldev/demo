package com.algorithm.demo.controller;

import com.algorithm.demo.entity.Dataset;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.DatasetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.algorithm.demo.configuration.Constant.baseUrl;

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
    private String datasetName;

    /**
     * 用户头像上传
     */
    @PostMapping("/datasetUpload")
    public String datasetUpload(@RequestParam("dataset") MultipartFile dataset) {

        //获取文件在服务器的储存位置/Users/zhengchuanlong/demonstration/src/main/resources/static
        String path = "/Users/zhengchuanlong/demonstration/src/main/resources/dataset";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            boolean isCreate = filePath.mkdir();
            if (!isCreate) {
                System.out.println("error");
                return null;
            }
        }

        //获取原始文件名称(包含格式)
        String originalFileName = dataset.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        assert originalFileName != null;
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String date = sdf.format(d);
        String fileName = name + "." + type;
        datasetName = fileName;
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            dataset.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            System.out.println("添加成功");
            return baseUrl + "/" + fileName;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加数据集
     *
     * @param dataset 数据集信息
     * @return 添加成功的数据集
     */
    @PostMapping("/insertDataset")
    public Resp<Object> addDataset(@RequestBody Dataset dataset) {
        dataset.setDataUrl(baseUrl + "/" + datasetName);
        Dataset data = datasetService.insert(dataset);
        return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), data);
    }

    /**
     * 获取全部数据集信息
     */
    @PostMapping("/getDataset")
    public Resp<Object> getDataset(@RequestBody Map<String, Object> model) {
        int pageNUm, pageSize;
        if (model.get("currentPage") == null) {
            pageNUm = 1;
        } else {
            pageNUm = (int) model.get("currentPage");
        }
        if (model.get("pageSize") == null) {
            pageSize = Integer.MAX_VALUE;
        } else {
            pageSize = (int) model.get("pageSize");
        }
        List<Dataset> datasetList = datasetService.queryDataset(pageNUm, pageSize);
        PageInfo<Dataset> pageInfo = new PageInfo<>(datasetList);
        return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(),
                StatusEnum.OPERATION_SUCCESS.getStatusMsg(), pageInfo);
    }

    @PostMapping("/updateDataset")
    public Resp<Object> updateDataset(@RequestBody Dataset data) {
        int result = datasetService.updateDataset(data);
        if (result != 0) {
            return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), data);
        } else {
            return new Resp<>(StatusEnum.OPERATION_FAIL.getStatusCode(), StatusEnum.OPERATION_FAIL.getStatusMsg(), null);
        }
    }

    @DeleteMapping("/deleteDataset")
    public Resp<Object> deleteDataset(@RequestBody Dataset data) {
        boolean result = datasetService.deleteById(data.getId());
        if (result) {
            return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), true);
        } else {
            return new Resp<>(StatusEnum.OPERATION_FAIL.getStatusCode(), StatusEnum.OPERATION_FAIL.getStatusMsg(), false);
        }
    }

    @RequestMapping
    @GetMapping("/test")
    public void download(HttpServletResponse response) {
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try {
            String fileName = "test.txt";
            // resources下路径，比如文件位置在：resources/file/test.docx
            String path = "dataset/" + fileName;
            //设置响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            ClassPathResource classPathResource = new ClassPathResource(path);
            fis = new FileInputStream(classPathResource.getFile());
            sos = response.getOutputStream();
            IOUtils.copy(fis, sos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("下载失败！");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (sos != null) {
                    sos.flush();
                    sos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

