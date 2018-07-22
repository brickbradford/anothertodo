package todo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;

public class NotFoundError {
    public final Collection<NotFoundErrorDetail> details;
    public final String name = "NotFoundError";

    @JsonCreator
    public NotFoundError(@JsonProperty("details") Collection<NotFoundErrorDetail> details) {
        this.details = details;
    }

    public static NotFoundError create(String  message) {
        return new NotFoundError(Collections.singleton(
                new NotFoundErrorDetail(message)));
    }

    public static class NotFoundErrorDetail {
        public final String message;

        @JsonCreator
        public NotFoundErrorDetail(@JsonProperty("message") String message) {
            this.message = message;
        }
    }
}
