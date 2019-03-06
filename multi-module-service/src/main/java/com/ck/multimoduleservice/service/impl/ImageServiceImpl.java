package com.ck.multimoduleservice.service.impl;

import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimoduledao.entity.Image;
import com.ck.multimoduledao.entity.UploadFile;
import com.ck.multimoduledao.mapper.ImageMapper;
import com.ck.multimoduledao.mapper.UploadFileMapper;
import com.ck.multimoduleservice.service.ImageService;
import com.ck.multimoduleservice.service.UploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ck
 * @date 2019/1/7 14:03
 * Description  :
 */
@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService {
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    UploadFileMapper uploadFileMapper;
    @Override
    public Image saveForm(Image image) {
        // 新增之前的校验
        image = this.checkForm(image);
        if(image.isEnableFlag()){
            super.saveForm(image);
            if(image.getId() != null){
                image.getUploadFile().setRefId(image.getId());
                image.getUploadFile().setRefTable(LmEnum.IMAGE_BLOG.getName());
                uploadFileService.updateForm(image.getUploadFile());
            }else{
                image.setEnableFlag(false);
                image.setReturnMsg("图片信息新增失败");
            }
        }
        return image;
    }

    @Override
    public Image updateForm(Image image) {
        // 更新之前的校验
        image = this.checkForm(image);
        if(image.isEnableFlag()){
            if(image.getId() != null){
                super.updateForm(image);
            }else{
                image.setEnableFlag(false);
                image.setReturnMsg("更新的id为空");
            }
        }
        return image;
    }

    @Override
    public Integer updateToDisable(Image image) {
        int count1 = super.updateToDisable(image);
        if(count1 > 0){
            UploadFile file = new UploadFile();
            file.setRefId(image.getId());
            file.setUpdatedBy(image.getUpdatedBy());
            file.setUpdatedDate(image.getUpdatedDate());
            int count2 = uploadFileMapper.updateToDisableByRefId(file);
        }
        return count1;
    }

    /**
     * 保存或更新之前的校验
     * @param image 图片信息
     * @return
     */
    private Image checkForm(Image image){
        if(image.isEnableFlag()){
            // 上传附件的校验
            if(image.getUploadFile() != null){
                if(image.getUploadFile().getId() == null){
                    image.setEnableFlag(false);
                    image.setReturnMsg("上传附件id为空");
                    return image;
                }
            }else{
                image.setEnableFlag(false);
                image.setReturnMsg("上传附件为空");
                return image;
            }
            // 图片信息的其他校验
            if(StringUtils.isEmpty(image.getTitle()) || StringUtils.isEmpty(image.getCategory()) || StringUtils.isEmpty(image.getContent())
                || StringUtils.isEmpty(image.getOutline())){
                image.setEnableFlag(false);
                image.setReturnMsg("图片信息：标题、概要、类别、正文等不能为空");
                return image;
            }
        }
        return image;
    }
}
