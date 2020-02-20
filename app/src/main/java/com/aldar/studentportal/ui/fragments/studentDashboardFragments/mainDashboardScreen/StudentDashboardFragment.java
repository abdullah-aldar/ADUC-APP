package com.aldar.studentportal.ui.fragments.studentDashboardFragments.mainDashboardScreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.storage.OnObbStateChangeListener;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudentDashboardItemsAdapter;
import com.aldar.studentportal.databinding.FragmentStudentDashboardBinding;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.ui.activities.NavigationActivity;
import com.aldar.studentportal.ui.fragments.login.LoginFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.announcment.AnnouncementFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.library.LibraryFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentDashboardFragment extends Fragment {
    private FragmentStudentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_dashboard,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        initViews();

        return binding.getRoot();
    }


    private void initViews() {

        String base64 = GeneralUtilities.getSharedPreferences(getActivity()).getString("student_image","");

        StudentDashboradItemsViewmodel viewmodel = new ViewModelProvider(this).get(StudentDashboradItemsViewmodel.class);
        binding.setLifecycleOwner(this);
        binding.setStudnetDasboarViewModel(viewmodel);


        viewmodel.getItemData().observe(getViewLifecycleOwner(), new Observer<List<DashboardItemModel>>() {
            @Override
            public void onChanged(List<DashboardItemModel> dashboardItemModels) {
                binding.rvDashboardItem.setLayoutManager(new GridLayoutManager(getContext(), 2));
                StudentDashboardItemsAdapter dashboardItemsAdapter = new StudentDashboardItemsAdapter(getContext(), dashboardItemModels);
                binding.rvDashboardItem.setAdapter(dashboardItemsAdapter);
            }
        });


//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//        binding.ivMortarboard.setImageBitmap(Bitmap.createScaledBitmap(bmp, binding.ivMortarboard.getWidth(),
//                binding.ivMortarboard.getHeight(), false));



        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(),NavigationActivity.class));

            }
        });

        binding.ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        binding.layoutAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtilities.connectFragmentWithBack(getActivity(),new AnnouncementFragment());
            }
        });


    }

    private void showDialog(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);
        Button btnLogout = dialog.findViewById(R.id.btn_logout);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), NavigationActivity.class));
                GeneralUtilities.putBooleanValueInEditor(getContext(),"isLogin",false);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.rvDashboardItem.setAdapter(null);
    }
}
