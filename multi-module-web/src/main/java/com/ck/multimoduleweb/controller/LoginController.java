package com.ck.multimoduleweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimodulecommon.util.JwtUtil;
import com.ck.multimoduledao.entity.Employee;
import com.ck.multimoduledao.entity.ResultData;
import com.ck.multimoduleservice.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ck
 * @date 2018/11/8 9:37
 * Description  :
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     * @param jsonStr 用户信息的json字符串
     * @return
     */
    @RequestMapping(value = "/goLogin", method = RequestMethod.POST)
    public ResultData login(@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        // 登录名
        String loginName = jsonObject.getString("loginName");
        // 登录密码
        String password = jsonObject.getString("password");
        Employee employee = employeeService.loginAccountByLoginName(loginName);
        if(employee == null){
            return ResultData.error("用户名错误");
        }else if(StringUtils.isEmpty(employee.getPassword()) || !employee.getPassword().equals(password)){
            return ResultData.error("密码错误");
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("Authorization",JwtUtil.sign(loginName));
            map.put("Refresh_Token",JwtUtil.refreshSign(loginName, employee.getPassword()));
            map.put("loginName",loginName);
            map.put("id",employee.getId());
            return new ResultData(map);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/quitLogin", method = RequestMethod.POST)
    public ResultData quitLogin(){
        return new ResultData();
    }

    @RequestMapping(value = "/unAuthorization", method = RequestMethod.POST)
    public ResultData unAuthorization(){
        return ResultData.error("登录身份验证错误");
    }
}
