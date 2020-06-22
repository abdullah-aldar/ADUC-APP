package com.aldar.studentportal.ui.fragments.navigations.userEnquiry;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.ContactUsFragmentBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactUsFragment extends Fragment implements OnMapReadyCallback  {

    private ContactUsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.contact_us_fragment,container,false));

        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            binding.mapView.getMapAsync(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ContactUsViewModel mViewModel = new ViewModelProvider(this).get(ContactUsViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(mViewModel);

        mViewModel.getEnquiryData().observe(getViewLifecycleOwner(),commonApiResponse -> {
            if(commonApiResponse != null){
                Toast.makeText(getContext(), ""+commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            mViewModel.onClick();
        });


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(25.253197, 55.338931);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Dubai"));
        googleMap.setMaxZoomPreference(15);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14.0f));
    }

    @Override
    public void onResume() {
        binding.mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        binding.mapView.onPause();
        super.onPause();

    }


    @Override
    public void onDestroy() {
        binding.mapView.onDestroy();
        binding = null;
        super.onDestroy();
    }
}
