package project.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.task.Models.Role;
import project.task.Repository.BaseRepository;
import project.task.Repository.RoleRepository;

@Service
public class RoleService extends GenericService<Role, Integer> {

    @Autowired
    public RoleService(BaseRepository<Role, Integer> repository) {
        super(repository);
    }
}
