package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myStudyPlan;

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
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudyPlanAdapter;
import com.aldar.studentportal.databinding.FragmentMyStudyPlanBinding;
import com.aldar.studentportal.interfaces.StudyPlanInterface;
import com.aldar.studentportal.models.studyplan.StudyPlanResponseModel;


public class MyStudyPlanFragment extends Fragment {
    private FragmentMyStudyPlanBinding binding;
    private StudyPlanAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_my_study_plan, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyStudyPlanViewModel viewModel =  new ViewModelProvider(this).get(MyStudyPlanViewModel.class);
        binding.rvStudyPlan.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudyPlanViewModel(viewModel);

        studyPlanData(viewModel.getStudyPlanData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void studyPlanData(MutableLiveData<StudyPlanResponseModel> mutableLiveData){
        mutableLiveData.observe(getViewLifecycleOwner(),studyPlanResponseModel -> {
            if(studyPlanResponseModel.getData() != null){

                for(int j=0;j<=studyPlanResponseModel.getData().size();j++){

                }

                adapter = new StudyPlanAdapter(getActivity(), studyPlanResponseModel.getData(),
                        studyPlanInterface);
                binding.rvStudyPlan.setAdapter(adapter);

            }
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }

    private final StudyPlanInterface studyPlanInterface = (total, passed) -> {
        binding.tvTotalCreditHour.setText(total);
        binding.tvPassCreditsHours.setText(passed);
    };
}
