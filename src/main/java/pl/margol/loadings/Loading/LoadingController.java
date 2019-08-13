package pl.margol.loadings.Loading;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.margol.loadings.Customer.Customer;
import pl.margol.loadings.Customer.CustomerService;
import pl.margol.loadings.TruckSet.TruckSetService;
import pl.margol.loadings.Utils.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Controller
public class LoadingController {
    private LoadingService loadingService;
    private TruckSetService truckSetService;
    private CustomerService customerService;

    public LoadingController(LoadingService loadingService, TruckSetService truckSetService,
                             CustomerService customerService) {
        this.loadingService = loadingService;
        this.truckSetService = truckSetService;
        this.customerService = customerService;
    }


    @GetMapping("/addLoading")
    String addLoadingForm(Model model) {
        List<Customer> customers = customerService.findAll();
        customers.sort(Comparator.comparing(Customer::getName));
        model.addAttribute("truckSetList", truckSetService.listAll());
        model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        model.addAttribute("customersList", customers);
        return "/loading/addLoading";
    }

    @PostMapping("/addLoading")
    String creatLoading(@RequestParam Long truckSetId, @RequestParam String customerName,
                        @RequestParam(required = false) boolean adr,
                        @RequestParam String price, @RequestParam String currency,
                        @RequestParam String countryOfLoad,
                        @RequestParam String loadingPlaceCode, @RequestParam String weight,
                        @RequestParam(required = false) @DateTimeFormat(iso =
                                DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDateTimeLoad,
                        @RequestParam String countryOfUnload,
                        @RequestParam String unloadingPlaceCode,
                        @RequestParam(required = false) @DateTimeFormat(iso =
                                DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDateTimeUnload,
                        @RequestParam String notes, Model model) {

        int priceInt = Integer.parseInt(price);
        double pladedWeightDouble = Double.parseDouble(weight);

        Loading newLoading = new Loading(truckSetId, customerName, adr, priceInt, currency,
                countryOfLoad, loadingPlaceCode,
                pladedWeightDouble, plannedDateTimeLoad, countryOfUnload, unloadingPlaceCode,
                plannedDateTimeUnload, notes);

        boolean result = loadingService.createLoading(newLoading);
        //  model.addAttribute("listOfAllLoadings", loadingService.listAllLoadings());
        return "redirect:/listOfLoadings";
        //  return "loading/listOfLoadings";
    }

    @GetMapping("/listOfLoadings")
    String listLoadings(Model model) {
        model.addAttribute("listOfAllLoadings", loadingService.listAllLoadings());
        return "loading/listOfLoadings";
    }

    @GetMapping("/loading/setLoad/{id}")
    String setDTofLoadForm(@PathVariable Long id, Model model) {
        Loading loading = loadingService.findLoadingById(id);
        model.addAttribute("loading", loading);
        return "/loading/setDTofLoad";
    }

    @PostMapping("/loading/setLoad/{id}")
    String setDTofLoad(@PathVariable Long id, @RequestParam(required = false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOfLoad, @RequestParam(required =
            false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOfLoad, Model model) {
        Loading loadingToSetLoad = loadingService.findLoadingById(id);
        loadingToSetLoad.setStartOfLoad(startOfLoad);
        loadingToSetLoad.setEndOfLoad(endOfLoad);
        if (loadingToSetLoad.getStartOfLoad() != null && loadingToSetLoad.getEndOfLoad() != null) {
            loadingToSetLoad.setStatus(Status.LOADED);
        }
        loadingService.createLoading(loadingToSetLoad);


        return "redirect:/listOfLoadings";


    }

}
