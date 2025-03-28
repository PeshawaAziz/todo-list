package example;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (entity instanceof Human human) {
            if (human.name == null || human.name.isEmpty() || human.age < 0)
                throw new InvalidEntityException("The provided Human entity is invalid.");
        } else {
            throw new IllegalArgumentException("The provided entity isn't an instance of Human.");
        }
    }
}
