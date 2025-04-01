package db;

import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int totalEntitiesCount = 0;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    public static void add(Entity entity) throws InvalidEntityException {
        Validator validator = validators.get(entity.getEntityCode());
        validator.validate(entity);

        // TODO use the instanceof patten if possible
        if (entity instanceof Trackable) {
            Trackable trackable = (Trackable) entity;
            Date now = new Date();

            trackable.setCreationDate(now);
            trackable.setLastModificationDate(now);
        }

        entity.id = totalEntitiesCount;
        totalEntitiesCount++;
        entities.add(entity.clone());
    }

    public static Entity get(int id) {
        for (Entity entity : entities) {
            if (entity.id == id)
                return entity.clone();
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) {
        int i = 0;
        for (Entity entity : entities) {
            if (entity.id == id) {
                entities.remove(i);
                return;
            }
            i++;
        }
        throw new EntityNotFoundException(id);
    }

    public static void update(Entity entity) throws InvalidEntityException {
        Validator validator = validators.get(entity.getEntityCode());
        validator.validate(entity);

        // TODO use the instanceof patten if possible
        if (entity instanceof Trackable) {
            Trackable trackable = (Trackable) entity;

            trackable.setLastModificationDate(new Date());
        }

        int i = 0;
        for (Entity e : entities) {
            if (e.id == e.id) {
                entities.set(i, e.clone());
                return;
            }
            i++;
        }
        throw new EntityNotFoundException(entity.id);
    }

    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.containsKey(entityCode))
            throw new IllegalArgumentException("The entity code already exists in the validators.");
        else
            validators.put(entityCode, validator);
    }
}
