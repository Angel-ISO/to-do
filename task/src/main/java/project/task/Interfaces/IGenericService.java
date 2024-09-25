package project.task.Interfaces;

import project.task.Models.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IGenericService<T extends BaseEntity, ID extends Serializable> {

    public List<T> getAll();

    public Optional<T> getById(ID id);

    public void create(T entity);

   public void update(T entity);

   public void delete(ID id);

}
