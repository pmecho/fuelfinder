package com.smpete.fuelfinder.provider;

import java.util.Date;

/**
 * Selection for the {@code station} table.
 */
public class StationSelection extends AbstractSelection<StationSelection> {
    public StationSelection id(Long... value) {
        addEquals(StationColumns._ID, (Object[]) value);
        return this;
    }


    public StationSelection name(String... value) {
        addEquals(StationColumns.NAME, (Object[]) value);
        return this;
    }


    public StationSelection address(String... value) {
        addEquals(StationColumns.ADDRESS, (Object[]) value);
        return this;
    }


    public StationSelection city(String... value) {
        addEquals(StationColumns.CITY, (Object[]) value);
        return this;
    }


    public StationSelection state(String... value) {
        addEquals(StationColumns.STATE, (Object[]) value);
        return this;
    }


    public StationSelection zip(String... value) {
        addEquals(StationColumns.ZIP, (Object[]) value);
        return this;
    }


    public StationSelection phone(String... value) {
        addEquals(StationColumns.PHONE, (Object[]) value);
        return this;
    }


    public StationSelection accessTime(String... value) {
        addEquals(StationColumns.ACCESS_TIME, (Object[]) value);
        return this;
    }


    public StationSelection geocodeStatus(String... value) {
        addEquals(StationColumns.GEOCODE_STATUS, (Object[]) value);
        return this;
    }


    public StationSelection fuelType(String... value) {
        addEquals(StationColumns.FUEL_TYPE, (Object[]) value);
        return this;
    }


    public StationSelection latitude(Double... value) {
        addEquals(StationColumns.LATITUDE, (Object[]) value);
        return this;
    }

    public StationSelection latitudeGt(double value) {
        addGreaterThan(StationColumns.LATITUDE, value);
        return this;
    }

    public StationSelection latitudeLt(double value) {
        addLessThan(StationColumns.LATITUDE, value);
        return this;
    }

    public StationSelection longitude(Double... value) {
        addEquals(StationColumns.LONGITUDE, (Object[]) value);
        return this;
    }

    public StationSelection longitudeGt(double value) {
        addGreaterThan(StationColumns.LONGITUDE, value);
        return this;
    }

    public StationSelection longitudeLt(double value) {
        addLessThan(StationColumns.LONGITUDE, value);
        return this;
    }

    public StationSelection updatedAt(Date... value) {
        addEquals(StationColumns.UPDATED_AT, (Object[]) value);
        return this;
    }

    public StationSelection updatedAt(Long... value) {
        addEquals(StationColumns.UPDATED_AT, (Object[]) value);
        return this;
    }
}
