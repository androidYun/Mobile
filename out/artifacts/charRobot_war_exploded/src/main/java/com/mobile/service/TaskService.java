package com.mobile.service;

import com.mobile.pojo.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(int taskId);

    Task getTaskByPhone(String phone);

    int addTask(Task task);

    void deleteTask(int taskId);

    List<Task> getTaskList(int userId,int isFinish,int pageSize, int pageNumber);

    void updateTask(Task task);


}
