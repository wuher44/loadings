package pl.margol.loadings.Loading;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pl.margol.loadings.Utils.Status;

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

    public void updateLoadingDateTime(LocalDateTime startOfLoad, LocalDateTime endOfLoad) {
        if (startOfLoad != null && endOfLoad != null && startOfLoad.isBefore(endOfLoad)) {
            this.startOfLoad = startOfLoad;
            this.endOfLoad = endOfLoad;
        }
        if (endOfLoad != null && (startOfLoad == null || startOfLoad.isAfter(endOfLoad))) {
            throw new IllegalArgumentException("Invalid values. startOfLoad must not be null nor " +
                    "after endOfLoad");
        }
    }

    public void updateUnloadingDateTime(LocalDateTime startOfUnload, LocalDateTime endOfUnload) {
        if (startOfUnload != null && endOfUnload != null && startOfUnload.isBefore(endOfUnload)) {
            this.startOfUnload = startOfUnload;
            this.endOfUnload = endOfUnload;
        }
        if (endOfUnload != null && (startOfUnload == null || startOfUnload.isAfter(endOfUnload))) {
            throw new IllegalArgumentException("Invalid values. startOfUnload must not be null " +
                    "nor after endOfUnload");
        }
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


    public void updateWeight(Double weight) {
        if (startOfLoad == null && endOfLoad == null || weight <= 0) {
            throw new IllegalArgumentException("Weight must be > 0 and startOfLoad and endOfLoad " +
                    "must be set");
        }

        this.loadedWeight = weight;
    }
}
