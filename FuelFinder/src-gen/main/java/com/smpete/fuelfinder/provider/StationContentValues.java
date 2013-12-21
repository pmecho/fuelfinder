package com.smpete.fuelfinder.provider;

import java.util.Date;

/**
 * Content values wrapper for the {@code station} table.
 */
public class StationContentValues extends AbstractContentValuesWrapper {

    public void putId(int value) {
        mContentValues.put(StationColumns._ID, value);
    }

    public void putName(String value) {
        mContentValues.put(StationColumns.NAME, value);
    }

    public void putNameNull() {
        mContentValues.putNull(StationColumns.NAME);
    }


    public void putAddress(String value) {
        mContentValues.put(StationColumns.ADDRESS, value);
    }

    public void putAddressNull() {
        mContentValues.putNull(StationColumns.ADDRESS);
    }


    public void putCity(String value) {
        mContentValues.put(StationColumns.CITY, value);
    }

    public void putCityNull() {
        mContentValues.putNull(StationColumns.CITY);
    }


    public void putState(String value) {
        mContentValues.put(StationColumns.STATE, value);
    }

    public void putStateNull() {
        mContentValues.putNull(StationColumns.STATE);
    }


    public void putZip(String value) {
        mContentValues.put(StationColumns.ZIP, value);
    }

    public void putZipNull() {
        mContentValues.putNull(StationColumns.ZIP);
    }


    public void putPhone(String value) {
        mContentValues.put(StationColumns.PHONE, value);
    }

    public void putPhoneNull() {
        mContentValues.putNull(StationColumns.PHONE);
    }


    public void putAccessTime(String value) {
        mContentValues.put(StationColumns.ACCESS_TIME, value);
    }

    public void putAccessTimeNull() {
        mContentValues.putNull(StationColumns.ACCESS_TIME);
    }


    public void putGeocodeStatus(String value) {
        mContentValues.put(StationColumns.GEOCODE_STATUS, value);
    }

    public void putGeocodeStatusNull() {
        mContentValues.putNull(StationColumns.GEOCODE_STATUS);
    }


    public void putFuelType(String value) {
        mContentValues.put(StationColumns.FUEL_TYPE, value);
    }

    public void putFuelTypeNull() {
        mContentValues.putNull(StationColumns.FUEL_TYPE);
    }


    public void putLatitude(Double value) {
        mContentValues.put(StationColumns.LATITUDE, value);
    }

    public void putLatitudeNull() {
        mContentValues.putNull(StationColumns.LATITUDE);
    }


    public void putLongitude(Double value) {
        mContentValues.put(StationColumns.LONGITUDE, value);
    }

    public void putLongitudeNull() {
        mContentValues.putNull(StationColumns.LONGITUDE);
    }


    public void putUpdatedAt(Date value) {
        mContentValues.put(StationColumns.UPDATED_AT, value.getTime());
    }

    public void putUpdatedAtNull() {
        mContentValues.putNull(StationColumns.UPDATED_AT);
    }

    public void putUpdatedAt(Long value) {
        mContentValues.put(StationColumns.UPDATED_AT, value);
    }

}
