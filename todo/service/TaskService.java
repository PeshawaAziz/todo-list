package todo.service;

import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import java.util.ArrayList;
import java.util.Date;
import todo.entity.Step;
import todo.entity.Task;
import todo.entity.Task.Status;

public class TaskService {
    public static void saveTask(String title, String description, Date dueDate) {
        try {
            Task task = new Task(title, description, Task.Status.NotStarted, dueDate);

            Database.add(task);
        } catch (InvalidEntityException e) {
            System.out.println("The provided entity is invalid.");
        }
    }

    public static void setAsNotStarted(int taskId) {
        updateTask(taskId, null, null, Status.NotStarted, null);
    }

    public static void setAsInProgress(int taskId) {
        updateTask(taskId, null, null, Status.InProgress, null);
    }

    public static void setAsCompleted(int taskId) {
        updateTask(taskId, null, null, Status.Completed, null);
    }

    public static void updateTitle(int taskId, String newTitle) {
        updateTask(taskId, newTitle, null, null, null);
    }

    public static void updateDescription(int taskId, String newDescription) {
        updateTask(taskId, null, newDescription, null, null);
    }

    public static void updateDueDate(int taskId, Date newDueDate) {
        updateTask(taskId, null, null, null, newDueDate);
    }

    private static void updateTask(int taskId, String newTitle, String newDescription, Task.Status newStatus,
            Date newDueDate) {
        try {
            Entity entity = Database.get(taskId);

            if (entity instanceof Task task) {
                if (newTitle != null)
                    task.setTitle(newTitle);

                if (newDescription != null)
                    task.setDescription(newDescription);

                if (newStatus != null)
                    task.setStatus(newStatus);

                if (newDueDate != null)
                    task.setDueDate(newDueDate);

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

    public static ArrayList<Step> getSteps(int taskId) {
        ArrayList<Step> stepList = new ArrayList<>();

        for (Entity entity : Database.getAll(Step.getCode())) {
            if (entity instanceof Step step)
                if (step.getTaskRef() == taskId)
                    stepList.add(step.clone());
        }

        return stepList;
    }
}