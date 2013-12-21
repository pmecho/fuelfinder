package com.smpete.fuelfinder.data.provider;

import android.content.ContentValues;

import java.util.Date;

public class StationContentValues {

    private final ContentValues mContentValues = new ContentValues();

    public ContentValues getContentValues() {
        return mContentValues;
    }

    public void putId(int value) {
        mContentValues.put(StationColumns._ID, value);
    }

    public void putName(String value) {
        mContentValues.put(StationColumns.NAME, value);
    }

    public void putAddress(String value) {
        mContentValues.put(StationColumns.ADDRESS, value);
    }

    public void putCity(String value) {
        mContentValues.put(StationColumns.CITY, value);
    }

    public void putState(String value) {
        mContentValues.put(StationColumns.STATE, value);
    }

    public void putZip(String value) {
        mContentValues.put(StationColumns.ZIP, value);
    }

    public void putPhone(String value) {
        mContentValues.put(StationColumns.PHONE, value);
    }

    public void putAccessTime(String value) {
        mContentValues.put(StationColumns.ACCESS_TIME, value);
    }

    public void putGeocodeStatus(String value) {
        mContentValues.put(StationColumns.GEOCODE_STATUS, value);
    }

    public void putFuelTypes(String value) {
        mContentValues.put(StationColumns.FUEL_TYPES, value);
    }

    public void putLatitude(Double value) {
        mContentValues.put(StationColumns.LATITUDE, value);
    }

    public void putLongitude(Double value) {
        mContentValues.put(StationColumns.LONGITUDE, value);
    }

    public void putUpdatedAt(Date value) {
        mContentValues.put(StationColumns.UPDATED_AT, value.getTime());
    }
}
