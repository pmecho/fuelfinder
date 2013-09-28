package com.smpete.fuelfinder.network;

import android.util.Log;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public enum NrelController {
    INSTANCE;

    private static final String API_KEY = "b50658292b2e83e2c5d4ad45291cdf2f7f76a920";
    private static final String BASE_URL = "http://developer.nrel.gov";
    private NrelService mService;

    private NrelController() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer(BASE_URL)
                .build();

        mService = restAdapter.create(NrelService.class);
    }

    public void getNearbyStations() {
        mService.listStations(API_KEY, "80218", new Callback<FuelStation>() {
            @Override
            public void success(FuelStation fuelStation, Response response) {
                for (Station station : fuelStation.fuel_stations) {
                    Log.d("XXX", station.street_address);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.v("XXX", "fail");
            }
        });

    }
    public void getAllStations() {
        mService.listAllStations(API_KEY, new Callback<FuelStation>() {
            @Override
            public void success(FuelStation fuelStation, Response response) {
                for (Station station : fuelStation.fuel_stations) {
                    Log.d("XXX", station.street_address);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.v("XXX", "fail");
            }
        });

    }



    public interface NrelService {
        @GET("/api/alt-fuel-stations/v1/nearest.json")
        void listStations(@Query("api_key") String apiKey,
                          @Query("location") String locationString,
                          Callback<FuelStation> callback);

        @GET("/api/alt-fuel-stations/v1.json")
        void listAllStations(@Query("api_key") String apiKey,
                             Callback<FuelStation> callback);
    }

    public static class FuelStation {
        List<Station> fuel_stations;
    }

    public static class Station {
        String street_address;
    }
}
