package com.ck.multimoduleweb.controller;

import com.alibaba.fastjson.JSON;
import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimodulecommon.exception.SysException;
import com.ck.multimodulecommon.util.AliyunOssClientUtil;
import com.ck.multimodulecommon.util.RedisUtil;
import com.ck.multimodulecommon.util.UploadUtil;
import com.ck.multimoduledao.entity.Employee;
import com.ck.multimoduledao.entity.ResultData;
import com.ck.multimoduledao.entity.UploadFile;
import com.ck.multimoduleservice.service.UploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * @author ck
 * @date 2019/4/12 11:41
 * Description  :
 */
@Controller
@RequestMapping("/aliOss")
public class AliOssController {
    private final static Logger logger = LoggerFactory.getLogger(AliOssController.class);
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 图片上传接口
     * @param multipartFile
     * @return
     */
    @RequiresPermissions("uploadOss")
    @RequestMapping(value = "/uploadOss", method = RequestMethod.POST)
    @ResponseBody
    public ResultData uploadOss(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request){
        if(multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())){
            return ResultData.error("上传文件NULL");
        }
        String contentType = multipartFile.getContentType();
        if(!contentType.contains("")){
            return ResultData.error("上传文件格式错误");
        }
        String rootFileName = multipartFile.getOriginalFilename();
        logger.info("上传图片：name={},type={}",rootFileName, contentType);
        try {
            // 获取时间戳
            long currTimestamp = System.currentTimeMillis();
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + currTimestamp;
            AliyunOssClientUtil.invokeOssUpload(multipartFile);
            UploadFile uploadFile = new UploadFile();
            this.getCurrentUser(uploadFile, request);
            uploadFile.setFileName(rootFileName);
            uploadFile.setFilePath("http://" + LmEnum.BACKET_NAME.getName() + "." + LmEnum.ENDPOINT.getName() + "/" + LmEnum.FOLDER.getName() + rootFileName);
            uploadFile.setFileType(multipartFile.getContentType());
            uploadFile.setFileSize(String.valueOf(multipartFile.getSize()));
            uploadFile = uploadFileService.saveForm(uploadFile);
            if(uploadFile.isEnableFlag()){
                return new ResultData(uploadFile);
            }else{
                return ResultData.error("上传失败");
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error("文件上传失败" + sw.toString());
            return ResultData.error("上传失败");
        }
    }

    /**
     * 从redis获取当前登录人的信息
     * @param request
     * @return
     */
    private void getCurrentUser(UploadFile uploadFile, HttpServletRequest request){
        String token = request.getHeader(LmEnum.AUTHORIZATION.getName());
        Employee emp = JSON.parseObject(redisUtil.hget(token, LmEnum.USER_INFO.getName()).toString(), Employee.class);
        uploadFile.setCreationBy(emp.getId());
    }
}
