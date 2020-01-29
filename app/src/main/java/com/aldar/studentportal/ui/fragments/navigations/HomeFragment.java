package com.aldar.studentportal.ui.fragments.navigations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_download_boucher)
    Button btnBoucher;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        btnBoucher.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_download_boucher:
                startActivity(new Intent(getActivity(), WebActivity.class));
                break;
        }
    }
}