package pl.margol.loadings.TruckSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.Driver.DriverService;
import pl.margol.loadings.Utils.Status;

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
    String addTruckSet(@RequestParam String truckPlate, @RequestParam String trailerPlate, @RequestParam Long driverId) {
    String firstName = driverService.findDriver(driverId).getFirstName();
    String lastName = driverService.findDriver(driverId).getLastName();
    truckPlate=truckPlate.trim().replaceAll("\\s*", "").toUpperCase();
    trailerPlate=trailerPlate.trim().replaceAll("\\s*", "").toUpperCase();

        boolean created = truckSetService.create(truckPlate, trailerPlate, driverId, firstName, lastName);
        if(created){
            driverService.editStatus(driverId, Status.NOT_AVAILABLE);
        }

        return "truckSet/addTruckSet";
    }
}
