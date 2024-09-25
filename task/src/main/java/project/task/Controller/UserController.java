package project.task.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.task.Mapper.UserMapper;
import project.task.Models.User;
import project.task.Service.UserService;
import project.task.Utils.LoginResponse;
import project.task.dto.AddRoleDto;
import project.task.dto.LoginDto;
import project.task.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController implements BaseApiController<User, Integer> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.of(userService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok("User sucessfully created");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok("User sucessfully updated");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("User sucessfully deleted");
    }

    @PostMapping("/add-role")
    public ResponseEntity<String> addRoleToUser(@RequestBody @Valid AddRoleDto addRoleDto) {
        String response = userService.addRole(addRoleDto);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto credentials) {
        boolean authenticated = userService.authenticate(credentials.getUsername(), credentials.getPassword());
        if (authenticated) {
            User user = userService.findByUsername(credentials.getUsername());
            UserDto userDto = UserMapper.toDTO(user);
            LoginResponse response = new LoginResponse("Login successful", userDto);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password", null));
        }
    }

}
