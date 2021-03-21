package pl.margol.loadings.Loading;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadingRepository extends CrudRepository<Loading, Long> {

    List<Loading> findAll();

    @Query("Select l from Loading l where l.status <> 'COMPLETED'")
    List<Loading> searchInCompleted();

    List<Loading> findAllByOrderByPlannedDateAndTimeOfLoadAsc();

    List<Loading> findAllByOrderByPlannedDateAndTimeOfLoadDesc();

    @Query("SELECT DISTINCT YEAR(endOfLoad) FROM Loading  where endOfLoad IS NOT NULL")
    List<Integer> searchDistinctYearsOfLoadings();
}
