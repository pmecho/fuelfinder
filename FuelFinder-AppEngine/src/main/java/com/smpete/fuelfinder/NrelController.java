package com.smpete.fuelfinder;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Api(name = "nrelcontroller", namespace = @ApiNamespace(ownerDomain = "smpete.com", ownerName = "smpete.com", packagePath = "fuelfinder"))
public class NrelController {

    private static final Logger log = Logger.getLogger(NrelController.class.getName());

    private static final String API_KEY = "b50658292b2e83e2c5d4ad45291cdf2f7f76a920";
    private static final String BASE_URL = "http://developer.nrel.gov";

    private static final String[] VALID_GEOCODE_STATUSES = {"GPS", "200-9", "200-8", "200-7"};

    private NrelService mService;

    public NrelController() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer(BASE_URL)
                .build();

        mService = restAdapter.create(NrelService.class);
    }

    @ApiMethod(name = "getNearbyStations")
    public void getNearbyStations() {
        try {
            FuelStationResult result = mService.listStations(API_KEY,
                "80218",
                "E",
                "public",
                "E85");
        for (StationModel stationModel : result.fuel_stations) {
            // Check station location accuracy
            boolean isAccurateEnough = false;
            for (String validStatus : VALID_GEOCODE_STATUSES) {
                if (validStatus.equals(stationModel.geocode_status)) {
                    isAccurateEnough = true;
                    break;
                }
            }
            if (!isAccurateEnough) {
                continue;
            }

            // Parse!
            FuelStation station = new FuelStation(stationModel);
            getEntityManager().persist(station);

            log.info(stationModel.street_address);

        }
        }catch (RetrofitError e) {
            log.info(e.getMessage() + e.getCause());
        }
    }

    @ApiMethod(name = "listStations")
    public CollectionResponse<FuelStation> listStations() {

        EntityManager mgr = null;
        Cursor cursor = null;
        List<FuelStation> execute = null;
        String cursorString = null;

        try {
            mgr = getEntityManager();
            javax.persistence.Query query = mgr
                    .createQuery("select from FuelStation as FuelStation");

            execute = (List<FuelStation>) query.getResultList();
            cursor = JPACursorHelper.getCursor(execute);
            if (cursor != null)
                cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (FuelStation obj : execute)
                ;
        } finally {
            mgr.close();
        }

        return CollectionResponse.<FuelStation>builder().setItems(execute)
                .setNextPageToken(cursorString).build();
    }

//    @ApiMethod(name = "getAllStations")
    private void getAllStations() {
        mService.listAllStations(API_KEY, new Callback<FuelStationResult>() {
            @Override
            public void success(FuelStationResult fuelStation, Response response) {
                for (StationModel station : fuelStation.fuel_stations) {
                    log.info(station.street_address);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                log.info("fail");
            }
        });

    }

    private static EntityManager getEntityManager() {
        return EMF.get().createEntityManager();
    }

    private interface NrelService {
        @GET("/api/alt-fuel-stations/v1/nearest.json")
        FuelStationResult listStations(@Query("api_key") String apiKey,
                          @Query("location") String locationString,
                          @Query("status") String status,
                          @Query("access") String access,
                          @Query("fuel_type") String fuelType);

        @GET("/api/alt-fuel-stations/v1.json")
        void listAllStations(@Query("api_key") String apiKey,
                             Callback<FuelStationResult> callback);
    }

    private static class FuelStationResult {
        List<StationModel> fuel_stations;
    }

    public static class StationModel {
        int id;
        String station_name;
        String street_address;
        String city;
        String state;
        String zip;
        String station_phone;
        String access_days_time;
        String geocode_status;
        float latitude;
        float longitude;
        Date updated_at;
    }
}
