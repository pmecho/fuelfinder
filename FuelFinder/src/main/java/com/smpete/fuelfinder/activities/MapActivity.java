package com.smpete.fuelfinder.activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.smpete.fuelfinder.R;
import com.smpete.fuelfinder.network.NrelController;

public class MapActivity extends Activity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        NrelController.INSTANCE.getAllStations();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }
    
}
