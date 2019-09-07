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
        //Review: to rozwiązanie działa ale ma 2 wady. 1 -> jest nierozszerzalne pod względem walidacji
        // jak Customer miałby więcej warunków do spełnienia (na przykład wiek > 18) etc.
        // to miałbyś miałbyś i tutaj i w kontrolerze jakąś logikę warunkową żeby poprawny error message
        // pokazać w widoku. 2 -> w odpowiedzi klienta nie masz jak odnieść się do tego co utworzyłeś, nie
        // zawsze jest to wymagane ale w webowych serwisach często pojawia się taka potrzeba.
        //Jak zrobić to lepiej ? na if'e jak sprawdzasz warunek walidacyjny to zrzucaj wyjątek IllegalArgumentException
        // z jakimś tekstem w tym przypadku customer name already taken (czy tam po polsku).
        // natomiast w kontrolerze masz try catch blok i na catchu robisz logikę do pokazania widoku w momencie jak
        // walidacja nie przechodzi. Natomiast jeśli udało Ci się zapisać do bazy to lepiej zwracać id tego co zostało
        // stworzone wtedy w widoku jesteś bardziej elastyczny
        if (nameIsNotUnique(newCustomer)) {
            throw new IllegalArgumentException("Customer name must be unique");
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