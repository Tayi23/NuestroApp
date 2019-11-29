package com.example.tayiapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import static java.util.Locale.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Marker marker1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings= mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);



        LatLng nuestro = new LatLng(-18.477941247964267, -70.31998930713664);

        marker1= googleMap.addMarker(new MarkerOptions().position(nuestro).draggable(true).title("Nuestro Bar 351").snippet("El Bar Restaurante ubicado en el casco histórico de la ciudad ").icon(BitmapDescriptorFactory.fromResource(R.drawable.icono1)));
        float zoomlevel=20;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nuestro,zoomlevel));
        googleMap.setOnMarkerClickListener(this);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(marker1)){
        String lat,log;
        lat = Double.toString(marker.getPosition().latitude);
        log = Double.toString(marker.getPosition().longitude);
            Toast.makeText(this,lat+","+log,Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}
