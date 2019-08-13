package pl.margol.loadings.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/addCustomer")
    String addCustomerForm() {
        return "customer/addCustomer";
    }

    @PostMapping("/addCustomer")
    String addCustomer(@RequestParam String customer, Model model) {
        Customer newCustomer = new Customer(customer.toUpperCase());

        boolean result = customerService.created(newCustomer);
        if (!result) {
            model.addAttribute("info", "Customer already exist!!!!");
            return "customer/addCustomer";
        } else {
            model.addAttribute("info", "Customer created!!!!");
            return "customer/addCustomer";
        }


    }
}
