package com.aldar.studentportal.ui.studentPortal.mainDashboardScreen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudentDashboardItemsAdapter;
import com.aldar.studentportal.databinding.FragmentStudentDashboardBinding;
import com.aldar.studentportal.interfaces.ItemClickCallBack;
import com.aldar.studentportal.ui.activities.common.NavigationActivity;
import com.aldar.studentportal.ui.activities.LoginSignUpActivity;
import com.aldar.studentportal.ui.studentPortal.announcment.AnnouncementFragment;
import com.aldar.studentportal.ui.studentPortal.myProfile.StudentProfileFragment;
import com.aldar.studentportal.ui.studentPortal.notifications.NotificationFragment;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import org.jetbrains.annotations.NotNull;



public class StudentDashboardFragment extends Fragment {
    private FragmentStudentDashboardBinding binding;
    private StudentDashboardItemsAdapter adapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_dashboard, container, false);
        initViews();

        return binding.getRoot();
    }


    private void initViews() {
        StudentDashboradViewmodel viewmodel = new ViewModelProvider(this).get(StudentDashboradViewmodel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudnetDasboarViewModel(viewmodel);

        int countMessage = SharedPreferencesManager.getInstance(getActivity()).getIntValue("notification_count");
        if(countMessage == 0){
            binding.tvCountNotification.setVisibility(View.GONE);
        }
        else {
            binding.tvCountNotification.setVisibility(View.VISIBLE);
            binding.tvCountNotification.setText(String.valueOf(countMessage));
        }



        viewmodel.getItemData().observe(getViewLifecycleOwner(), dashboardItemModels -> {
            binding.rvDashboardItem.setLayoutManager(new GridLayoutManager(getContext(), 2));
            adapter = new StudentDashboardItemsAdapter(mItemClickCallback);
            adapter.setProductList(dashboardItemModels);
            binding.rvDashboardItem.setAdapter(adapter);
        });

        binding.ivProfile.setOnClickListener(v -> loadFragment(new StudentProfileFragment()));
        binding.layoutAnnouncement.setOnClickListener(v -> loadFragment(new AnnouncementFragment()));
        binding.layoutNotification.setOnClickListener(v -> {
                    loadFragment(new NotificationFragment());
                    SharedPreferencesManager.getInstance(getActivity()).setIntValueInEditor("notification_count", 0);
                });

        binding.ivBack.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NavigationActivity.class));
            getActivity().finish();

        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }


    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("BACK")
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container,
                        fragment, null).commit();
    }


    private final ItemClickCallBack mItemClickCallback = item -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((LoginSignUpActivity) requireActivity()).show(item);
        }
    };
}
