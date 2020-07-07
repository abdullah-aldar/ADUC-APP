package com.aldar.studentportal.ui.commonScreens.navigations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.payment.PaymentActivity;

public class AdmissionFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        startActivity(new Intent(getContext(), PaymentActivity.class));
        return root;
    }
}