package todo.service;

import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;

public class StepService {
    public static int saveStep(int taskRef, String title) {
        try {
            Step step = new Step(title, taskRef);

            Database.add(step);

            return step.id;
        } catch (InvalidEntityException e) {
            System.out.println("Cannot save step.");
            System.out.println(e.getMessage());

            return -1;
        }
    }

    public static void setAsNotStarted(int stepId) {
        updateTask(stepId, null, Step.Status.NotStarted);
    }

    public static void setAsCompleted(int stepId) {
        updateTask(stepId, null, Step.Status.Completed);
    }

    public static void updateTitle(int stepId, String newTitle) {
        updateTask(stepId, newTitle, null);
    }

    private static void updateTask(int stepId, String newTitle, Step.Status newStatus) {
        try {
            Entity entity = Database.get(stepId);

            if (entity instanceof Step step) {
                if (newTitle != null)
                    step.setTitle(newTitle);

                if (newStatus != null)
                    step.setStatus(newStatus);

                System.out.println("Successfully updated the step.");
                Database.update(step);
            } else {
                System.out.println("Entity with ID=" + stepId + " is not an instance of Step.");
            }
        } catch (EntityNotFoundException | InvalidEntityException e) {
            System.out.println("Cannot update step.");
            System.out.println(e.getMessage());
        }
    }
}
