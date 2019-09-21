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


    public Long createLoading(Long truckSetId, String customerName, String adr, int price,
                              String currency, String countryOfLoad, String loadingPlaceCode, double weight,
                              LocalDateTime plannedDateTimeLoad, String countryOfUnload,
                              String unloadingPlaceCode, LocalDateTime plannedDateTimeUnload, String notes) {

        Loading newLoading = new Loading(truckSetId, customerName, adr, price, currency, countryOfLoad,
                loadingPlaceCode, weight, plannedDateTimeLoad, countryOfUnload, unloadingPlaceCode,
                plannedDateTimeUnload, notes);

        return loadingRepository.save(newLoading).getId();
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
        loading.updateLoadingDateTime(startOfLoad, endOfLoad);
        Optional.ofNullable(weight).ifPresent(loading::updateWeight);

        if (loading.getStartOfLoad() != null && loading.getEndOfLoad() != null) {
            loading.setStatus(Status.LOADED);
        }

        loadingRepository.save(loading);
    }

    public void updateUnloading(Loading loading, LocalDateTime startOfUnload,
                                LocalDateTime endOfUnload) {
        loading.setStartOfUnload(startOfUnload);
        loading.updateUnloadingDateTime(startOfUnload, endOfUnload);

        if (loading.getStartOfUnload() != null && loading.getEndOfUnload() != null) {
            loading.setStatus(Status.COMPLETED);
        }

        loadingRepository.save(loading);
    }
}
