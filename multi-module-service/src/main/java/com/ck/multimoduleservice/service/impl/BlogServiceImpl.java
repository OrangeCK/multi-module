package com.ck.multimoduleservice.service.impl;

import com.ck.multimoduledao.entity.BlogQuery;
import com.ck.multimoduledao.mapper.BlogMapper;
import com.ck.multimoduleservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ck
 * @date 2019/1/25 16:01
 * Description  :
 */
@Service
public class BlogServiceImpl extends BaseServiceImpl<BlogQuery> implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
}
