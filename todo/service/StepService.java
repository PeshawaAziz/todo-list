package todo.service;

import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;

public class StepService {
    public static void saveStep(int taskRef, String title) {
        try {
            Step step = new Step(title, Step.Status.NotStarted, taskRef);

            Database.add(step);
        } catch (InvalidEntityException e) {
            System.out.println("The provided entity is invalid.");
        }
    }

    public static void setAsCompleted(int stepId) {
        updateStatus(stepId, Step.Status.Completed);
    }

    public static void setAsNotStarted(int stepId) {
        updateStatus(stepId, Step.Status.NotStarted);
    }

    private static void updateStatus(int stepId, Step.Status newStatus) {
        try {
            Entity entity = Database.get(stepId);

            if (entity instanceof Step step) {
                step.setStatus(newStatus);
                Database.update(step);
            } else {
                System.out.println("Entity with ID=" + stepId + " is not a Step.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Step with ID=" + stepId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Step with ID=" + stepId + " is invalid.");
        }
    }

    public static void updateTitle(int stepId, String newTitle) {
        try {
            Entity entity = Database.get(stepId);

            if (entity instanceof Step step) {
                step.setTitle(newTitle);
                Database.update(step);
            } else {
                System.out.println("Entity with ID=" + stepId + " is not a Step.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Step with ID=" + stepId + " not found.");
        } catch (InvalidEntityException e) {
            System.out.println("Step with ID=" + stepId + " is invalid.");
        }
    }
}
