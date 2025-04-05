package todo.entity;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Task extends Entity implements Trackable {
    private static final int TASK_ENTITY_CODE = 0;

    private Date creationDate;
    private Date lastModificationDate;
    private Date dueDate;

    private String title;
    private String description;
    private Status status;

    public enum Status {
        NotStarted, InProgress, Completed
    }

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Task title cannot be empty.");
        else
            this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Task description cannot be empty.");
        else
            this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }

    @Override
    public Task clone() {
        Task cloned = (Task) super.clone();

        return cloned;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}
