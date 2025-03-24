package db;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int totalEntitiesCount = 0;

    public static void add(Entity e) {
        e.id = totalEntitiesCount;
        totalEntitiesCount++;
        entities.add(e);
    }

    public static void get(int id) {

    }

    public static void delete(int id) {

    }

    public static void update(Entity e) {

    }
}
