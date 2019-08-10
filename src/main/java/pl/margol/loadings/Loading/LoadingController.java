package pl.margol.loadings.Loading;

import org.springframework.stereotype.Controller;

@Controller
public class LoadingController {
    private LoadingService loadingService;

    public LoadingController(LoadingService loadingService) {
        this.loadingService = loadingService;
    }
}
