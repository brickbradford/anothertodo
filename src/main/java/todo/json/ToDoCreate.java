package todo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

public class ToDoCreate {

    @Size(min=1, max =50, message = "Must be between 1 and 50 chars long")
    public final String text;

    @JsonCreator
    public ToDoCreate(@JsonProperty("text") String text) {
        this.text = text;
    }
}

