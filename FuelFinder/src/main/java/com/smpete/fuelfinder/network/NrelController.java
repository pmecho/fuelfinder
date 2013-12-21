package com.smpete.fuelfinder.network;

import android.util.Log;
import com.smpete.fuelfinder.data.StationModel;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.Date;
import java.util.List;

public enum NrelController {
    INSTANCE;

    private static final String API_KEY = "b50658292b2e83e2c5d4ad45291cdf2f7f76a920";
    private static final String BASE_URL = "http://developer.nrel.gov";

    // Use in memory throttle for now
    private static final int STATION_THROTTLE = 1000 * 60 * 60 * 24 * 3; // 3 days
    private long mLastRequestMillis;

    private NrelService mService;

    private NrelController() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer(BASE_URL)
                .build();

        mService = restAdapter.create(NrelService.class);
    }

    public void getAllStations() {
        if (System.currentTimeMillis() - mLastRequestMillis > STATION_THROTTLE) {
            mService.listAllStations(API_KEY, new Callback<FuelStationResult>() {
                @Override
                public void success(FuelStationResult fuelStationResult, Response response) {
                    mLastRequestMillis = System.currentTimeMillis();
                    for (StationModel station : fuelStationResult.fuel_stations) {
//                        Log.d("XXX", station.getStreetAddress());
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.v("XXX", "fail");
                }
            });
        }
    }

    public FuelStationResult getAllStationsSynchronous() {
        // TODO:  Only use local for debugging
        return mService.listLocalStationsSynchronous(API_KEY, "80218", 50, 100);
//        return mService.listAllStationsSynchronous(API_KEY);
    }



    public interface NrelService {
        @GET("/api/alt-fuel-stations/v1.json")
        void listAllStations(@Query("api_key") String apiKey,
                             Callback<FuelStationResult> callback);

        @GET("/api/alt-fuel-stations/v1.json")
        FuelStationResult listAllStationsSynchronous(@Query("api_key") String apiKey);

        @GET("/api/alt-fuel-stations/v1/nearest.json")
        FuelStationResult listLocalStationsSynchronous(@Query("api_key") String apiKey,
                          @Query("location") String locationString, @Query("radius") double radius, @Query("limit") int limit);
    }

    static class FuelStationResult {
        List<StationModel> fuel_stations;
    }
}
