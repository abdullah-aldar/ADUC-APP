package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.inbox;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.InboxAdapter;
import com.aldar.studentportal.adapters.MarksAdapter;
import com.aldar.studentportal.databinding.InboxFragmentBinding;
import com.aldar.studentportal.models.inboxModels.StudentInboxResponseModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class InboxFragment extends Fragment {
    private InboxFragmentBinding binding;
    private LinearLayout bottomSheetContainer;
    private InboxAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.inbox_fragment, container, false);
       // bottomSheetContainer = binding.getRoot().findViewById(R.id.bottom_sheet);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InboxViewModel viewModel = new ViewModelProvider(this).get(InboxViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setInboxViewModel(viewModel);

        getSudentInboxData(viewModel.getStudentInboxData());

//        binding.tvComposeEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomsheetTheme);
//                View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet,bottomSheetContainer);
//
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });

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

}
