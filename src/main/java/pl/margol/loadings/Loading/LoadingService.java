package pl.margol.loadings.Loading;

import org.springframework.stereotype.Service;

@Service
public class LoadingService {
    private LoadingRepository loadingRepository;

    public LoadingService(LoadingRepository loadingRepository) {
        this.loadingRepository = loadingRepository;
    }
}
