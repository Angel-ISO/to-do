package project.task.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends BaseEntity {

    private String tittle;
    private String description;
    private LocalDateTime create_date;
    private LocalDateTime due_date;
    private Boolean complete;

    @ManyToOne
    @JoinColumn(name = "id_label", nullable = true)  // la etiqueta es opcional GG
    private Label label;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return getId() == task.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
