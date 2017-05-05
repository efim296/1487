package ru.innopolis.model.dao;

import ru.innopolis.model.entity.Task;

import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks();

    Task getTask(long taskId);

    Task addTask(Task task);

    Task updateTask(Task task);

    void deleteTask(long taskId);
}
