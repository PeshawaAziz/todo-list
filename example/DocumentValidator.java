package example;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

public class DocumentValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
    }
}
