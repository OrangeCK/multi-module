package com.ck.multimoduleweb.exception;

import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimoduledao.entity.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ck
 * @date 2018/11/2 17:03
 * Description  : 全局异常控制处理
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class MyBasicErrorController extends AbstractErrorController {
    private final static Logger logger = LoggerFactory.getLogger(MyBasicErrorController.class);
    private final ErrorProperties errorProperties;

    @Autowired
    public MyBasicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties=serverProperties.getError();
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("error");
        Map<String, Object> errorMap = getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = this.getStatus(request);
        if(errorMap != null){
            modelAndView.addObject("error", errorMap.get("error"));
            modelAndView.addObject("status", errorMap.get("status"));
            modelAndView.addObject("timestamp", errorMap.get("timestamp"));
            modelAndView.addObject("path", errorMap.get("path"));
            modelAndView.addObject("message", errorMap.get("message"));
            logHandler(errorMap);
        }
        return modelAndView;
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        logHandler(body);
        return new ResponseEntity(ResultData.error().setCode((Integer)body.get("status")).setMsg((String)body.get("message")), status);
    }

    private void logHandler(Map<String, Object> errorMap) {
        logger.error("url:{},status{},time:{},error:{},errorMsg:{}",errorMap.get("path"),errorMap.get("status"),errorMap.get("timestamp"),errorMap.get("error"),errorMap.get("message"));
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.getErrorProperties().getIncludeStacktrace();
        return include == ErrorProperties.IncludeStacktrace.ALWAYS?true:(include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM?this.getTraceParameter(request):false);
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}
