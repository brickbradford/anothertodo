package todo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToDoUpdate extends ToDoCreate {

    public final Boolean isCompleted;

    @JsonCreator
    public ToDoUpdate(@JsonProperty("text") String text,
                      @JsonProperty("isCompleted") Boolean isCompleted) {
        super(text);
        this.isCompleted = isCompleted;
    }
}
