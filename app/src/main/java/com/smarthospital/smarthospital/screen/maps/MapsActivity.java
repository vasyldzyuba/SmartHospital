package com.smarthospital.smarthospital.screen.maps;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Hospital;
import com.smarthospital.smarthospital.model.Location;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String EXTRA_HOSPITAL_LIST = "EXTRA_HOSPITAL_LIST";

    public static Intent getStartIntent(Context context, List<Hospital> hospitalList) {
        Intent startIntent = new Intent(context, MapsActivity.class);
        startIntent.putParcelableArrayListExtra(
                EXTRA_HOSPITAL_LIST, new ArrayList<Hospital>(hospitalList));

        return startIntent;
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar mapsToolbar = (Toolbar) findViewById(R.id.mapsToolbar);
        mapsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng lviv = new LatLng(49.838, 24.029);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lviv, 12));
        List<Hospital> hospitalList=getIntent().getParcelableArrayListExtra(EXTRA_HOSPITAL_LIST);
        for(int i=0; i< hospitalList.size(); i++ ) {
            Hospital hospital = hospitalList.get(i);
            Location location = hospital.getLocation();
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .title(hospital.getName()));
        }
    }

}
