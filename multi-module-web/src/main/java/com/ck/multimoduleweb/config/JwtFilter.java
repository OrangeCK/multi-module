package com.ck.multimoduleweb.config;

import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimodulecommon.util.JwtUtil;
import com.ck.multimoduledao.entity.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author ck
 * @date 2018/12/12 13:43
 * Description  :  JWT针对shiro的过滤器
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    /**
     * 登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 得到header中的token
        String token = httpServletRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        // 判断请求头上是否带有token
        if(token != null){
            // 如果存在token,则进入executeLogin方法检查token是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        // 如果请求头不存在 token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 执行登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 得到header中的token
        String token = httpServletRequest.getHeader(LmEnum.AUTHORIZATION.getName());
        try {
            // 获取用户名和密码
            String loginName = JwtUtil.getLoginName(token, LmEnum.LOGIN_NAME.getName());
            if(loginName == null){
                throw new AuthenticationException("token身份认证失败，token格式不正确");
            }
            // 验证token是否失效
            if(!JwtUtil.verify(token, loginName)){
                throw new IncorrectCredentialsException("token身份认证失败，token失效");
            }
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            JwtToken jwtToken = new JwtToken(token);
            getSubject(request, response).login(jwtToken);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 提供对跨域的支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
