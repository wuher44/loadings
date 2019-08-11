package pl.margol.loadings.Loading;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.TruckSet.TruckSetService;

import java.time.LocalDateTime;

@Controller
public class LoadingController {
    private LoadingService loadingService;
    private TruckSetService truckSetService;

    public LoadingController(LoadingService loadingService, TruckSetService truckSetService) {
        this.loadingService = loadingService;
        this.truckSetService = truckSetService;
    }

    @GetMapping("/addLoading")
    String addLoadingForm(Model model) {
        model.addAttribute("truckSetList", truckSetService.listAll());
        return "/loading/addLoading";
    }

    @PostMapping("/addLoading")
    String creatLoading(@RequestParam Long truckSetId, @RequestParam String customer,
                        @RequestParam(required = false) boolean adr,
                        @RequestParam String price, @RequestParam String currency,
                        @RequestParam String countryOfLoad,
                        @RequestParam String loadingPlaceCode, @RequestParam String weight,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDateTimeLoad,
                        @RequestParam String countryOfUnload,
                        @RequestParam String unloadingPlaceCode,
                        @RequestParam String plannedDateTimeUnload,
                        @RequestParam String notes, Model model) {
//        LocalDateTime pDaToL = LocalDateTime.parse(plannedDateTimeLoad);
        LocalDateTime pDaToU = LocalDateTime.parse(plannedDateTimeUnload);
        int priceInt = Integer.parseInt(price);
        double pladedWeightDouble = Double.parseDouble(weight);

        Loading newLoading = new Loading(truckSetId, customer, adr, priceInt, currency,
                countryOfLoad, loadingPlaceCode,
                pladedWeightDouble, plannedDateTimeLoad, countryOfUnload, unloadingPlaceCode,
                pDaToU, notes);

        boolean result = loadingService.createLoading(newLoading);
        model.addAttribute("listOfAllLoadings", loadingService.listAllLoadings());

        return "loading/listOfLoadings";


    }

}
