package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ck
 * @date 2019/1/4 11:09
 * Description  :
 */
@Mapper
@Repository
public interface UploadFileMapper extends BaseMapper<UploadFile> {
    /**
     * 根据refId失效附件
     * @param file
     * @return
     */
    Integer updateToDisableByRefId(UploadFile file);
}
