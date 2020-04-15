package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.inbox;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.InboxAdapter;
import com.aldar.studentportal.databinding.InboxFragmentBinding;
import com.aldar.studentportal.models.inboxModels.StudentInboxResponseModel;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseSchedule.CourseScheduleViewModel;

public class InboxFragment extends Fragment {
    private InboxFragmentBinding binding;
    private InboxAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.inbox_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StudentInboxViewModel viewModel = new ViewModelProvider(this).get(StudentInboxViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setInboxViewModel(viewModel);
        binding.rvInbox.setLayoutManager(new LinearLayoutManager(getActivity()));

        getSudentInboxData(viewModel.getStudentInboxData());

        binding.tvComposeEmail.setOnClickListener(v -> {
          loadFragment(new ComposeEmailFragment());
        });

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }

    private void getSudentInboxData(MutableLiveData<StudentInboxResponseModel> studentInboxData){
        studentInboxData.observe(getViewLifecycleOwner(), new Observer<StudentInboxResponseModel>() {
            @Override
            public void onChanged(StudentInboxResponseModel studentInboxResponseModel) {
                if(studentInboxResponseModel != null){
                    adapter = new InboxAdapter(studentInboxResponseModel.getData());
                    binding.rvInbox.setAdapter(adapter);
                }
            }
        });
    }


    private void loadFragment(Fragment fragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        fragment, null).commit();
    }
    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }
}
