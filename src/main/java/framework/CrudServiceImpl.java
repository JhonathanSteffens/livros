package framework;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    public abstract JpaRepository<T, ID> getRepository();
    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
