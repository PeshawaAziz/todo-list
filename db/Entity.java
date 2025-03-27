package db;

public abstract class Entity implements Cloneable {
    public int id;

    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}