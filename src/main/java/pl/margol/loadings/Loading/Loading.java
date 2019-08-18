package pl.margol.loadings.Loading;

import pl.margol.loadings.Utils.Status;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Loading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long truckSetId;
    private String customer;
    private String adr;
    private int price;
    private String currency;
    private String countryOfLoad;
    private String loadingPlaceCode;
    private double plannedWeight;
    private LocalDateTime plannedDateAndTimeOfLoad;
    private LocalDateTime startOfLoad;
    private LocalDateTime endOfLoad;
    private String countryOfUnload;
    private String unloadingPlaceCode;
    private LocalDateTime plannedDateAndTimeOfUnload;
    private LocalDateTime startOfUnload;
    private LocalDateTime endOfUnload;
    private Double loadedWeight;
    private String notes;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Loading() {
    }

    public Loading(Long truckSetId, String customer, String adr, int price, String currency,
                   String countryOfLoad, String loadingPlaceCode, double plannedWeight,
                   LocalDateTime plannedDateAndTimeOfLoad, String countryOfUnload,
                   String unloadingPlaceCode, LocalDateTime plannedDateAndTimeOfUnload,
                   String notes) {
        this.truckSetId = truckSetId;
        this.customer = customer;
        this.adr = adr;
        this.price = price;
        this.currency = currency;
        this.countryOfLoad = countryOfLoad;
        this.loadingPlaceCode = loadingPlaceCode;
        this.plannedWeight = plannedWeight;
        this.plannedDateAndTimeOfLoad = plannedDateAndTimeOfLoad;
        this.startOfLoad = null;
        this.endOfLoad = null;
        this.countryOfUnload = countryOfUnload;
        this.unloadingPlaceCode = unloadingPlaceCode;
        this.plannedDateAndTimeOfUnload = plannedDateAndTimeOfUnload;
        this.startOfUnload = null;
        this.endOfUnload = null;
        this.loadedWeight = null;
        this.notes = notes;
        this.status = Status.WAITING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTruckSetId() {
        return truckSetId;
    }

    public void setTruckSetId(Long truckSetId) {
        this.truckSetId = truckSetId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountryOfLoad() {
        return countryOfLoad;
    }

    public void setCountryOfLoad(String countryOfLoad) {
        this.countryOfLoad = countryOfLoad;
    }

    public String getLoadingPlaceCode() {
        return loadingPlaceCode;
    }

    public void setLoadingPlaceCode(String loadingPlaceCode) {
        this.loadingPlaceCode = loadingPlaceCode;
    }

    public double getPlannedWeight() {
        return plannedWeight;
    }

    public void setPlannedWeight(double plannedWeight) {
        this.plannedWeight = plannedWeight;
    }

    public LocalDateTime getPlannedDateAndTimeOfLoad() {
        return plannedDateAndTimeOfLoad;
    }

    public void setPlannedDateAndTimeOfLoad(LocalDateTime plannedDateAndTimeOfLoad) {
        this.plannedDateAndTimeOfLoad = plannedDateAndTimeOfLoad;
    }

    public LocalDateTime getStartOfLoad() {
        return startOfLoad;
    }

    public void setStartOfLoad(LocalDateTime startOfLoad) {
        this.startOfLoad = startOfLoad;
    }

    public LocalDateTime getEndOfLoad() {
        return endOfLoad;
    }

    public void setEndOfLoad(LocalDateTime endOfLoad) {
        this.endOfLoad = endOfLoad;
    }

    public String getCountryOfUnload() {
        return countryOfUnload;
    }

    public void setCountryOfUnload(String countryOfUnload) {
        this.countryOfUnload = countryOfUnload;
    }

    public String getUnloadingPlaceCode() {
        return unloadingPlaceCode;
    }

    public void setUnloadingPlaceCode(String unloadingPlaceCode) {
        this.unloadingPlaceCode = unloadingPlaceCode;
    }

    public LocalDateTime getPlannedDateAndTimeOfUnload() {
        return plannedDateAndTimeOfUnload;
    }

    public void setPlannedDateAndTimeOfUnload(LocalDateTime plannedDateAndTimeOfUnload) {

        this.plannedDateAndTimeOfUnload = plannedDateAndTimeOfUnload;
    }

    public LocalDateTime getStartOfUnload() {
        return startOfUnload;
    }

    public void setStartOfUnload(LocalDateTime startOfUnload) {
        this.startOfUnload = startOfUnload;
    }

    public LocalDateTime getEndOfUnload() {
        return endOfUnload;
    }

    public void setEndOfUnload(LocalDateTime endOfUnload) {
        this.endOfUnload = endOfUnload;
    }

    public Double getLoadedWeight() {
        return loadedWeight;
    }

    public void setLoadedWeight(Double loadedWeight) {
        this.loadedWeight = loadedWeight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
