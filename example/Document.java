package example;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable {
    public static final int DOCUMENT_ENTITY_CODE = 14;

    public Date creationDate;
    public Date lastModificationDate;

    public String content;

    public Document(String content) {
        this.content = content;
    }

    @Override
    public Document clone() {
        return (Document) super.clone();
    }

    @Override
    public int getEntityCode() {
        return DOCUMENT_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
}