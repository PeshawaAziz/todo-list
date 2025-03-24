package db;

import db.exception.EntityNotFoundException;
import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int totalEntitiesCount = 0;

    public static void add(Entity e) {
        e.id = totalEntitiesCount;
        totalEntitiesCount++;
        entities.add(e);
    }

    public static Entity get(int id) {
        for (Entity entity : entities) {
            if (entity.id == id)
                return entity;
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
                entities.set(i, e);
                return;
            }
            i++;
        }
        throw new EntityNotFoundException(e.id);
    }
}
