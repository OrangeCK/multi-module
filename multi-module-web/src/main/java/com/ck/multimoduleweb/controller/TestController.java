package com.ck.multimoduleweb.controller;

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
        return testService.testEntity();
    }
}
