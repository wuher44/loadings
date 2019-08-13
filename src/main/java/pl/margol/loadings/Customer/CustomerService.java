package pl.margol.loadings.Customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public boolean created(Customer newCustomer) {
        if (customerRepository.findAll().stream().anyMatch(e -> newCustomer.getName().equals(e.getName()))) {
            return false;
        } else {
            Customer created = customerRepository.save(newCustomer);
            return true;

        }
    }
}