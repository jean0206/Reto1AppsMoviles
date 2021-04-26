package com.example.reto1apps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class MapsFragment extends Fragment implements modalFragment.OnOkListener, GoogleMap.OnMarkerClickListener {

    private boolean addresCheck;
    private modalFragment dialog;
    private String address = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng( 3.42158, -76.5205);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .snippet(""));
                            getAddressFromLatLng(latLng);
                            openModal();
                }
            });
        }
    };


    public void openModal () {

        dialog.setListener(this);
        dialog.show(getFragmentManager(),"buttonDialog");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maps,container,false);
        dialog = new modalFragment();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void getAddressFromLatLng( LatLng latLng ) {
        Geocoder geocoder = new Geocoder( getActivity() );

        try {
            address = geocoder.getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {

        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }



    @Override
    public void onOk(boolean setAddress) {
        dialog.dismiss();
        Bundle bundle = new Bundle();
        bundle.putString("address",address);
        AddPlace fragment = new AddPlace();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }
}