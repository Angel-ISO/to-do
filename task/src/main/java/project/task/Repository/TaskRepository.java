package project.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.task.Models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends BaseRepository<Task, Integer> {


    @Query("SELECT t FROM Task t WHERE t.user.id = ?1")
    List<Task> findByUserId(Integer userId);

    @Query("SELECT t FROM Task t WHERE t.label.id = ?1 AND t.user.id = ?2")
    List<Task> findByUserIdAndLabelId(Integer userId, Integer labelId);

}
