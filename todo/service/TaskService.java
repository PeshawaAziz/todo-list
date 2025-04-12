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
    public static int saveTask(String title, String description, Date dueDate) {
        try {
            Task task = new Task(title, description, dueDate);

            Database.add(task);

            return task.id;
        } catch (InvalidEntityException e) {
            System.out.println("Cannot save task.");
            System.out.println(e.getMessage());

            return -1;
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

                System.out.println("Successfully updated the task.");
                Database.update(task);
            } else {
                System.out.println("Entity with ID=" + taskId + " is not an instance of Task.");
            }
        } catch (EntityNotFoundException | InvalidEntityException e) {
            System.out.println("Cannot update task.");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> getIncompleteTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        for (Entity entity : Database.getAll(Task.getCode())) {
            if (entity instanceof Task task)
                if (task.getStatus() == Task.Status.NotStarted || task.getStatus() == Task.Status.InProgress)
                    taskList.add(task.clone());
        }

        return taskList;
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