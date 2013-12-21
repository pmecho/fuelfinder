package com.smpete.fuelfinder.network;

import android.app.IntentService;
import android.content.Intent;
import com.smpete.fuelfinder.data.StationModel;
import com.smpete.fuelfinder.provider.StationColumns;
import com.smpete.fuelfinder.provider.StationContentValues;

import java.util.List;

public class FetchStationsService extends IntentService {

    private static final String SERVICE_NAME = "FetchStations";

    public FetchStationsService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NrelController.FuelStationResult stationResult = NrelController.INSTANCE.getAllStationsSynchronous();
        List<StationModel> stationModels = stationResult.fuel_stations;


        // TODO add ID
        StationContentValues stationContentValues;
        for (StationModel stationModel : stationModels) {
            stationContentValues = new StationContentValues();
            stationContentValues.putName(stationModel.getName());
            stationContentValues.putAddress(stationModel.getStreetAddress());
            stationContentValues.putCity(stationModel.getCity());
            stationContentValues.putState(stationModel.getState());
            stationContentValues.putZip(stationModel.getZip());
            stationContentValues.putPhone(stationModel.getPhone());
            stationContentValues.putAccessTime(stationModel.getAccessDaysTime());
            stationContentValues.putGeocodeStatus(stationModel.getGeocodeStatus());
            stationContentValues.putFuelType(stationModel.getFuelType());
            stationContentValues.putLatitude((double) stationModel.getLatitude());
            stationContentValues.putLongitude((double) stationModel.getLongitude());
            if (stationModel.getUpdatedAt() != null) {
                stationContentValues.putUpdatedAt(stationModel.getUpdatedAt());
            }

            getContentResolver().insert(StationColumns.CONTENT_URI, stationContentValues.getContentValues());
        }
    }
}
