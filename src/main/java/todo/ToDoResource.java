package todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.json.Brackets;
import todo.json.ToDo;
import todo.json.ToDoCreate;
import todo.json.ToDoUpdate;
import todo.service.BalancedBracketChecker;
import todo.service.ToDoDetail;
import todo.service.ToDoRepository;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
public class ToDoResource {

    @Autowired
    private ToDoRepository todoRepository;

    @GetMapping(path = "/tasks/validateBrackets",
            consumes= {"text/plain", "application/*"},
            produces = "application/json")
    public Brackets checkBrackets(@RequestParam @Size(max=50) String input) {
        return new Brackets(input, BalancedBracketChecker.isBalanced(input));
    }

    @GetMapping("/todo/{id}")
    public ToDo find(@PathVariable long id) {
        return buildToDo(id, checkToDo(id, todoRepository.find(id)));
    }

    @PostMapping("/todo")
    public ToDo create(@Valid @RequestBody ToDoCreate todo) {
        Long id =  todoRepository.add(new ToDoDetail(todo.text));
        return find(id);
    }

    @PatchMapping("/todo/{id}")
    public ToDo update(@Valid @RequestBody ToDoUpdate todo, @PathVariable long id) {
        return buildToDo(id, checkToDo(id,
                todoRepository.update(id, Optional.ofNullable(todo.text),
                        Optional.ofNullable(todo.isCompleted))));
    }

    protected ToDoDetail checkToDo(long id, Optional<ToDoDetail> todo) {
        if (!todo.isPresent())
            throw new EntityNotFoundException("Item with " + id + " not found");
        return todo.get();
    }

    protected ToDo buildToDo(Long id, ToDoDetail detail) {
        return new ToDo(id, detail.getCreatedAt(), detail.getText(), detail.isCompleted());
    }
}
