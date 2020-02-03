package com.aldar.studentportal.ui.fragments.navigations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.FaqActivity;
import com.aldar.studentportal.ui.activities.LoginActivity;
import com.aldar.studentportal.ui.activities.StudentPortalMainActivity;
import com.aldar.studentportal.utilities.FileUtils;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.layout_portal)
    LinearLayout layoutPortal;
    @BindView(R.id.btn_download_boucher)
    Button btnBoucher;
    @BindView(R.id.layout_faq)
    LinearLayout layoutFAQ;

    YouTubePlayerSupportFragment youTubePlayerSupportFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);

        layoutPortal.setOnClickListener(this);
        btnBoucher.setOnClickListener(this);
        layoutFAQ.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_portal:
                startActivity(new Intent(getActivity(), StudentPortalMainActivity.class));
                break;
            case R.id.btn_download_boucher:
                try {
                   new FileUtils(getActivity(),"https://www.aldar.ac.ae/wp-content/uploads/2019/11/prospectus.pdf");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_faq:
                startActivity(new Intent(getActivity(),FaqActivity.class));
                break;
        }
    }
}