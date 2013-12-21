package com.smpete.fuelfinder.network;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import com.smpete.fuelfinder.data.StationModel;
import com.smpete.fuelfinder.data.provider.StationColumns;
import com.smpete.fuelfinder.data.provider.StationContentValues;

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

        ContentValues stationContentValues[] = new ContentValues[stationModels.size()];
        StationModel stationModel;
        StationContentValues value;
        for (int i = 0; i < stationModels.size(); i++) {
            stationModel = stationModels.get(i);
            value = new StationContentValues();
            value.putId(stationModel.getId());
            value.putName(stationModel.getName());
            value.putAddress(stationModel.getStreetAddress());
            value.putCity(stationModel.getCity());
            value.putState(stationModel.getState());
            value.putZip(stationModel.getZip());
            value.putPhone(stationModel.getPhone());
            value.putAccessTime(stationModel.getAccessDaysTime());
            value.putGeocodeStatus(stationModel.getGeocodeStatus());
            value.putFuelTypes(stationModel.getFuelTypes());
            value.putLatitude((double) stationModel.getLatitude());
            value.putLongitude((double) stationModel.getLongitude());
            if (stationModel.getUpdatedAt() != null) {
                value.putUpdatedAt(stationModel.getUpdatedAt());
            }

            stationContentValues[i] = value.getContentValues();
        }
        getContentResolver().bulkInsert(StationColumns.CONTENT_URI, stationContentValues);
    }
}
