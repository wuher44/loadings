package pl.margol.loadings.TruckSet;

import pl.margol.loadings.Loading.Loading;
import pl.margol.loadings.Utils.Status;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TruckSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String truckPlate;
    private String trailerPlate;
    private Long driverId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "truckSetId")
    private List<Loading> loadingList;


    public TruckSet() {
    }

    public TruckSet(String truckPlate, String trailerPlate, Long driverId) {
        this.truckPlate = truckPlate;
        this.trailerPlate = trailerPlate;
        this.driverId = driverId;
        this.name = null;
        this.status = Status.ACTIVE;
        this.loadingList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTruckPlate() {
        return truckPlate;
    }

    public void setTruckPlate(String truckPlate) {
        this.truckPlate = truckPlate;
    }

    public String getTrailerPlate() {
        return trailerPlate;
    }

    public void setTrailerPlate(String trailerPlate) {
        this.trailerPlate = trailerPlate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Loading> getLoadingList() {
        return loadingList;
    }

    public void setLoadingList(List<Loading> loadingList) {
        this.loadingList = loadingList;
    }


}
