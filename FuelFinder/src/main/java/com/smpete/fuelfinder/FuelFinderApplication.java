package com.smpete.fuelfinder;

import android.app.Application;
import android.content.Intent;
import com.smpete.fuelfinder.network.FetchStationsService;
import com.smpete.fuelfinder.network.NrelController;

public class FuelFinderApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, FetchStationsService.class));
    }
}
