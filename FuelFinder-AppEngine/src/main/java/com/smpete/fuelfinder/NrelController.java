package com.smpete.fuelfinder;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;
import java.util.logging.Logger;

@Api(name = "nrelcontroller", namespace = @ApiNamespace(ownerDomain = "smpete.com", ownerName = "smpete.com", packagePath = "fuelfinder"))
public class NrelController {

    private static final Logger log = Logger.getLogger(NrelController.class.getName());

    private static final String API_KEY = "b50658292b2e83e2c5d4ad45291cdf2f7f76a920";
    private static final String BASE_URL = "http://developer.nrel.gov";
    private NrelService mService;

    public NrelController() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer(BASE_URL)
                .build();

        mService = restAdapter.create(NrelService.class);
    }

    @ApiMethod(name = "getNearbyStations")
    public void getNearbyStations() {
        FuelStationResult result = mService.listStations(API_KEY, "80218");
        for (Station station : result.fuel_stations) {
            log.info(station.street_address);
        }
    }

//    @ApiMethod(name = "getAllStations")
    private void getAllStations() {
        mService.listAllStations(API_KEY, new Callback<FuelStationResult>() {
            @Override
            public void success(FuelStationResult fuelStation, Response response) {
                for (Station station : fuelStation.fuel_stations) {
                    log.info(station.street_address);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                log.info("fail");
            }
        });

    }



    private interface NrelService {
        @GET("/api/alt-fuel-stations/v1/nearest.json")
        FuelStationResult listStations(@Query("api_key") String apiKey,
                          @Query("location") String locationString);

        @GET("/api/alt-fuel-stations/v1.json")
        void listAllStations(@Query("api_key") String apiKey,
                             Callback<FuelStationResult> callback);
    }

    public static class FuelStationResult {
        List<Station> fuel_stations;
    }

    public static class Station {
        String street_address;
    }
}
