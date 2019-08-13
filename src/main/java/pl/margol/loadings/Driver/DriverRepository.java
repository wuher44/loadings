package pl.margol.loadings.Driver;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findAll();
    @Query("select d from  Driver d where d.firstName like %:phrase% or d.lastName like %:phrase% ")
    List<Driver> searchDriverByPhrase(@Param("phrase") String phrase);

    @Query("select d from Driver d where d.firstName like %:firstName% and d.lastName like %:lastName% ")
    List<Driver> searchDriverByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}


