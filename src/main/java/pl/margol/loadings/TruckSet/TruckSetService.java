package pl.margol.loadings.TruckSet;

import org.springframework.stereotype.Service;
import pl.margol.loadings.Utils.Status;
import java.util.ArrayList;
import java.util.List;

@Service
public class TruckSetService {
    private TruckSetRepository truckSetRepository;

    public TruckSetService(TruckSetRepository truckSetRepository) {
        this.truckSetRepository = truckSetRepository;
    }

    boolean create(String truckPlate, String trailerPlate, Long driverId, String firstName,
                   String lastName, String company) {

        TruckSet truckSet = new TruckSet(truckPlate, trailerPlate, driverId, company);
        truckSet.setName(lastName + " " + firstName + " " + truckPlate + " " + trailerPlate);
        truckSet.setStatus(Status.ACTIVE);

        TruckSet created = truckSetRepository.save(truckSet);

        return created.getId() != null;

    }

    public List<TruckSet> listAll() {
        List<TruckSet> list = new ArrayList<>();
        list = truckSetRepository.findAll();
        return list;
    }

    public TruckSet findTruckSet(Long id) {
        TruckSet truckSet = truckSetRepository.findById(id).get();
        return truckSet;
    }

    public boolean edit(Long id, Status status) {
        TruckSet truckSetToEdit = findTruckSet(id);
        truckSetToEdit.setStatus(status);
        truckSetRepository.save(truckSetToEdit);
        return findTruckSet(id).getStatus().equals(status);
    }
}
