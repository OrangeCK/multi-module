package com.ck.multimoduleweb.exception;

import com.ck.multimodulecommon.exception.SysException;
import com.ck.multimoduledao.entity.ResultData;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ck
 * @date 2018/11/2 17:03
 * Description  : 系统异常处理器
 */
@ControllerAdvice
public class SysExceptionHandler {
    /**
     * 处理自定义异常
     */
//    @ExceptionHandler(SysException.class)
//    @ResponseBody
//    public ResultData handleSysException(SysException e){
//        return ResultData.error().setMsg(e.getMsg()).setSuccess(false).setCode(e.getCode());
//    }
    /**
     * 不满足@RequiresGuest注解时抛出的异常信息
     */
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResultData handleShiroException(ShiroException e, HttpServletResponse response) {
        String eName = e.getClass().getSimpleName();
        response.setStatus(401);
        return ResultData.error("鉴权或授权过程出错").setCode(401);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public ResultData page401(UnauthenticatedException e, HttpServletResponse response) {
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
            response.setStatus(403);
            return ResultData.error("只允许游客访问，若您已登录，请先退出登录").setCode(403);
        }else{
            response.setStatus(401);
            return ResultData.error("用户未登录").setCode(401);
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResultData page403(HttpServletResponse response) {
        response.setStatus(403);
        return ResultData.error("用户没有访问权限").setCode(403);
    }
}
