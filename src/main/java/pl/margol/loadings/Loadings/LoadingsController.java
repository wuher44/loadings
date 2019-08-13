package pl.margol.loadings.Loadings;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LoadingsController {


    @GetMapping("/start")
    String getStart() {
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
