package com.chatRobot.interceptor;

import com.alibaba.fastjson.JSON;
import com.chatRobot.pojo.common.JsonResult;
import com.chatRobot.pojo.common.ResultCode;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
        JsonResult result=new JsonResult();
        result.setCode(ResultCode.SYS_ERROR.code());
        result.setMessage(ResultCode.SYS_ERROR.msg());
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;

    }
}
