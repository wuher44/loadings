package pl.margol.loadings.Loading;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

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

@Controller
public class LoadingController {

    private static final List<String> ADR_CODES = Arrays.asList("-", "1", "2", "3", "4", "5", "6"
            , "7", "8", "9");
    private static final List<String> COUNTRIES = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK",
            "EST", "ESP",
            "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");
    private LoadingService loadingService;
    private TruckSetService truckSetService;
    private CustomerService customerService;

    public LoadingController(LoadingService loadingService, TruckSetService truckSetService,
                             CustomerService customerService) {
        this.loadingService = loadingService;
        this.truckSetService = truckSetService;
        this.customerService = customerService;
    }

    private void initLoadingForm(Model model) {
        List<Customer> customers = customerService.findAllByOrderByNameAsc();
        model.addAttribute("truckSetList", truckSetService.listAll());
        model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        model.addAttribute("customersList", customers);
        model.addAttribute("adrCodes", ADR_CODES);
        model.addAttribute("countries", COUNTRIES);
    }


    @GetMapping("/addLoading")
    String addLoadingForm(Model model) {
        initLoadingForm(model);

        return "/loading/addLoading";
    }


    @PostMapping("/addLoading")
    String createLoading(@RequestParam Long truckSetId, @RequestParam String customerName,
                         @RequestParam String adr,
                         @RequestParam Integer price, @RequestParam String currency,
                         @RequestParam String countryOfLoad,
                         @RequestParam String loadingPlaceCode, @RequestParam Double weight,
                         @RequestParam(required = false) @DateTimeFormat(iso =
                                 DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDateTimeLoad,
                         @RequestParam String countryOfUnload,
                         @RequestParam String unloadingPlaceCode,
                         @RequestParam(required = false) @DateTimeFormat(iso =
                                 DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDateTimeUnload,
                         @RequestParam String notes, Model model) {


        initLoadingForm(model);

        try {
            Long loadingId = loadingService.createLoading(truckSetId, customerName, adr, price,
                    currency,
                    countryOfLoad, loadingPlaceCode, weight, plannedDateTimeLoad, countryOfUnload
                    , unloadingPlaceCode,
                    plannedDateTimeUnload, notes);

            model.addAttribute("loadingId", loadingId);

        } catch (Exception e) {
            model.addAttribute("info", e.getLocalizedMessage());
            return "/loading/addLoading";
        }
        return "redirect:/listOfLoadings";
    }




    @GetMapping("/listOfLoadings")
    String listLoadings(Model model) {
        List<Loading> list = loadingService.listAllLoadingsByPlannedDate();

        model.addAttribute("listOfAllLoadings", list);

        return "loading/listOfLoadings";
    }

    @GetMapping("/loading/setLoad/{id}")
    String setLoadForm(@PathVariable Long id, Model model) {
        Loading loading = loadingService.findLoadingById(id);
        model.addAttribute("loading", loading);
        return "/loading/setLoad";
    }

    @PostMapping("/loading/setLoad/{id}")
    String setLoad(@PathVariable Long id,
                   @RequestParam(required = false) @DateTimeFormat(iso =
                           DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOfLoad,
                   @RequestParam(required = false) @DateTimeFormat(iso =
                           DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOfLoad,
                   @RequestParam(required = false) Double weight, Model model) {
        Loading loadingToSetLoad = loadingService.findLoadingById(id);

        try {
            loadingService.updateLoading(loadingToSetLoad, startOfLoad, endOfLoad, weight);
        } catch (Exception e) {
            model.addAttribute("loading", loadingToSetLoad);
            model.addAttribute("info", e.getLocalizedMessage());
            return "/loading/setLoad";
        }

        return "redirect:/listOfLoadings";
    }

    @GetMapping("/loading/setUnload/{id}")
    String setUnloadForm(@PathVariable Long id, Model model) {
        Loading loading = loadingService.findLoadingById(id);
        model.addAttribute("loading", loading);
        return "/loading/setUnload";
    }

    @PostMapping("/loading/setUnload/{id}")
    String setUnload(@PathVariable Long id, @RequestParam(required = false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOfUnload, @RequestParam(required =
            false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOfUnload, Model model) {

        Loading loadingToSetUnload = loadingService.findLoadingById(id);

        try {
            loadingService.updateUnloading(loadingToSetUnload, startOfUnload, endOfUnload);
        } catch (Exception e) {
            model.addAttribute("loading", loadingToSetUnload);
            model.addAttribute("info", e.getLocalizedMessage());
            return "/loading/setUnload";
        }

        return "redirect:/listOfLoadings";

    }

}
