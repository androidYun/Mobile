package com.mobile.controller;

import com.mobile.pojo.User;
import com.mobile.pojo.common.JsonResult;
import com.mobile.pojo.common.ResultCode;
import com.mobile.service.UserService;
import com.mobile.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonResult login(String phone, String password) throws Exception {
        User user = userService.login(phone, password);
        JsonResult result = new JsonResult();
        if (user == null) {
            result.setCode(ResultCode.EXCEPTION.code());
            result.setMessage(ResultCode.EXCEPTION.msg());
        } else {
            result.setCode(ResultCode.SUCCESS.code());
            result.setData(JwtToken.createToken(user.getId().longValue()));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public JsonResult register( String phone, String password) {
        JsonResult result = new JsonResult();
        if (StringUtils.isEmpty(phone)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(password)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("密码不能为空");
            return result;
        }
        User user = userService.login(phone, password);
        if (user != null) {
            result.setCode(ResultCode.SUCCESS_IS_HAVE.code());
            result.setMessage("此用户名已存在");
        } else {
            String register = userService.register( phone, password);
            result.setCode(ResultCode.SUCCESS.code());
        }
        return result;
    }
}
