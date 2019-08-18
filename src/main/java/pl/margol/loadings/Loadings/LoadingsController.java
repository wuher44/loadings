package pl.margol.loadings.Loadings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.Loading.Loading;
import pl.margol.loadings.Loading.LoadingService;
import pl.margol.loadings.Utils.Status;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class LoadingsController {
    LoadingService loadingService;

    public LoadingsController(LoadingService loadingService) {
        this.loadingService = loadingService;
    }

    @GetMapping("/start")
    String getStart(Model model) {
        List<Loading> list = loadingService.listAllLoadings();
        model.addAttribute("list",
                list.stream().filter(l -> l.getStatus() != Status.COMPLETED).collect(Collectors.toList()));
        return "start";
    }

    @GetMapping("/error")
    public String accessDenied() {
        return "error";
    }

    @GetMapping("/")
    String root() {
        return "start";
    }
}
