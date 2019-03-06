package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.IndexQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ck
 * @date 2019/1/25 9:57
 * Description  :
 */
@Mapper
@Repository
public interface IndexMapper extends BaseMapper<IndexQuery> {
}
