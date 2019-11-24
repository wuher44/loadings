package pl.margol.loadings.Loadings;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.margol.loadings.Loading.Loading;
import pl.margol.loadings.Loading.LoadingRepository;


@Controller
public class LoadingsController {

    LoadingRepository loadingRepository;


    public LoadingsController(LoadingRepository loadingRepository) {
        this.loadingRepository = loadingRepository;
    }

    @GetMapping("/start")
    String getStart(Model model) {
        List<Loading> list = loadingRepository.searchInCompleted();
        model.addAttribute("list", list);

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
