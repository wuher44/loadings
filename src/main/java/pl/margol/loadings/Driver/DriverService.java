package pl.margol.loadings.Driver;

import org.springframework.stereotype.Service;
import pl.margol.loadings.Utils.Status;

import java.util.List;

@Service
public class DriverService {
    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> listAllDrivers() {
        return driverRepository.findAll();
    }

   public boolean create(String firstName, String lastName) {

        Driver driver = new Driver(firstName, lastName);

        Driver created = driverRepository.save(driver);

        return created.getId() != null;

    }

    public Driver findDriver(long id) {
        Driver driver = driverRepository.findById(id).get();
        return driver;
    }

    public void deleteDriver(long id) {
        driverRepository.deleteById(id);
    }

    public boolean edit(Long id, String firstName, String lastName, Status status) {
        Driver edited = driverRepository.findById(id).get();
        edited.setFirstName(firstName);
        edited.setLastName(lastName);
        edited.setStatus(status);
        driverRepository.save(edited);

        return edited.getFirstName().equals(firstName) && edited.getLastName().equals(lastName) && edited.getStatus() == status;

    }
    public void editStatus(Long id, Status status){
        Driver edited = driverRepository.findById(id).get();
        edited.setStatus(status);
        driverRepository.save(edited);
    }

    public List<Driver> searchDriverByPhrase(String phrase) {
        List<Driver> list = driverRepository.searchDriverByPhrase(phrase);
        return list;
    }
    public List<Driver> searchDriverByName(String firstname, String lastName) {
        List<Driver> list = driverRepository.searchDriverByName(firstname, lastName);
        return list;
    }
}
