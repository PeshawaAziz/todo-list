package db;

import db.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int totalEntitiesCount = 0;
    private static HashMap<Integer, Validator> validators;

    public static void add(Entity e) {
        e.id = totalEntitiesCount;
        totalEntitiesCount++;
        entities.add(e.clone());
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

    public static void update(Entity e) {
        int i = 0;
        for (Entity entity : entities) {
            if (entity.id == e.id) {
                entities.set(i, e.clone());
                return;
            }
            i++;
        }
        throw new EntityNotFoundException(e.id);
    }

    public static void registerValidator(int entityCode, Validator validator) {
        if (validators.containsKey(entityCode))
            throw new IllegalArgumentException("The entity code already exists in the validators.");
        else
            validators.put(entityCode, validator);
    }
}
