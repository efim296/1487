package ru.innopolis.services;

import ru.innopolis.model.dao.TaskDao;
import ru.innopolis.model.entity.Task;
import ru.innopolis.model.impl.TaskDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
public class TaskService {
    TaskDao taskDao = new TaskDaoImpl();

    // URI:
    // /contextPath/servletPath/employees
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Task> getEmployees_JSON() {
        List<Task> listOfTask = taskDao.getAllTasks();
        return listOfTask;
    }

    // URI:
    // /contextPath/servletPath/employees/{empNo}
    @GET
    @Path("/{taskId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Task getEmployee(@PathParam("taskId") long taskId) {
        return taskDao.getTask(taskId);
    }

    // URI:
    // /contextPath/servletPath/employees
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Task addEmployee(Task task) {
        return taskDao.addTask(task);
    }


    // URI:
    // /contextPath/servletPath/employees
    @PUT
    @Consumes("application/json")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEmployee(Task task) {
        taskDao.updateTask(task);
        String result = "Product created : ";
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/{taskId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEmployee(@PathParam("taskId") long taskId) {
        taskDao.deleteTask(taskId);
    }

}