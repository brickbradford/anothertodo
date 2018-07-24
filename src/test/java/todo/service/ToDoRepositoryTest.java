package todo.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ToDoRepositoryTest {

    @Test
    public void test() {
        ToDoRepository repo = new ToDoRepositoryImpl();

        Optional<ToDoDetail> optToDo = repo.find(Long.MAX_VALUE);
        Assert.assertFalse(optToDo.isPresent());

        final String text1 = "todo1";
        ToDoDetail detail = new ToDoDetail(text1);
        final Long oid =  repo.add(detail);
        Assert.assertTrue(oid > 0);

        optToDo = repo.find(oid);
        Assert.assertTrue(optToDo.isPresent());
        detail = optToDo.get();
        Assert.assertTrue(text1.equals(detail.getText()));
        Assert.assertFalse(detail.isCompleted());

        final String text2 = "todo1-updated";
        repo.update(oid, Optional.of(text2), Optional.of(true));
        optToDo = repo.find(oid);
        Assert.assertTrue(optToDo.isPresent());
        detail = optToDo.get();
        Assert.assertTrue(text2.equals(detail.getText()));
        Assert.assertTrue(detail.isCompleted());

    }
}
