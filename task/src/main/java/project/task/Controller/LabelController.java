package project.task.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.task.Models.Label;
import project.task.Service.LabelService;

@RestController
@RequestMapping("/api/labels")
public class LabelController implements BaseApiController<Label, Integer> {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }


    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(labelService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.of(labelService.getById(id));
    }

    @Override
    public ResponseEntity<?> save(@RequestBody Label label) {
        labelService.create(label);
        return ResponseEntity.ok("Label sucessfully created");
    }

    @Override
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return ResponseEntity.ok("Label sucessfully updated");
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        labelService.delete(id);
        return ResponseEntity.ok("Label sucessfully deleted");
    }
}
