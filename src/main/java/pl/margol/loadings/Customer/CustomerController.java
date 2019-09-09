package pl.margol.loadings.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    //Review: brakuje private final
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/addCustomer")
    String addCustomerForm() {
        return "customer/addCustomer";
    }

    @PostMapping("/addCustomer")
    String addCustomer(@RequestParam String name, Model model) {
        Customer newCustomer = new Customer(name.toUpperCase());

        try {
            Long customerId = customerService.created(newCustomer);
            model.addAttribute("customerId", customerId);
            model.addAttribute("info", "Customer created!!!!");
            return "customer/addCustomer";
        } catch (Exception e) {
            model.addAttribute("info", e.getLocalizedMessage());
            return "customer/addCustomer";
        }
    }
}
