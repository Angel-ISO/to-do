package project.task.Service;

import org.springframework.stereotype.Service;
import project.task.Interfaces.IGenericService;
import project.task.Models.BaseEntity;
import project.task.Repository.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericService<T extends BaseEntity, ID extends Serializable> implements IGenericService<T, ID> {

    protected final BaseRepository<T, ID> repository;

    public GenericService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void create(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
