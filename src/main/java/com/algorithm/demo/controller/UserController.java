package com.algorithm.demo.controller;

import com.algorithm.demo.entity.User;
import com.algorithm.demo.entity.VerifyCode;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.UserService;
import com.algorithm.demo.service.VerifyCodeService;
import com.algorithm.demo.service.impl.SimpleCharVerifyCodeGenImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.algorithm.demo.configuration.Constant.baseUrl;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/22 下午1:36
 */
@RestController
@RequestMapping("/skyline")
@Log4j2
public class UserController {

    @Resource
    UserService userService;
    //验证码
    String code = "";

    @PostMapping(value = "/login")
    public Resp<Object> login(@RequestBody Map<String, String> loginInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isLogin", false);
        if (code.equals("") || !code.equalsIgnoreCase(loginInfo.get("code"))) {
            resultMap.put("Msg", "请输入正确的验证码");
            return new Resp<>(StatusEnum.LOGIN_ERROR.getStatusCode(), StatusEnum.LOGIN_ERROR.getStatusMsg(), resultMap);
        }
        Boolean isLogin = userService.queryUser(loginInfo.get("userId"), loginInfo.get("password"));
        resultMap.put("isLogin", isLogin);
        if (isLogin) {
            User user = userService.queryById(loginInfo.get("userId")).get(0);
            resultMap.put("isSupper", user.getIsSupper());
            if (user.getState() == 1) {
                return new Resp<>(StatusEnum.LOGIN_SUCCESS.getStatusCode(), StatusEnum.LOGIN_SUCCESS.getStatusMsg(), resultMap);
            } else {
                return new Resp<>(StatusEnum.USER_LOCKED.getStatusCode(),StatusEnum.USER_LOCKED.getStatusMsg(),null);
            }
        } else {
            resultMap.put("Msg", "账号或密码不正确");
            return new Resp<>(StatusEnum.LOGIN_ERROR.getStatusCode(), StatusEnum.LOGIN_ERROR.getStatusMsg(), resultMap);
        }

    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    /**
     * 修改用户密码
     */
    @PostMapping(value = "/updatePassword")
    public Resp<Object> updatePassword(@RequestBody Map<String, String> userInfo) {
        boolean isSuccess = userService.updatePassword(userInfo.get("userId"), userInfo.get("password"), userInfo.get("newPassword"));
        return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), isSuccess);
    }

    /**
     * 修改用户密码
     */
    @PostMapping(value = "/createUser")
    public Resp<Object> createUser(@RequestBody User userInfo) {
        if (userService.userExistVerify(userInfo)) {
            return new Resp<>(StatusEnum.USER_EXIST.getStatusCode(), StatusEnum.USER_EXIST.getStatusMsg(), null);
        }
        User user = userService.insert(userInfo);
        boolean isSuccess = user.equals(userInfo);
        return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), isSuccess);
    }

    @GetMapping(value = "/userInfo")
    public Resp<Object> getUserInfo(@RequestParam(name = "userId", required = false) String userId) {
        List<User> user = userService.queryById(userId);
        if (user.size() == 1) {
            return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), user.get(0));
        }
        return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), user);
    }

    /**
     * 锁定与解锁用户
     * @param user 用户信息
     * @return 状态
     */
    @PostMapping(value = "/lock")
    public Resp<Object> updateState(@RequestBody User user) {
        int result = userService.updateState(user);
        if (result != 0) {
            return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), user);
        } else {
            return new Resp<>(StatusEnum.OPERATION_FAIL.getStatusCode(), StatusEnum.OPERATION_FAIL.getStatusMsg(), null);
        }
    }

    /**
     *重置密码
     */
    @PostMapping(value="/resetPassword")
    public Resp<Object> resetPassword(@RequestBody User user){
        String result = userService.resetPassword(user);
        if (result != null) {
            return new Resp<>(StatusEnum.OPERATION_SUCCESS.getStatusCode(), StatusEnum.OPERATION_SUCCESS.getStatusMsg(), result);
        } else {
            return new Resp<>(StatusEnum.OPERATION_FAIL.getStatusCode(), StatusEnum.OPERATION_FAIL.getStatusMsg(), null);
        }
    }


    /**
     * 生成验证
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        VerifyCodeService iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.info("", e);
        }
    }

    /**
     * 用户头像上传
     */
    @PostMapping("/pfpUpload")
    public String pfpUpload(@RequestParam("picFile") MultipartFile picture) {

        //获取文件在服务器的储存位置/Users/zhengchuanlong/demonstration/src/main/resources/static
        String path = "/Users/zhengchuanlong/demonstration/src/main/resources/static";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            boolean isCreate = filePath.mkdir();
            if (!isCreate) {
                System.out.println("error");
                return null;
            }
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        assert originalFileName != null;
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = name + date + "." + type;

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            System.out.println("添加成功");
            return baseUrl + "/" + fileName;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return null;
        }
    }
}
