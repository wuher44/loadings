package pl.margol.loadings.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Long created(Customer newCustomer) {
        if (nameIsNotUnique(newCustomer)) {
            throw new IllegalArgumentException("Customer already exist!!!!");
        }

        return customerRepository.save(newCustomer).getId();
    }

    private boolean nameIsNotUnique(Customer newCustomer) {
        return customerRepository.findAll()
            .stream()
            .anyMatch(e -> newCustomer.getName().equals(e.getName()));
    }

    public List<Customer> findAllByOrderByNameAsc() {
        return customerRepository.findAllByOrderByNameAsc();
    }
}