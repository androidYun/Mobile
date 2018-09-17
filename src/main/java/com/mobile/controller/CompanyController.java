package com.mobile.controller;

import com.mobile.pojo.Company;
import com.mobile.pojo.User;
import com.mobile.pojo.common.JsonResult;
import com.mobile.pojo.common.ResultCode;
import com.mobile.service.impl.CompanyServiceImpl;
import com.mobile.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/list")
    private @ResponseBody
    JsonResult getCompany(int pageSize, int pageNumber) {
        JsonResult result = new JsonResult();
        List<Company> companyList = companyService.selectCompanyList(pageSize, pageNumber);
        result.setCode(ResultCode.SUCCESS.code());
        result.setData(companyList);
        return result;
    }

    @RequestMapping("/add")
    private @ResponseBody
    JsonResult addCompany(HttpServletRequest request, String companyName, String companyAddress) {
        JsonResult result = new JsonResult();
        Integer userId = (Integer) request.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("此用户不存在");
            return result;
        }
        if (StringUtils.isEmpty(companyName)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("公司名称不能为空");
            return result;
        }
        if (StringUtils.isEmpty(companyAddress)) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("公司地址不能为空");
            return result;
        }
        Company company = companyService.getCompanyByName(companyName);
        if (company != null) {
            result.setCode(ResultCode.SUCCESS_IS_HAVE.code());
            result.setMessage("此公司已经存在");
            return result;
        }
        int companyId = companyService.insertCompany(userId, companyName, companyAddress);
        if (companyId > -1) {
            result.setCode(ResultCode.SUCCESS.code());
        }
        return result;
    }

    @RequestMapping("/delete")
    private @ResponseBody
    JsonResult deleteCompany(HttpServletRequest request, Integer companyId) {
        JsonResult result = new JsonResult();
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("请输入用户Id");
            return result;
        }
        if (companyId == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("请输入要删除的公司Id");
            return result;
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("此用户不存在");
            return result;
        }
        if (user.getRole() == 1) {
            result.setCode(ResultCode.NOT_PERMISSION.code());
            result.setMessage("此用户没有删除权限");
            return result;
        }
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("此公司不存在");
            return result;
        }
        companyService.deleteCompanyById(companyId);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }
}
