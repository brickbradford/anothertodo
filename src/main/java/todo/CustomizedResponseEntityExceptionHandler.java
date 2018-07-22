package todo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import todo.json.NotFoundError;
import todo.json.ValidationError;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(
                ValidationError.create( "params", ex.getParameter().getParameterName(),
                        ex.getMessage(), ex.getBindingResult().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<NotFoundError> handleUserNotFoundException(EntityNotFoundException ex,
                                                                          WebRequest request) {
        return new ResponseEntity<NotFoundError>(NotFoundError.create(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
