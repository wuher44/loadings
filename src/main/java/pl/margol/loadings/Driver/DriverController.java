package pl.margol.loadings.Driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.Utils.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        firstName = firstName.replaceAll("\\s*", "").toUpperCase().trim();
        lastName = lastName.replaceAll("\\s*", "").toUpperCase().trim();

        if (!firstName.matches("[A-Z]+") || !lastName.matches("[A-Z]+")) {
            model.addAttribute("info", "Name have to contain only letters!!!!!!");
            return "driver/addDriver";
        }

        if (driverService.searchDriverByName(firstName, lastName).isEmpty()) {

            boolean result = driverService.create(firstName, lastName);
            model.addAttribute("DriverCreated", result);
            List<Driver> driversList = driverService.listAllDrivers();
            model.addAttribute("driversList", driversList);

            return "driver/addDriver";
        } else {
            model.addAttribute("info", "Driver already exist!!!!!!");
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
        List<Driver> driversList = driverService.listAllDrivers();
        driversList.sort(Comparator.comparing(Driver::getLastName));
        model.addAttribute("driversList", driversList);
        model.addAttribute("show", 1);
        return "driver/listOfDrivers";
    }

    @PostMapping("/listOfDrivers")
    String driversListView(@RequestParam(required = false) String phrase,
                           @RequestParam String show, Model model) {

        if (phrase == null || phrase.isEmpty()) {
            if (show.equals("all")) {
                List<Driver> driversList = driverService.listAllDrivers().stream()
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 1);
                return "driver/listOfDrivers";
            } else if (show.equals("available")) {
                List<Driver> driversList = driverService.listAllDrivers().stream()
                        .filter(e -> e.getStatus() == Status.AVAILABLE)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 2);
                return "driver/listOfDrivers";

            } else if (show.equals("not_available")) {
                List<Driver> driversList = driverService.listAllDrivers().stream()
                        .filter(e -> e.getStatus() == Status.NOT_AVAILABLE)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 3);
                return "driver/listOfDrivers";
            } else {
                List<Driver> driversList = driverService.listAllDrivers().stream()
                        .filter(e -> e.getStatus() == Status.FIRED)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 4);
                return "driver/listOfDrivers";
            }
        } else {
            phrase = phrase.toUpperCase();
            if (show.equals("all")) {

                List<Driver> driversList = driverService.searchDriverByPhrase(phrase).stream()
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 1);
                model.addAttribute("lastPhrase", phrase);
                return "driver/listOfDrivers";
            } else if (show.equals("available")) {

                List<Driver> driversList = driverService.searchDriverByPhrase(phrase).stream()
                        .filter(driver -> driver.getStatus() == Status.AVAILABLE)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList)
                        .addAttribute("show", 2)
                        .addAttribute("lastPhrase", phrase);
                return "driver/listOfDrivers";

            } else if (show.equals("not_available")) {
                List<Driver> driversList = driverService.searchDriverByPhrase(phrase).stream()
                        .filter(driver -> driver.getStatus() == Status.NOT_AVAILABLE)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 3);
                model.addAttribute("lastPhrase", phrase);
                return "driver/listOfDrivers";
            } else {
                List<Driver> driversList = driverService.searchDriverByPhrase(phrase).stream()
                        .filter(driver -> driver.getStatus() == Status.FIRED)
                        .sorted(Comparator.comparing(Driver::getLastName))
                        .collect(Collectors.toList());

                model.addAttribute("driversList", driversList);
                model.addAttribute("show", 4);
                model.addAttribute("lastPhrase", phrase);
                return "driver/listOfDrivers";
            }
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

        boolean result = driverService.edit(id,
                firstName.trim().replaceAll("\\s*", "").toUpperCase(),
                lastName.trim().replaceAll("\\s*", "").toUpperCase(), status.equals("Available")
                        ? Status.AVAILABLE : status.equals("Not_Available") ?
                        Status.NOT_AVAILABLE : Status.FIRED);

        return "redirect:/listOfDrivers";
    }

    @PostMapping("/searchDriver")
    String searchDriver(@RequestParam String phrase, Model model) {
        if (phrase == null || phrase.isEmpty()) {

            return "redirect:/listOfDrivers";
        } else {
            List<Driver> driversList = driverService.searchDriverByPhrase(phrase.toUpperCase());
            model.addAttribute("driversList", driversList);

            return "driver/listOfDrivers";
        }
    }
}
