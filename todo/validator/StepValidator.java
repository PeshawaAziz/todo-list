package todo.validator;

import db.Database;
import db.Entity;
import db.Validator;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (entity instanceof Step step) {
            if (step.getTitle() == null || step.getTitle().isEmpty()) {
                throw new InvalidEntityException("Task title cannot be empty.");
            }
            try {
                Database.get(step.getTaskRef());
            } catch (EntityNotFoundException e) {
                throw new InvalidEntityException("Referenced task does not exist.");
            }
        } else
            throw new IllegalArgumentException("The provided entity isn't an instance of Step.");
    }
}
