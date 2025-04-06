package todo.validator;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;
import todo.entity.Task;

public class TaskValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (entity instanceof Task task) {
            if (task.getTitle() == null || task.getTitle().isEmpty()) {
                throw new InvalidEntityException("Task title cannot be empty.");
            }
        } else
            throw new IllegalArgumentException("The provided entity isn't an instance of Task.");
    }
}
