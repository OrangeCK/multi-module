package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ck
 * @date 2019/1/25 16:02
 * Description  :
 */
@Mapper
@Repository
public interface BlogMapper extends BaseMapper<BlogQuery> {
}
