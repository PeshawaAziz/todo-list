package example;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (entity instanceof Human human) {
            if (human.name == null || human.name.isEmpty())
                throw new InvalidEntityException(
                        "Entity with id=" + entity.id + " is invalid. (Human with null or empty name)");
            else if (human.age < 0)
                throw new InvalidEntityException(
                        "Entity with id=" + entity.id + " is invalid. (Human with negative age)");
        } else {
            throw new IllegalArgumentException("The provided entity isn't an instance of Human.");
        }
    }
}
