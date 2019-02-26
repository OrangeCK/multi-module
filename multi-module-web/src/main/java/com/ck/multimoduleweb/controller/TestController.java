package com.ck.multimoduleweb.controller;

import com.ck.multimoduleservice.TestUtil.TestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/date")
    public String testDate(){
        Date d = TestUtil.getCurDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "当前时间：" + df.format(d);
    }
}
