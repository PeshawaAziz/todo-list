package db;

import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import todo.entity.Step;
import todo.entity.Task;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int totalEntitiesCount = 0;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    public static void add(Entity entity) throws InvalidEntityException {
        try {
            Validator validator = validators.get(entity.getEntityCode());
            validator.validate(entity);
        } catch (NullPointerException e) {
            System.out.println("No validator has been registered for class: " + entity.getClass().getName());
        }

        if (entity instanceof Trackable trackable) {
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
        boolean found = false;
        for (Entity entity : entities) {
            if (entity.id == id || (entity instanceof Step step && step.getTaskRef() == id)) {
                found = true;
                entities.remove(i);
            }
            i++;
        }

        if (found)
            ;
        else
            throw new EntityNotFoundException(id);
    }

    public static void update(Entity entity) throws InvalidEntityException {
        try {
            Validator validator = validators.get(entity.getEntityCode());
            validator.validate(entity);
        } catch (NullPointerException e) {
            System.out.println("No validator has been registered for class: " + entity.getClass().getName());
        }

        if (entity instanceof Trackable trackable)
            trackable.setLastModificationDate(new Date());

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

    public static ArrayList<Entity> getAll(int entityCode) {
        ArrayList<Entity> entityList = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity.getEntityCode() == entityCode) {
                entityList.add(entity.clone());
            }
        }

        if (entityCode == Task.getCode()) {
            entityList.sort(Comparator.comparing(entity -> ((Task) entity).getDueDate()));
        }

        return entityList;
    }
}
