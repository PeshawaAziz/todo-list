package todo.service;

import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import java.util.Date;
import todo.entity.Task;

public class TaskService {
    public static void saveTask(String title, String description, Date dueDate) {
        try {
            Task task = new Task(title, description, Task.Status.NotStarted, dueDate);

            Database.add(task);
        } catch (InvalidEntityException e) {
            System.out.println("The provided entity is invalid.");
        }
    }

    public static void setAsCompleted(int taskId) {
        updateStatus(taskId, Task.Status.Completed);
    }

    public static void setAsInProgress(int taskId) {
        updateStatus(taskId, Task.Status.InProgress);
    }

    public static void setAsNotStarted(int taskId) {
        updateStatus(taskId, Task.Status.NotStarted);
    }

    private static void updateStatus(int taskId, Task.Status newStatus) {
        try {
            Entity entity = Database.get(taskId);

            if (entity instanceof Task task) {
                task.setStatus(newStatus);
                Database.update(task);
            } else {
                System.out.println("Entity with ID=" + taskId + " is not an instance of Task.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Task with ID=" + taskId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Task with ID=" + taskId + " is invalid.");
        }
    }

    public static void updateTitle(int taskId, String newTitle) {
        try {
            Entity entity = Database.get(taskId);

            if (entity instanceof Task task) {
                task.setTitle(newTitle);
                Database.update(task);
            } else {
                System.out.println("Entity with ID=" + taskId + " is not an instance of Task.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Task with ID=" + taskId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Task with ID=" + taskId + " is invalid.");
        }
    }

    public static void updateDescription(int taskId, String newDescription) {
        try {
            Entity entity = Database.get(taskId);

            if (entity instanceof Task task) {
                task.setDescription(newDescription);
                Database.update(task);
            } else {
                System.out.println("Entity with ID=" + taskId + " is not an instance of Task.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Task with ID=" + taskId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Task with ID=" + taskId + " is invalid.");
        }
    }

    public static void updateDueDate(int taskId, Date newDueDate) {
        try {
            Entity entity = Database.get(taskId);

            if (entity instanceof Task task) {
                task.setDueDate(newDueDate);
                Database.update(task);
            } else {
                System.out.println("Entity with ID " + taskId + " is not an instance of Task.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Task with ID=" + taskId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Task with ID=" + taskId + " is invalid.");
        }
    }
}
