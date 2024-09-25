package project.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.task.Models.Label;
import project.task.Repository.LabelRepository;

@Service
public class LabelService extends GenericService<Label, Integer> {

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        super(labelRepository);
        this.labelRepository = labelRepository;
    }

    private final LabelRepository labelRepository;

}
