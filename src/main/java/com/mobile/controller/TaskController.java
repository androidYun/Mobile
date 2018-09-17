package com.mobile.controller;

import com.mobile.pojo.Task;
import com.mobile.pojo.common.JsonResult;
import com.mobile.pojo.common.ResultCode;
import com.mobile.service.impl.TaskServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult add(HttpServletRequest request, Task task) {
        JsonResult result = new JsonResult();
        if (StringUtils.isEmpty(task.getTaskTitle())) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("任务标题不能为空");
            return result;
        }
        if (StringUtils.isEmpty(task.getClientPhone())) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("客户手机号不能为空");
            return result;
        }
        if (StringUtils.isEmpty(task.getClientAddress())) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("客户地址不能为空");
            return result;
        }
        if (StringUtils.isEmpty(task.getAppointmentTime())) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("预约时间不能为空");
            return result;
        }
        task.setCreateTime(new Date());
        taskService.addTask(task);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult deleteTask(HttpServletRequest request, int taskId) {
        Task task = taskService.getTaskById(taskId);
        JsonResult result = new JsonResult();
        if (task == null) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("此任务不存在");
            return result;
        }
        taskService.deleteTask(taskId);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult updateTask(HttpServletRequest request, Task task) {
        JsonResult result = new JsonResult();
        if (task == null) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("参数错误");
            return result;
        }
        if (task.getTaskId() == null) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("参数错误");
            return result;
        }
        Task taskById = taskService.getTaskById(task.getTaskId());
        if (taskById == null) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("次任务不存在");
            return result;
        }
        taskService.updateTask(task);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult finishTask(HttpServletRequest request, int taskId) {
        Task task = taskService.getTaskById(taskId);
        JsonResult result = new JsonResult();
        if (task == null) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("此任务不存在");
            return result;
        }
        if (task.getIsFinish() == 1) {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage("此任务是已经完成的任务");
            return result;
        }
        task.setIsFinish(1);
        taskService.updateTask(task);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult getTaskList(HttpServletRequest request, int pageSize, int pageNumber) {
        JsonResult result = new JsonResult();
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            result.setCode(ResultCode.PARAMS_ERROR.code());
            result.setMessage("请输入用户Id");
            return result;
        }
        List<Task> taskList = taskService.getTaskList(userId, pageSize, pageNumber);
        result.setData(taskList);
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

}
