package com.ck.multimoduleservice.service.impl;

import com.ck.multimoduledao.entity.IndexQuery;
import com.ck.multimoduledao.mapper.IndexMapper;
import com.ck.multimoduleservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ck
 * @date 2019/1/25 9:56
 * Description  :
 */
@Service
public class IndexServiceImpl extends BaseServiceImpl<IndexQuery> implements IndexService {
    @Autowired
    private IndexMapper indexMapper;
}
