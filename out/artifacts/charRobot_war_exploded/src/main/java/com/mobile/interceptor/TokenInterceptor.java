package com.mobile.interceptor;

import com.alibaba.fastjson.JSON;
import com.mobile.pojo.common.JsonResult;
import com.mobile.pojo.common.ResultCode;
import com.mobile.utils.JwtToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null) {
            Long userId = JwtToken.getAppUID(token);
            if (userId != null) {
                request.setAttribute("userId", userId.intValue());
                return true;
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        JsonResult result = new JsonResult();
        result.setCode(ResultCode.TOKEN_ERROR.code());
        result.setMessage(ResultCode.TOKEN_ERROR.msg());
        String json = JSON.toJSONString(result);
        out.print(json);
        out.flush();
        out.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
