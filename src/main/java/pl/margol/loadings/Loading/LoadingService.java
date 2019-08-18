package pl.margol.loadings.Loading;

import org.springframework.stereotype.Service;
import java.util.List;

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
}
