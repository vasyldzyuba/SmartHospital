package com.smarthospital.smarthospital.screen.maps;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smarthospital.smarthospital.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static Intent getStartIntent(Context context){
        Intent startActivity = new Intent(context, MapsActivity.class);
        return startActivity;
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng lviv = new LatLng(49.838, 24.029);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lviv, 12));
    }
}
