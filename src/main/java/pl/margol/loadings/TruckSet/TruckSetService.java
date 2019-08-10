package pl.margol.loadings.TruckSet;

import org.springframework.stereotype.Service;
import pl.margol.loadings.Utils.Status;

@Service
public class TruckSetService {
    private TruckSetRepository truckSetRepository;

    public TruckSetService(TruckSetRepository truckSetRepository) {
        this.truckSetRepository = truckSetRepository;
    }
    boolean create(String truckPlate, String trailerPlate, Long driverId, String firstName, String lastName) {

        TruckSet truckSet = new TruckSet(truckPlate, trailerPlate, driverId);
        truckSet.setName(truckPlate+" "+trailerPlate+" "+firstName+" "+lastName);
        truckSet.setStatus(Status.ACTIVE);

        TruckSet created = truckSetRepository.save(truckSet);

        return created.getId() != null;

    }
}
