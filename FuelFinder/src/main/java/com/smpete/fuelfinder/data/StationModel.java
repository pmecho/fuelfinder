package com.smpete.fuelfinder.data;

import java.util.Date;

public class StationModel {

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
    String fuel_type;
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

    public String getFuelType() {
        return fuel_type;
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
}
