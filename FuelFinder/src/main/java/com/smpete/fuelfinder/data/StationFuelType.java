package com.smpete.fuelfinder.data;

public class StationFuelType {

    private long id;
    private long stationId;
    private long fuelTypeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public long getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }
}
