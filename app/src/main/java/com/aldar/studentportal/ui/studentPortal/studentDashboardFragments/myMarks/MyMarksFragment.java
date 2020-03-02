package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myMarks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.MarksAdapter;
import com.aldar.studentportal.adapters.StudyPlanAdapter;
import com.aldar.studentportal.databinding.FragmentMyMarksBinding;
import com.aldar.studentportal.databinding.FragmentMyStudyPlanBinding;
import com.aldar.studentportal.interfaces.StudyPlanInterface;
import com.aldar.studentportal.models.mymarksmodels.MarksResponseModel;
import com.aldar.studentportal.models.studyplan.StudyPlanResponseModel;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myStudyPlan.MyStudyPlanViewModel;

public class MyMarksFragment extends Fragment {
    private FragmentMyMarksBinding binding;
    private MarksAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_my_marks, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyMarksViewModel viewModel =  new ViewModelProvider(this).get(MyMarksViewModel.class);
        binding.rvMarks.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setMarksViewModel(viewModel);

        marksData(viewModel.getMarksData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void marksData(MutableLiveData<MarksResponseModel> mutableLiveData){
        mutableLiveData.observe(getViewLifecycleOwner(),marksResponseModel -> {
            if(marksResponseModel != null){
                adapter = new MarksAdapter(getActivity(), marksResponseModel.getData(),marksResponseModel.getData().get(0).getCourses());
                binding.rvMarks.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }

}
