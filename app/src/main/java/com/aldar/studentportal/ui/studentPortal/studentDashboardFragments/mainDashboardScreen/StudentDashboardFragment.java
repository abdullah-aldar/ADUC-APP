package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.mainDashboardScreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudentDashboardItemsAdapter;
import com.aldar.studentportal.databinding.FragmentStudentDashboardBinding;
import com.aldar.studentportal.interfaces.ItemClickCallBack;
import com.aldar.studentportal.ui.activities.common.NavigationActivity;
import com.aldar.studentportal.ui.studentPortal.activities.LoginSignUpActivity;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.announcment.AnnouncementFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;


public class StudentDashboardFragment extends Fragment {
    private FragmentStudentDashboardBinding binding;
    private StudentDashboardItemsAdapter adapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_dashboard,container,false);

        adapter = new StudentDashboardItemsAdapter(mItemClickCallback);
        initViews();

        return binding.getRoot();
    }


    private void initViews() {
        StudentDashboradItemsViewmodel viewmodel = new ViewModelProvider(this).get(StudentDashboradItemsViewmodel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudnetDasboarViewModel(viewmodel);


        viewmodel.getItemData().observe(getViewLifecycleOwner(), dashboardItemModels -> {

            binding.rvDashboardItem.setLayoutManager(new GridLayoutManager(getContext(), 2));
            adapter.setProductList(dashboardItemModels);
            binding.rvDashboardItem.setAdapter(adapter);
        });


        binding.ivBack.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),NavigationActivity.class));
            Objects.requireNonNull(getActivity()).finish();

        });

        binding.ivLogout.setOnClickListener(v -> showDialog());

        binding.layoutAnnouncement.setOnClickListener(v -> GeneralUtilities.connectFragmentWithBack(getActivity(),new AnnouncementFragment()));
    }

    private void showDialog(){
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnLogout = dialog.findViewById(R.id.btn_logout);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnLogout.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getActivity(), NavigationActivity.class));
            SharedPreferencesManager.getInstance(getContext()).setBooleaninEditor("isLogin",false);
        });
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }


    private void greetingMessage(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            Toast.makeText(getContext(), "Good Morning", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            Toast.makeText(getContext(), "Good Afternoon", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            Toast.makeText(getContext(), "Good Evening", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            Toast.makeText(getContext(), "Good Night", Toast.LENGTH_SHORT).show();
        }
    }


    private final ItemClickCallBack mItemClickCallback = item -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((LoginSignUpActivity) requireActivity()).show(item);
        }
    };
}
