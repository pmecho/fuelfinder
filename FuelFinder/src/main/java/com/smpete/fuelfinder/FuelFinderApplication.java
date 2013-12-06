package com.smpete.fuelfinder;

import android.app.Application;
import com.smpete.fuelfinder.network.NrelController;

public class FuelFinderApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        NrelController.INSTANCE.getAllStations();
    }
}
