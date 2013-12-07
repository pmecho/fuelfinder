package com.smpete.fuelfinder.provider;

import java.util.Date;

import android.database.Cursor;

/**
 * Cursor wrapper for the {@code station} table.
 */
public class StationCursorWrapper extends AbstractCursorWrapper {
    public StationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Long getId() {
        return getLongOrNull(StationColumns._ID);
    }

    public String getName() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.NAME);
        return getString(index);
    }

    public String getAddress() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.ADDRESS);
        return getString(index);
    }

    public String getCity() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.CITY);
        return getString(index);
    }

    public String getState() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.STATE);
        return getString(index);
    }

    public String getZip() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.ZIP);
        return getString(index);
    }

    public String getPhone() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.PHONE);
        return getString(index);
    }

    public String getAccessTime() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.ACCESS_TIME);
        return getString(index);
    }

    public String getGeocodeStatus() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.GEOCODE_STATUS);
        return getString(index);
    }

    public String getFuelType() {
        Integer index = getCachedColumnIndexOrThrow(StationColumns.FUEL_TYPE);
        return getString(index);
    }

    public Double getLatitude() {
        return getDoubleOrNull(StationColumns.LATITUDE);
    }

    public Double getLongitude() {
        return getDoubleOrNull(StationColumns.LONGITUDE);
    }

    public Date getUpdatedAt() {
        return getDateOrNull(StationColumns.UPDATED_AT);
    }
}
