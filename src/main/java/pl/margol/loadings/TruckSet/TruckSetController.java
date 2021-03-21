package pl.margol.loadings.TruckSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.Driver.DriverService;
import pl.margol.loadings.Utils.Status;

import java.util.List;

@Controller
public class TruckSetController {

    private final TruckSetService truckSetService;
    private final DriverService driverService;

    public TruckSetController(TruckSetService truckSetService, DriverService driverService) {
        this.truckSetService = truckSetService;
        this.driverService = driverService;
    }

    @GetMapping("/addTruckSet")
    String addTruckSetForm(Model model) {
        model.addAttribute("driversList", driverService.listAllDrivers());
        return "truckSet/addTruckSet";
    }

    @PostMapping("/addTruckSet")
    String addTruckSet(@RequestParam String truckPlate, @RequestParam String trailerPlate,
                       @RequestParam Long driverId, @RequestParam String company, Model model) {
        String firstName = driverService.findDriver(driverId).getFirstName();
        String lastName = driverService.findDriver(driverId).getLastName();

        truckPlate = truckPlate.trim().replaceAll("\\s*", "").toUpperCase();
        trailerPlate = trailerPlate.trim().replaceAll("\\s*", "").toUpperCase();
        company = company.trim().toUpperCase();
        if (company.equals("ERA") || company.equals("MIRPOL")) {
            boolean created = truckSetService.create(truckPlate, trailerPlate, driverId, firstName,
                    lastName, company);

            if (created) {
                driverService.editStatus(driverId, Status.NOT_AVAILABLE);
            }
            model.addAttribute("info", "TruckSet Created");
        } else {
            model.addAttribute("info", "ERA or Mirpol only!");
            model.addAttribute("driversList", driverService.listAllDrivers());
        }

        return "truckSet/addTruckSet";
    }

    @GetMapping("/truckSetList")
    String truckSetsList(Model model) {
        List<TruckSet> list = truckSetService.listAll();
        model.addAttribute("truckSetList", list);
        return "truckSet/list";
    }

    @GetMapping("/truckSet/edit/{id}")
    String getTruckSetEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("truckSet", truckSetService.findTruckSet(id));
        return "/truckSet/edit";
    }

    @PostMapping("/truckSet/edit/{id}")
    String editTruckSet(@PathVariable Long id, @RequestParam String status, Model model) {

        boolean isEdited = truckSetService.edit(id, status.equals("Active") ? Status.ACTIVE :
                Status.NOT_ACTIVE);

        if (isEdited) {

            driverService.editStatus(truckSetService.findTruckSet(id).getDriverId(),
                    Status.AVAILABLE);

        }
        return "redirect:/truckSetList";
    }
}