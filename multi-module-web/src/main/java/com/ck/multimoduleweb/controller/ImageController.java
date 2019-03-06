package com.ck.multimoduleweb.controller;

import com.alibaba.fastjson.JSON;
import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimodulecommon.exception.SysException;
import com.ck.multimodulecommon.util.RedisUtil;
import com.ck.multimoduledao.entity.Employee;
import com.ck.multimoduledao.entity.Image;
import com.ck.multimoduledao.entity.PageList;
import com.ck.multimoduledao.entity.ResultData;
import com.ck.multimoduleservice.service.ImageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ck
 * @date 2019/1/7 14:02
 * Description  :
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private final static Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    ImageService imageService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页查询
     * @param image 图片信息
     * @return
     */
    @RequiresPermissions("imagePageList")
    @RequestMapping(value = "/imagePageList", method = RequestMethod.POST)
    public PageList<Image> imagePageList(@RequestBody Image image){
        return imageService.getPageList(image);
    }

    /**
     * 查看详情
     * @param id 博客id
     * @return
     */
    @RequiresPermissions("imageDetail")
    @RequestMapping(value = "/imageDetail", method = RequestMethod.POST)
    public ResultData imageDetail(@RequestParam Long id){
        return ResultData.ok(imageService.findDetailById(id));
    }

    /**
     * 失效图片博客
     * @param image 博客
     * @return
     */
    @RequiresPermissions("disableImage")
    @RequestMapping(value = "/disableImage", method = RequestMethod.POST)
    public ResultData disableImage(@RequestBody Image image, HttpServletRequest request){
        this.getCurrentUser(image, request);
        int count = imageService.updateToDisable(image);
        if(count > 0){
            return new ResultData();
        }else{
            return ResultData.error("操作失败");
        }
    }
    /**
     * 新增图片信息
     * @param image 图片信息
     * @param request
     * @return
     */
    @RequiresPermissions("updateImage")
    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public ResultData updateImage(@RequestBody Image image, HttpServletRequest request){
        try {
            this.getCurrentUser(image, request);
            image = imageService.updateForm(image);
            if(image.isEnableFlag()){
                return new ResultData(image);
            }else{
                return ResultData.error(image.getReturnMsg());
            }
        } catch (SysException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error(e.getMsg() + sw.toString());
            return ResultData.error(e.getMsg());
        }
    }

    /**
     * 新增图片信息
     * @param image 图片信息
     * @param request
     * @return
     */
    @RequiresPermissions("addImage")
    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    public ResultData addImage(@RequestBody Image image, HttpServletRequest request){
        try {
            this.getCurrentUser(image, request);
            image = imageService.saveForm(image);
            if(image.isEnableFlag()){
                return new ResultData(image);
            }else{
                return ResultData.error(image.getReturnMsg());
            }
        } catch (SysException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            logger.error(e.getMsg() + sw.toString());
            return ResultData.error(e.getMsg());
        }
    }

    /**
     * 从redis获取当前登录人的信息
     * @param request
     * @return
     */
    private void getCurrentUser(Image image, HttpServletRequest request){
        String token = request.getHeader(LmEnum.AUTHORIZATION.getName());
        Employee emp = JSON.parseObject(redisUtil.hget(token, LmEnum.USER_INFO.getName()).toString(), Employee.class);
        image.setCreationBy(emp.getId());
        image.setUpdatedBy(emp.getId());
    }

}
