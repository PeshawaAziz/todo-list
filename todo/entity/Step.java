package todo.entity;

import db.Entity;

public final class Step extends Entity {
    private static final int STEP_ENTITY_CODE = 0;

    private String title;
    private Status status;
    private int taskRef;

    public enum Status {
        NotStarted, Completed
    }

    public Step(String title, Status status, int taskRef) {
        setTitle(title);
        setStatus(status);
        setTaskRef(taskRef);
    }

    @Override
    public Step clone() {
        return (Step) super.clone();
    }

    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Step title cannot be empty.");
        else
            this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }

    public int getTaskRef() {
        return taskRef;
    }

    public void setTaskRef(int taskRef) {
        if (taskRef < 0)
            throw new IllegalArgumentException("Step task reference cannot be negative.");
        else
            this.taskRef = taskRef;
    }

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }
}
