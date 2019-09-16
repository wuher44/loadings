package pl.margol.loadings.Driver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.margol.loadings.Utils.Status;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("select d from  Driver d where d.firstName like %:phrase% or d.lastName like %:phrase% ")
    List<Driver> searchDriverByPhrase(@Param("phrase") String phrase);

    @Query("select d from Driver d where d.firstName like %:firstName% and d.lastName like " +
            "%:lastName% ")
    List<Driver> searchDriverByName(@Param("firstName") String firstName,
                                    @Param("lastName") String lastName);

    long countByFirstNameAndLastName(String firstName, String lastName);

    List<Driver> findAllByOrderByLastNameAsc();

    @Query("select d from  Driver d where (d.firstName like %:phrase% or d.lastName like " +
            "%:phrase%) and d.status = :status ")
    List<Driver> searchByPhraseAndStatus(@Param("phrase") String phrase,
                                         @Param("status") Status status);

    List<Driver> findAllByStatus(Status status);




}


