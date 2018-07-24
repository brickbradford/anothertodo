package todo.service;

import java.util.Optional;

/**
 * Repository service to ToDos
 */
public interface ToDoRepository {

    Optional<ToDoDetail> find(Long id);

    Long add(ToDoDetail todo);

    Optional<ToDoDetail> update(Long id, Optional<String> text, Optional<Boolean> isCompleted);

}
