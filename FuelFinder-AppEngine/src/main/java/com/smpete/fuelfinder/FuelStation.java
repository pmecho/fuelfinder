package com.smpete.fuelfinder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class FuelStation {

    @Id
    private int id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String hours;
    private float latitude;
    private float longitude;
    private Date nrelUpdatedAt;

    public FuelStation(NrelController.StationModel model) {
        id = model.id;
        name = model.station_name;
        address = model.street_address;
        city = model.city;
        state = model.state;
        zip = model.zip;
        hours = model.access_days_time;
        latitude = model.latitude;
        longitude = model.longitude;
        nrelUpdatedAt = model.updated_at;


    }
}
