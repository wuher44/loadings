package pl.margol.loadings.Loading;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.margol.loadings.Utils.Status;

@Service
public class LoadingService {

    private LoadingRepository loadingRepository;

    public LoadingService(LoadingRepository loadingRepository) {
        this.loadingRepository = loadingRepository;
    }

    boolean createLoading(Loading loading) {
        Loading created = loadingRepository.save(loading);

        return created.getId() != null;
    }

    public List<Loading> listAllLoadings() {
        List<Loading> list = loadingRepository.findAll();
        return list;
    }

    public Loading findLoadingById(Long id) {

        Loading loading = loadingRepository.findById(id).get();

        return loading;
    }

    public List<Loading> listAllLoadingsByPlannedDate() {
        return loadingRepository.findAllByOrderByPlannedDateAndTimeOfLoadAsc();
    }

    public void updateLoading(Loading loading, LocalDateTime startOfLoad, LocalDateTime endOfLoad,
                              Double weight) {
        loading.setStartOfLoad(startOfLoad);
        loading.updateLoadingTime(endOfLoad, startOfLoad);
        Optional.ofNullable(weight).ifPresent(loading::updateWeight);

        if (loading.getStartOfLoad() != null && loading.getEndOfLoad() != null) {
            loading.setStatus(Status.LOADED);
        }

        loadingRepository.save(loading);
    }
}
