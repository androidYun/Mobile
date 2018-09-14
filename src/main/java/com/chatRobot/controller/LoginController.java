package com.chatRobot.controller;

import com.chatRobot.pojo.User;
import com.chatRobot.pojo.common.JsonResult;
import com.chatRobot.pojo.common.ResultCode;
import com.chatRobot.service.UserService;
import com.chatRobot.utils.JwtToken;
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
    public JsonResult login(String username, String password) throws Exception {
        User user = userService.login(username, password);
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
    public JsonResult register(Integer role, String username, String password) {
        JsonResult result = new JsonResult();
        if (StringUtils.isEmpty(username)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(password)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("密码不能为空");
            return result;
        }
        if (role != 1 || role != 2) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("角色类型选择错误");
            return result;
        }
        User user = userService.login(username, password);
        if (user != null) {
            result.setCode(ResultCode.SUCCESS_IS_HAVE.code());
            result.setMessage("此用户名已存在");
        } else {
            String register = userService.register(role, username, password);
            result.setCode(ResultCode.SUCCESS.code());
        }
        return result;
    }
}
