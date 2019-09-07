package pl.margol.loadings.Driver;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import pl.margol.loadings.Utils.Status;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> listAllDrivers() {
        return driverRepository.findAll();
    }

    public List<Driver> listAllDriversSortedByLastName() {
        return driverRepository.findAllByOrderByLastnNameAsc();
    }

    public Long create(String firstName, String lastName) {
        Driver driver = new Driver(firstName, lastName);
        if (doesDriverExist(firstName, lastName)) {
            throw new IllegalArgumentException("firstName and lastName must be unique");
        }

        return driverRepository.save(driver).getId();
    }

    public Driver findDriver(long id) {
        return getDriver(id);
    }

    void deleteDriver(long id) {
        driverRepository.deleteById(id);
    }

    public boolean edit(Long id, String firstName, String lastName, Status status) {
        Driver edited = getDriver(id);
        edited.setFirstName(firstName);
        edited.setLastName(lastName);
        edited.setStatus(status);

        return driverRepository.save(edited).getId() != null;
    }

    public void editStatus(Long id, Status status) {
        Driver edited = getDriver(id);
        edited.setStatus(status);
        driverRepository.save(edited);
    }

    private Driver getDriver(Long id) {
        return driverRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
    }

    List<Driver> searchDriverByPhrase(String phrase) {
        return driverRepository.searchDriverByPhrase(phrase);
    }

    private boolean doesDriverExist(String firstname, String lastName) {
        return driverRepository.countByFirstNameAndLastName(firstname, lastName) >= 0;
    }

    List<Driver> findDrivers(String phrase, String show) {
        if (StringUtils.isEmpty(phrase) && show.equals("all")) {
            return driverRepository.findAll();
        } else if (!StringUtils.isEmpty(phrase) && show.equals("all")) {
            return driverRepository.searchDriverByPhrase(phrase);
        } else if (!StringUtils.isEmpty(phrase) && !show.equals("all")) {
            return driverRepository.searchByPhraseAndStatus(phrase, toStatus(show));
        } else {
            return driverRepository.findAllByStatus(toStatus(show));
        }
    }

    private Status toStatus(String show) {
        return Status.valueOf(show.toUpperCase());
    }
}
