package pl.margol.loadings.Loading;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.margol.loadings.Customer.Customer;
import pl.margol.loadings.Customer.CustomerService;
import pl.margol.loadings.TruckSet.TruckSet;
import pl.margol.loadings.TruckSet.TruckSetService;

@Controller
public class LoadingController {

    private static final List<String> ADR_CODES = Arrays.asList("-", "1", "2", "3", "4.1", "4.2",
            "4.3", "5.1", "5.2", "6.1", "6.2", "7", "8", "9", "KAT1", "KAT2", "KAT3");
    private static final List<String> COUNTRIES = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK",
            "EST", "ESP", "F", "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE"
            , "UA");
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
        List<Loading> list = loadingService.listAllLoadingsByPlannedDate().stream()
                .filter(loading -> loading.getPlannedDateAndTimeOfLoad().isAfter(LocalDateTime.now().minusMonths(1l)))
                .collect(Collectors.toList());


        List<TruckSet> truckSetList = truckSetService.listAll();
        List<Customer> customers = customerService.findAllByOrderByNameAsc();
        model.addAttribute("listOfAllLoadings", list);
        model.addAttribute("truckSetList", truckSetList);
        model.addAttribute("customersList", customers);
        model.addAttribute("countries", COUNTRIES);
        return "loading/listOfLoadings";
    }



    @PostMapping("/listOfLoadings")
    String listOfLoadingsFilter(String customerName, String loadingPlace, String unloadingPlace,
                                @RequestParam(required = false) String from,
                                @RequestParam(required = false) String to, Model model) {

        List<Loading> list = loadingService.listAllLoadingsByPlannedDate().stream()
                .filter(l -> !customerName.isEmpty() == l.getCustomer().equals(customerName))
                .filter(l -> !loadingPlace.isEmpty() == l.getCountryOfLoad().equals(loadingPlace))
                .filter(l -> !unloadingPlace.isEmpty() == l.getCountryOfUnload().equals(unloadingPlace))
                .filter(l -> l.getPlannedDateAndTimeOfLoad().toLocalDate().isAfter(from.isEmpty() ? LocalDate.MIN : LocalDate.parse(from)))
                .filter(l -> l.getPlannedDateAndTimeOfLoad().toLocalDate().isBefore(to.isEmpty()
                        ? LocalDate.MAX : LocalDate.parse(to)))
                .collect(Collectors.toList());

        List<TruckSet> truckSetList = truckSetService.listAll();
        List<Customer> customers = customerService.findAllByOrderByNameAsc();
        model.addAttribute("listOfAllLoadings", list);
        model.addAttribute("truckSetList", truckSetList);
        model.addAttribute("customersList", customers);
        model.addAttribute("countries", COUNTRIES);
        return "loading/listOfLoadings";
    }
    @GetMapping("/stats")
    String stats(Model model) {
        List<Integer> years = loadingService.searchDistinctYearsOfLoadings();
        model.addAttribute("years", years);
        model.addAttribute("selectedYear", null);
        return "stats";
    }
    @PostMapping("/stats")
    String stats(@RequestParam Integer year, Model model) {
        Map<String, Double> eraTransStats = loadingService.listAllLoadingsByPlannedDate().stream()
                .filter(loading -> loading.getEndOfLoad()!= null)
                .filter(loading -> loading.getEndOfLoad().getYear() == year)
                .filter(loading -> truckSetService.findTruckSet(loading.getTruckSetId()).getCompany().equals("ERA"))
                .filter(loading -> loading.getLoadedWeight() != null)
                .collect(Collectors.groupingBy(Loading::getAdr, Collectors.summingDouble(Loading::getLoadedWeight)));


        Map<String, Double> mirpolTransStats = loadingService.listAllLoadingsByPlannedDate().stream()
                .filter(loading -> loading.getEndOfLoad()!= null)
                .filter(loading -> loading.getEndOfLoad().getYear() == year)
                .filter(loading -> truckSetService.findTruckSet(loading.getTruckSetId()).getCompany().equals("MIRPOL"))
                .filter(loading -> loading.getLoadedWeight() != null)
                .collect(Collectors.groupingBy(Loading::getAdr, Collectors.summingDouble(Loading::getLoadedWeight)));


        List<Integer> years = loadingService.searchDistinctYearsOfLoadings();

        model.addAttribute("years", years);
        model.addAttribute("eraStats", eraTransStats);
        model.addAttribute("mirpolStats", mirpolTransStats);
        model.addAttribute("selectedYear", year);

        return "stats";
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

    @GetMapping("/loading/delete/{id}")
    String deleteLoad(@PathVariable Long id, Model model) {
        loadingService.deleteloading(id);
        return "redirect:/listOfLoadings";
    }

}
