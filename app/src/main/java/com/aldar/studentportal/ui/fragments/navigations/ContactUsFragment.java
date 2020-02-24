package com.aldar.studentportal.ui.fragments.navigations;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactUsFragment extends Fragment implements OnMapReadyCallback  {

    private ContactUsViewModel mViewModel;
    private MapView mapView;
    private GoogleMap mGoogleMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.contact_us_fragment, container, false);

        mapView = root.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            mapView.getMapAsync(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContactUsViewModel.class);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng sydney = new LatLng(25.253197, 55.338931);
        mGoogleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        mGoogleMap.setMaxZoomPreference(15);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14.0f));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();

    }


    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}
