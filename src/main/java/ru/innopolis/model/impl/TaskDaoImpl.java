package ru.innopolis.model.impl;

import ru.innopolis.model.dao.TaskDao;
import ru.innopolis.model.entity.Task;

import java.sql.*;
import java.util.*;

public class TaskDaoImpl implements TaskDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/lab";
    private static final String DB_USER = "r2d2";
    private static final String DB_PASS = "";

    public Task getTask(long taskId) {
        Task task = new Task();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE id = '" + taskId + "'");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                task.setId(result.getInt("id"));
                task.setName(result.getString("name"));
                task.setDateFrom(result.getDate("date_from"));
                task.setDateTo(result.getDate("date_to"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return task;
    }

    public Task addTask(Task task) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks(name, date_from, date_to) VALUES ('" + task.getName() + "', '" + task.getDateFrom() + "', '" + task.getDateTo() + "')");
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return task;
    }

    public Task updateTask(Task task) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET name = '" +
                    task.getName() + "', date_from = '" + task.getDateFrom() + "' WHERE id_tasks = '" + task.getId() + "'");
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void deleteTask(long taskId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE id_tasks = '" + taskId + "'");
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Task task = new Task();
                task.setId(result.getInt("id"));
                task.setName(result.getString("name"));
                task.setDateFrom(result.getDate("date_from"));
                task.setDateTo(result.getDate("date_to"));
                taskList.add(task);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
