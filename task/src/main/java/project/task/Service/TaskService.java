package project.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.task.Models.Task;
import project.task.Repository.TaskRepository;

import java.util.List;


@Service
public class TaskService extends GenericService<Task, Integer> {

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;

    public List<Task> findTasksByUserId(Integer userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> findTasksByUserIdAndLabelId(Integer userId, Integer labelId) {
        return taskRepository.findByUserIdAndLabelId(userId, labelId);
    }

}
