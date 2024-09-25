package project.task.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.task.Models.User;

import java.util.List;


@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> findAllWithRoles();
    
    @Query("select u from User u where u.username = ?1")
    public User findByUsername(String username);
}
