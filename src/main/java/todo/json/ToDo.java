package todo.json;

import java.util.Date;

public class ToDo {
    public final Long id;
    public final Date createdAt;
    public final String text;
    public final Boolean isCompleted;

    public ToDo(Long id, Date createdAt, String text, Boolean isCompleted) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
        this.isCompleted = isCompleted;
    }
}
