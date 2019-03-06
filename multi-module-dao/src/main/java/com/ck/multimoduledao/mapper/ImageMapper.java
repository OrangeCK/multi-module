package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ck
 * @date 2019/1/7 14:03
 * Description  :
 */
@Mapper
@Repository
public interface ImageMapper extends BaseMapper<Image> {
}
