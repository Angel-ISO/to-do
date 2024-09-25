package project.task.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.task.Models.Task;
import project.task.Service.TaskService;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController implements BaseApiController<Task, Integer> {


    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.of(taskService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Task task) {
        taskService.create(task);
        return ResponseEntity.ok("Task sucessfully created");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Task task) {
        task.setId(id);
        taskService.update(task);
        return ResponseEntity.ok("Task sucessfully updated");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        taskService.delete(id);
        return ResponseEntity.ok("Task sucessfully deleted");
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId) {
        List<Task> tasks = taskService.findTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/user/{userId}/label/{labelId}")
    public ResponseEntity<List<Task>> getTasksByUserIdAndLabelId(@PathVariable Integer userId, @PathVariable Integer labelId) {
        List<Task> tasks = taskService.findTasksByUserIdAndLabelId(userId, labelId);
        return ResponseEntity.ok(tasks);
    }
}
