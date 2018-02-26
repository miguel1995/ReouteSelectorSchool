package com.example.miguel.reouteselectorschool;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle extras;
    private LatLng point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        extras = getIntent().getExtras();

    }


    //En este metodo se manipulan las caracteristicas del mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int pointZise = extras.getInt("pointsZise");

        //Recorre todas las coordenadas y las grafica en el mapa
        for(int i = 0; i < pointZise; i++) {
            point = new LatLng(extras.getFloat("latitude" + i), extras.getFloat("longitude" + i));
            mMap.addMarker(new MarkerOptions().position(point).title(String.valueOf(point.latitude) +"  "+ String.valueOf(point.longitude)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mMap.setMinZoomPreference(16.0f);
    }
}
