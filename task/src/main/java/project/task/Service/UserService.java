package project.task.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.task.Helpers.Autorizacion;
import project.task.Models.Role;
import project.task.Models.User;
import project.task.Repository.RoleRepository;
import project.task.Repository.UserRepository;
import project.task.dto.AddRoleDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GenericService<User, Integer>{


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;



    @Override
    public void create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("El username ya está en uso.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role defaultRole = roleRepository.findByName(Autorizacion.ROL_POR_DEFECTO.name())
                .orElseThrow(() -> new IllegalStateException("El rol por defecto no existe en la base de datos"));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            assert user.getRoles() != null;
            user.getRoles().add(defaultRole);
        }

        super.create(user);
    }




    @Override
    public List<User> getAll() {
        return userRepository.findAllWithRoles();
    }


    @Override
    public void update(User user) {
        Integer userId = user.getId();

        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("El username ya está en uso.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        super.update(user);
    }





    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public boolean authenticate(String username, String rawPassword) {
        User user = findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }




    public String addRole(AddRoleDto model) {
        User user = userRepository.findByUsername(model.getUsername());

        if (user == null) {
            return "No existe ningún usuario registrado con el nombre: " + model.getUsername();
        }

        if (passwordEncoder.matches(model.getPassword(), user.getPassword())) {

            Optional<Role> roleOptional = roleRepository.findByName(model.getRole().toLowerCase());

            if (roleOptional.isPresent()) {
                Role role = roleOptional.get();

                if (!user.getRoles().contains(role)) {
                    user.getRoles().add(role);
                    userRepository.save(user);

                    return "Rol " + model.getRole() + " sucessfully added to the user   " + model.getUsername() ;
                } else {
                    return "the user already has the role " + model.getRole();
                }
            } else {
                return "Rol " + model.getRole() + " not found .";
            }
        } else {
            return "Credentials are not valid for the user  " + model.getUsername();
        }
    }
}
