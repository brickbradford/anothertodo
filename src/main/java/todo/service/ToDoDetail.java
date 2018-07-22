package todo.service;

import java.util.Date;

public class ToDoDetail {
    private final Date createdAt;
    private String text;
    private boolean isCompleted = false;

    public ToDoDetail() {
        this.createdAt = new Date();
    }

    public ToDoDetail(String text) {
        this();
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
