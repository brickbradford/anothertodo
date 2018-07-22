package todo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;

public class ValidationError {

    public final Collection<ValidationErrorDetail> details;
    public final String name = "ValidationError";

    @JsonCreator
    public ValidationError(@JsonProperty("details") Collection<ValidationErrorDetail> details) {
        this.details = details;
    }

    public static ValidationError create(String location, String param, String msg, String value) {
        return new ValidationError(Collections.singleton(
                new ValidationErrorDetail(location, param, msg, value)));
    }

    public static class ValidationErrorDetail {
        public final String location;
        public final String param;
        public final String msg;
        public final String value;

        @JsonCreator
        public ValidationErrorDetail(@JsonProperty("location") String location,
                                     @JsonProperty("param") String param,
                                     @JsonProperty("msg") String msg,
                                     @JsonProperty("value") String value) {
            this.location = location;
            this.param = param;
            this.msg = msg;
            this.value = value;
        }
    }
}
