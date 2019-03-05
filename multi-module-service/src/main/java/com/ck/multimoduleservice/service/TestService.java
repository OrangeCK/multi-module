package com.ck.multimoduleservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ck.multimoduledao.entity.FndUser;
import com.ck.multimoduledao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService extends ServiceImpl<TestMapper, FndUser> {

    @Autowired
    private TestMapper testMapper;

    public FndUser testEntity(){
        QueryWrapper<FndUser> query = new QueryWrapper<>();
        List<FndUser> listUser = this.list(query);
        return listUser.get(0);
    }
}
