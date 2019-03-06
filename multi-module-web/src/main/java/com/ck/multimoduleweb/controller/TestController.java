package com.ck.multimoduleweb.controller;

import com.ck.multimodulecommon.exception.SysException;
import com.ck.multimoduledao.entity.FndUser;
import com.ck.multimoduleservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/getEntity")
    @ResponseBody
    public FndUser testEntity(){
        FndUser user = null;
        if(user == null){
            throw new SysException("系统出现了空指针");
        }
        System.out.println("现在系统出现的空指针");
        int age = user.getAge();
        return testService.testEntity();
    }
}
