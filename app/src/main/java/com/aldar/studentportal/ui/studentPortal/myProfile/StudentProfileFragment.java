package com.aldar.studentportal.ui.studentPortal.myProfile;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentStudentProfileBinding;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class StudentProfileFragment extends Fragment {
    private FragmentStudentProfileBinding binding;
    private StudentProfileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(StudentProfileViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudenProfileViewModel(viewModel);

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.tvEditMobileNo.setOnClickListener(v -> {
            enterUserInfo();
        });

    }

    private void enterUserInfo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_edit_dialog);
        dialog.setCancelable(false);
        EditText etData = dialog.findViewById(R.id.et_data);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        Button btnClose = dialog.findViewById(R.id.btn_close);

        btnConfirm.setOnClickListener(v -> {
            String data = etData.getText().toString();
            if (data.isEmpty()) {
                etData.setError("Please enter you new mobile number");
            } else if (viewModel.apiCallUpdateProfile(data, false)) {
                dialog.dismiss();
                GeneralUtilities.connectFragmentWithBack(getActivity(), new UpdateProfileOTPFragment());

            }
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

}
