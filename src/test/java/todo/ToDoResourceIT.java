package todo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import todo.json.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoResourceIT {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testBrackets() throws Exception {
        ResponseEntity<Brackets> response = restTemplate.getForEntity(
                createURLWithPort("/tasks/validateBrackets?input=asdf()"),
                Brackets.class);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getBody().isBalanced);
    }

    @Test
    public void testCreateToDo() {
        // Make one
        String text1 = "hello test";
        ToDoCreate create = new ToDoCreate(text1);
        ResponseEntity<ToDo> responseEntity =
                restTemplate.postForEntity(createURLWithPort("/todo"), create, ToDo.class);
        ToDo todo = responseEntity.getBody();
        Assert.assertNotNull(todo);
        Assert.assertTrue(todo.id > 0);
        Assert.assertTrue(text1.equals(todo.text));

        // Retrieve one
        Long oid = todo.id;
        responseEntity = restTemplate.getForEntity(createURLWithPort("/todo/" + oid), ToDo.class);
        todo = responseEntity.getBody();
        Assert.assertNotNull(todo);
        Assert.assertTrue(oid.equals(todo.id));
        Assert.assertTrue(text1.equals(todo.text));

        // Update one
        String text2 = "hello test2";
        ToDoUpdate update = new ToDoUpdate(text2, true);
        HttpEntity<ToDoUpdate> httpEntity = new HttpEntity<>(update);
        // Straight up HttpMethod.PATCH doesn't appear to work for RestTemplate
        // https://stackoverflow.com/questions/29447382/resttemplate-patch-request
        responseEntity = restTemplate.exchange(
                createURLWithPort("/todo/" + oid + "?_method=patch"), HttpMethod.POST, httpEntity, ToDo.class);
        todo = responseEntity.getBody();
        Assert.assertNotNull(todo);
        Assert.assertTrue(oid.equals(todo.id));
        Assert.assertTrue(text2.equals(todo.text));
        Assert.assertTrue(todo.isCompleted);
    }

    @Test
    public void testErrors() {
        ResponseEntity<NotFoundError> responseEntity =
                restTemplate.getForEntity(createURLWithPort("/todo/54321"), NotFoundError.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        NotFoundError error = responseEntity.getBody();
        Assert.assertNotNull(error);
        Assert.assertEquals(error.name, "NotFoundError");


        String text1 = "";
        ToDoCreate create = new ToDoCreate(text1);
        ResponseEntity<ValidationError> validationErrorResponseEntity =
                restTemplate.postForEntity(createURLWithPort("/todo"), create, ValidationError.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, validationErrorResponseEntity.getStatusCode());
        ValidationError validationError = validationErrorResponseEntity.getBody();
        Assert.assertNotNull(validationError);
        Assert.assertTrue(validationError.details.size() > 0);
        Assert.assertEquals(validationError.name, "ValidationError");
    }

    private String createURLWithPort(String uri) {
        return "http://127.0.0.1:" + port + "/test/1.0" + uri;
    }

}
