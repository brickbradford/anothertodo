package todo.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ToDoRepositoryImpl implements ToDoRepository {

    private final Map<Long, ToDoDetail> todos = Collections.synchronizedMap(new HashMap<>());
    private final AtomicLong seq = new AtomicLong();
    private final Object updateGuard = new Object();

    public Optional<ToDoDetail> find(Long id) {
       return Optional.ofNullable(todos.get(id));
    }

    public Long add(ToDoDetail todo) {
        long nextId = seq.incrementAndGet();
        todos.put(nextId, todo);
        return nextId;
    }

    public Optional<ToDoDetail> update(Long id, String text, Boolean isCompleted) {
        Optional<ToDoDetail> detail = find(id);
        if (!detail.isPresent()) {
            return detail;
        }
        ToDoDetail todo = detail.get();
        // Avoid races on update
        synchronized (updateGuard) {
            todo.setCompleted(isCompleted);
            todo.setText(text);
        }
        return Optional.of(todo);
    }

}
