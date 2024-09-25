package project.task.Repository;

import org.springframework.stereotype.Repository;
import project.task.Models.Label;

@Repository
public interface LabelRepository extends BaseRepository<Label, Integer> {
}
