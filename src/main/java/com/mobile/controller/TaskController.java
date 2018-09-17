package com.mobile.controller;

import com.mobile.pojo.Task;
import com.mobile.pojo.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/task")
public class TaskController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult add(HttpServletRequest request, Task task) {

        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult deleteTask(HttpServletRequest request, int taskId) {
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult updateTask(HttpServletRequest request, Task task) {
        return null;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult finishTask(HttpServletRequest request, int taskId) {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private @ResponseBody
    JsonResult getTaskList(HttpServletRequest request, int pageSize, int pageNumber) {
        return null;
    }

}
