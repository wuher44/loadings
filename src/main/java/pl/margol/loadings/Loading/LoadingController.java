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
import java.util.Arrays;
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
        List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

        List<Customer> customers = customerService.findAll();
        customers.sort(Comparator.comparing(Customer::getName));
        model.addAttribute("truckSetList", truckSetService.listAll());
        model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        model.addAttribute("customersList", customers);
        model.addAttribute("adrCodes", adrCodes);
        model.addAttribute("countries", countries);
        return "/loading/addLoading";
    }

    @PostMapping("/addLoading")
    String createLoading(@RequestParam Long truckSetId, @RequestParam String customerName,
                         @RequestParam String adr,
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
        int priceInt;
        double weightDouble;

        try {
            priceInt = Integer.parseInt(price);
        } catch (Exception e) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Add correct price!!!!");
            return "/loading/addLoading";
        }

        try {
            weightDouble = Double.parseDouble(weight);
        } catch (Exception e) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Add correct weight!!!!");
            return "/loading/addLoading";
        }


        if (priceInt <= 0) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Add correct price!!!!");
            return "/loading/addLoading";
        }
        if (loadingPlaceCode.isEmpty() || unloadingPlaceCode.isEmpty()) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Add postcode!!!!");
            return "/loading/addLoading";
        }
        if (weightDouble <= 0) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Add correct weight!!!!");
            return "/loading/addLoading";
        }

        if (plannedDateTimeLoad.isAfter(plannedDateTimeUnload)) {
            List<String> adrCodes = Arrays.asList("-", "1", "2", "3", "4", "5", "6", "7", "8", "9");

            List<String> countries = Arrays.asList("AT", "B", "BG", "CZ", "D", "DK", "EST", "ESP",
                    "HR", "HU", "IT", "LT", "LV", "NL", "P", "PL", "RO", "SK", "SLO", "SWE");

            List<Customer> customers = customerService.findAll();
            customers.sort(Comparator.comparing(Customer::getName));
            model.addAttribute("truckSetList", truckSetService.listAll());
            model.addAttribute("dateTimeNow", LocalDateTime.of(LocalDate.now(), LocalTime.of(8,
                    0)));
            model.addAttribute("customersList", customers);
            model.addAttribute("adrCodes", adrCodes);
            model.addAttribute("countries", countries);
            model.addAttribute("info", "Date of load cannot be after date of unload!!!!");
            return "/loading/addLoading";
        }


        Loading newLoading = new Loading(truckSetId, customerName, adr, priceInt, currency,
                countryOfLoad, loadingPlaceCode,
                weightDouble, plannedDateTimeLoad, countryOfUnload, unloadingPlaceCode,
                plannedDateTimeUnload, notes);

        boolean result = loadingService.createLoading(newLoading);

        return "redirect:/listOfLoadings";

    }

    @GetMapping("/listOfLoadings")
    String listLoadings(Model model) {
        List<Loading> list = loadingService.listAllLoadings();
        list.sort((o1, o2) -> o2.getPlannedDateAndTimeOfLoad().compareTo(o1.getPlannedDateAndTimeOfLoad()));

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
    String setLoad(@PathVariable Long id, @RequestParam(required = false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime startOfLoad, @RequestParam(required =
            false) @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE_TIME) LocalDateTime endOfLoad,
                   @RequestParam(required = false) String weight, Model model) {

        Loading loadingToSetLoad = loadingService.findLoadingById(id);

        loadingToSetLoad.setStartOfLoad(startOfLoad);

        if (endOfLoad != null) {
            if (loadingToSetLoad.getStartOfLoad() != null) {
                if (endOfLoad.isBefore(loadingToSetLoad.getStartOfLoad())) {
                    model.addAttribute("loading", loadingToSetLoad);
                    model.addAttribute("info", "End cannot be before start!!!!");
                    return "/loading/setLoad";
                }
                loadingToSetLoad.setEndOfLoad(endOfLoad);
            } else {
                model.addAttribute("loading", loadingToSetLoad);
                model.addAttribute("info", "Add start first!!!!");
                return "/loading/setLoad";
            }
        }
        if (weight != null && !weight.isEmpty()) {
            if (loadingToSetLoad.getEndOfLoad() != null && loadingToSetLoad.getStartOfLoad() != null) {
                loadingToSetLoad.setLoadedWeight(Double.parseDouble(weight));
            } else {
                model.addAttribute("loading", loadingToSetLoad);
                model.addAttribute("info", "Add start and end first!!!!");
                return "/loading/setLoad";
            }
        }

        if (loadingToSetLoad.getStartOfLoad() != null && loadingToSetLoad.getEndOfLoad() != null) {
            loadingToSetLoad.setStatus(Status.LOADED);
        }

        loadingService.createLoading(loadingToSetLoad);

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

        if (startOfUnload.isBefore(loadingToSetUnload.getEndOfLoad())) {
            model.addAttribute("loading", loadingToSetUnload);
            model.addAttribute("info", "Unload cannot be before load!!!!");
            return "/loading/setUnload";
        }

        loadingToSetUnload.setStartOfUnload(startOfUnload);

        if (endOfUnload != null) {
            if (loadingToSetUnload.getStartOfUnload() != null) {
                if (endOfUnload.isBefore(loadingToSetUnload.getStartOfUnload())) {
                    model.addAttribute("loading", loadingToSetUnload);
                    model.addAttribute("info", "End cannot be before start!!!!");
                    return "/loading/setUnload";
                }
                loadingToSetUnload.setEndOfUnload(endOfUnload);
            } else {
                model.addAttribute("loading", loadingToSetUnload);
                model.addAttribute("info", "Add start first!!!!");
                return "/loading/setUnload";
            }
        }


        if (loadingToSetUnload.getStartOfUnload() != null && loadingToSetUnload.getEndOfUnload() != null) {
            loadingToSetUnload.setStatus(Status.COMPLETED);
        }

        loadingService.createLoading(loadingToSetUnload);

        return "redirect:/listOfLoadings";

    }

}
