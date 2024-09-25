package project.task.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.task.Models.Role;
import project.task.Service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController implements BaseApiController<Role, Integer> {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }



    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.of(roleService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role) {
        roleService.create(role);
        return ResponseEntity.ok("Role sucessfully created");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return ResponseEntity.ok("Role sucessfully updated");
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.ok("Role sucessfully deleted");
    }
}

