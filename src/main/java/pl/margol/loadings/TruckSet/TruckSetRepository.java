package pl.margol.loadings.TruckSet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckSetRepository extends CrudRepository<TruckSet, Long> {
    List<TruckSet> findAll();
}


