package com.aldar.studentportal.ui.studentPortal.courseAdvice.cart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.FragmentAdvisedCartBinding;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses.SavedCoursesActivity;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.advising.CourseAdviceViewModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;


public class AdvisedCartFragment extends Fragment {
    FragmentAdvisedCartBinding binding;
    private CourseAdviceViewModel viewModel;
    private String strSectionId = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_advised_cart, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CourseAdviceViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCartCoursesViewModel(viewModel);
        viewModel.showCart();

        viewModel.getAdvisedCartData().observe(getViewLifecycleOwner(), advisedCourseDataModels -> {
            binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(getActivity()));
            SelectedCoursesAdapter adapter = new SelectedCoursesAdapter(advisedCourseDataModels, getActivity(),"Local");
            binding.rvCourseslist.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            for (int i = 0; i < advisedCourseDataModels.size(); i++) {
                if (i == 0) {
                    strSectionId += advisedCourseDataModels.get(i).getSectionId();
                } else {
                    strSectionId += "," + advisedCourseDataModels.get(i).getSectionId();
                }
            }
        });

        viewModel.saveAdvisedCoursesData().observe(getViewLifecycleOwner(),commonApiResponse -> {
            if(commonApiResponse != null){
                Toast.makeText(getActivity(), ""+commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSeeCourses.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("semesterID",100);
            startActivity(new Intent(getActivity(), SavedCoursesActivity.class).putExtras(bundle));
        });

        binding.tvSave.setOnClickListener(view1 -> {
            showDialog();
        });
        binding.ivBack.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });
    }

    private void showDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.course_advice_dialog);
        TextView tvTitle = dialog.findViewById(R.id.title);
        TextView tvMessage = dialog.findViewById(R.id.message);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        Button btnReview = dialog.findViewById(R.id.btn_review);

        tvTitle.setText(getResources().getText(R.string.confirm));
        tvMessage.setText("Are you sure to register these courses!");

        btnOk.setText(getResources().getText(R.string.cancel));
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnReview.setText(getResources().getText(R.string.save));
        btnReview.setOnClickListener(v -> {
            dialog.dismiss();
            String studentID = String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getIntValue("student_id"));
            int semesterId = SharedPreferencesManager.getInstance(getActivity()).getIntValue("semester_id");
            if (validate()) {
                viewModel.apiCallSaveAdvisedCourses(studentID, String.valueOf(semesterId), strSectionId);
            }
        });
        dialog.show();
    }

    private boolean validate() {
        boolean valid;

        if (strSectionId.isEmpty()) {
            valid = false;
            Toast.makeText(getActivity(), "you have no course selected", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }
        return valid;
    }
}