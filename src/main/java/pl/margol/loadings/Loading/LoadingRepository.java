package pl.margol.loadings.Loading;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadingRepository extends CrudRepository<Loading, Long> {
    List<Loading> findAll();
}
