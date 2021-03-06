package com.mobile.dao;

import com.mobile.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITaskDao {

    List<Task> selectTaskListById(@Param("userId") int taskId, @Param("isFinish") int isFinish, @Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);

    int addTask(Task task);

    void deleteTask(int taskId);

    void updateTask(Task task);

    Task selectTaskById(int taskId);

    Task selectTaskByPhone(String phone);
}
