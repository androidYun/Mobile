package com.mobile.service.impl;

import com.mobile.dao.ITaskDao;
import com.mobile.pojo.Task;
import com.mobile.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    ITaskDao iTaskDao;

    @Override
    public Task getTaskById(int taskId) {
        return iTaskDao.selectTaskById(taskId);
    }

    @Override
    public int addTask(Task task) {
        return iTaskDao.addTask(task);
    }

    @Override
    public void deleteTask(int taskId) {
        iTaskDao.deleteTask(taskId);
    }

    @Override
    public List<Task> getTaskList(int userId,int isFinish, int pageSize, int pageNumber) {
        return iTaskDao.selectTaskListById(userId, isFinish,pageSize, pageNumber);
    }

    @Override
    public void updateTask(Task task) {
        iTaskDao.updateTask(task);
    }

    @Override
    public Task getTaskByPhone(String phone) {
        return iTaskDao.selectTaskByPhone(phone);
    }
}
