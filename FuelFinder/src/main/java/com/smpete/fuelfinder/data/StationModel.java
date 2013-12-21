package com.smpete.fuelfinder.data;

import android.database.Cursor;
import com.smpete.fuelfinder.data.provider.StationColumns;
import com.smpete.fuelfinder.utils.CursorCreator;

import java.util.Date;

public class StationModel implements CursorCreator<StationModel> {

    // Check here for docs: http://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/all/
    // TODO: Convert those to javadocs?

    int id;
    String station_name;
    String street_address;
    String city;
    String state;
    String zip;
    String station_phone;
    String access_days_time;
    String geocode_status;
    String fuel_type_code;
    float latitude;
    float longitude;
    Date updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return station_name;
    }

    public String getStreetAddress() {
        return street_address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return station_phone;
    }

    public String getAccessDaysTime() {
        return access_days_time;
    }

    public String getGeocodeStatus() {
        return geocode_status;
    }

    public String getFuelTypes() {
        return fuel_type_code;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    @Override
    public StationModel createFromCursor(Cursor c) {
        StationModel model = new StationModel();

        // TODO: Cache indicies?
        model.id = c.getInt(c.getColumnIndex(StationColumns._ID));
        model.station_name = c.getString(c.getColumnIndex(StationColumns.NAME));
        model.street_address = c.getString(c.getColumnIndex(StationColumns.ADDRESS));
        model.city = c.getString(c.getColumnIndex(StationColumns.CITY));
        model.state = c.getString(c.getColumnIndex(StationColumns.STATE));
        model.zip = c.getString(c.getColumnIndex(StationColumns.ZIP));
        model.station_phone = c.getString(c.getColumnIndex(StationColumns.PHONE));
        model.access_days_time = c.getString(c.getColumnIndex(StationColumns.ACCESS_TIME));
        model.geocode_status = c.getString(c.getColumnIndex(StationColumns.GEOCODE_STATUS));
        model.fuel_type_code = c.getString(c.getColumnIndex(StationColumns.FUEL_TYPES));
        model.latitude = c.getFloat(c.getColumnIndex(StationColumns.LATITUDE));
        model.longitude = c.getFloat(c.getColumnIndex(StationColumns.LONGITUDE));
        model.updated_at = new Date(c.getLong(c.getColumnIndex(StationColumns.UPDATED_AT)));
        return model;
    }
}
