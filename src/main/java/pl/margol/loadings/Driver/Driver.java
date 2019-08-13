package pl.margol.loadings.Driver;

import pl.margol.loadings.TruckSet.TruckSet;
import pl.margol.loadings.Utils.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "driverId")
    List<TruckSet> truckSetList;


    public Driver() {
    }

    public Driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = Status.AVAILABLE;
        this.truckSetList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<TruckSet> getTruckSetList() {
        return truckSetList;
    }

    public void setTruckSetList(List<TruckSet> truckSetList) {
        this.truckSetList = truckSetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) &&
                Objects.equals(firstName, driver.firstName) &&
                Objects.equals(lastName, driver.lastName) &&
                status == driver.status &&
                Objects.equals(truckSetList, driver.truckSetList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, status, truckSetList);
    }

    /*public String getCurrentTruckSet(){
        return truckSetList.stream()
                .sorted((s1,s2)-> s2.getId().compareTo(s1.getId()))
                .findFirst()
                .map(TruckSet::getName)
                .orElse("");
    }*/
    public String getCurrentTruckSet() {
        return truckSetList.stream()
                .filter(ts -> ts.getStatus().equals(Status.ACTIVE))
                .findFirst()
                .map(TruckSet::getName)
                .get();

    }
}
