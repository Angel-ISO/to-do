package project.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.task.Models.Role;

import java.util.Optional;


@Repository
public interface RoleRepository extends BaseRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
