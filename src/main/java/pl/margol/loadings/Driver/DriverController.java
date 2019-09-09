package pl.margol.loadings.Driver;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.margol.loadings.Utils.Status;

@Controller
public class DriverController {

    private final DriverService driverService;


    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/addDriver")
    String addDriverForm() {
        return "driver/addDriver";
    }

    @PostMapping("/addDriver")
    String addDriver(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        try {
            Long driverId = driverService.create(firstName, lastName);
            model.addAttribute("driverId", driverId);
            List<Driver> driversList = driverService.listAllDrivers();
            model.addAttribute("driversList", driversList);
            model.addAttribute("info", "Driver created!!!!");
            return "driver/addDriver";
        } catch (Exception e) {
            model.addAttribute("info", e.getLocalizedMessage());

            return "driver/addDriver";
        }
    }

    @GetMapping("/driver/delete/{id}")
    public String deleteDriver(@PathVariable long id) {
        driverService.deleteDriver(id);

        return "redirect:/listOfDrivers";
    }

    @GetMapping("/listOfDrivers")
    String listDrivers(Model model) {
        List<Driver> driversList = driverService.listAllDriversSortedByLastName();
        model.addAttribute("driversList", driversList);
        model.addAttribute("show", 1);
        return "driver/listOfDrivers";
    }

    @PostMapping("/listOfDrivers")
    String driversListView(@RequestParam(required = false) String phrase,
                           @RequestParam String show, Model model) {

        model.addAttribute("driversList", driverService.findDrivers(phrase, show));
        model.addAttribute("show", toShowNumber(show));
        model.addAttribute("lastPhrase", phrase);
        return "driver/listOfDrivers";
    }

    private int toShowNumber(String show) {
        switch (show) {
            case "all":
                return 1;
            case "available":
                return 2;
            case "not_available":
                return 3;
            case "fired":
                return 4;
            default:
                throw new IllegalArgumentException("invalid show value");
        }
    }

    @GetMapping("/driver/edit/{id}")
    String editDriverForm(@PathVariable long id, Model model) {

        model.addAttribute("driver", driverService.findDriver(id));

        return "/driver/edit";
    }

    @PostMapping("/driver/edit/{id}")
    String editDriver(@PathVariable long id, @RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam String status, Model model) {
        try {
            boolean result = driverService.edit(id, firstName, lastName,
                    Status.valueOf(status.toUpperCase()));
            return "redirect:/listOfDrivers";
        } catch (Exception e) {
            model.addAttribute("driver", driverService.findDriver(id));
            model.addAttribute("info", e.getLocalizedMessage());
            return "/driver/edit";

        }
    }
    /*@PostMapping("/searchDriver")
    String searchDriver(@RequestParam String phrase, Model model) {
        if (StringUtils.isEmpty(phrase)) {
            return "redirect:/listOfDrivers";
        } else {
            List<Driver> driversList = driverService.searchDriverByPhrase(phrase.toUpperCase());
            model.addAttribute("driversList", driversList);

            return "driver/listOfDrivers";
        }
    }*/

}
