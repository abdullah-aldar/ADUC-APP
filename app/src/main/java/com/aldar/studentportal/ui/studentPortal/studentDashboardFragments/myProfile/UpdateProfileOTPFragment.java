package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myProfile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.updateProfileModel.UpdateProfileModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.ui.studentPortal.forgotPassword.SetNewFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileOTPFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.et_otp1)
    EditText etOTP1;
    @BindView(R.id.et_otp2)
    EditText etOTP2;
    @BindView(R.id.et_otp3)
    EditText etOTP3;
    @BindView(R.id.et_otp4)
    EditText etOTP4;
    @BindView(R.id.btn_verify)
    Button btnVerifyCode;

    private boolean valid = false;
    private String strOTP, strUserEnterOTP;
    private StudentProfileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);
        ButterKnife.bind(this, view);

        btnVerifyCode.setOnClickListener(this);
        etOTP1.addTextChangedListener(genraltextWatcher);
        etOTP2.addTextChangedListener(genraltextWatcher);
        etOTP3.addTextChangedListener(genraltextWatcher);
        etOTP4.addTextChangedListener(genraltextWatcher);

        return view;
    }

    private TextWatcher genraltextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (etOTP1.length() == 1) {

                etOTP2.requestFocus();

            }
            if (etOTP2.length() == 1) {

                etOTP3.requestFocus();

            }
            if (etOTP3.length() == 1) {

                etOTP4.requestFocus();

            }
        }

    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(StudentProfileViewModel.class);

        viewModel.getUpdateProfileData().observe(getViewLifecycleOwner(), updateProfileModel -> {
            if (updateProfileModel.getSuccess()) {
                showToast(getContext(), updateProfileModel.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                btnVerifyCode.setOnClickListener(v1 -> {
                    if (checkOTP()) {
                        confirmMobile();
                    }
                });
                break;
        }
    }


    private boolean checkOTP() {
        valid = true;

        String strOTP1 = etOTP1.getText().toString().trim();
        String strOTP2 = etOTP2.getText().toString().trim();
        String strOTP3 = etOTP3.getText().toString().trim();
        String strOTP4 = etOTP4.getText().toString().trim();

        strOTP = SharedPreferencesManager.getInstance(getContext()).getStringValue("otp");
        strUserEnterOTP = strOTP1 + strOTP2 + strOTP3 + strOTP4;

        if (strUserEnterOTP.isEmpty()) {
            valid = false;
            showToast(getActivity(), "Please Enter your OTP");
        }

        if (strOTP.equals(strUserEnterOTP)) {
            valid = true;
            showToast(getActivity(), "OTP matched");

        } else {
            valid = false;
            showToast(getActivity(), "OTP Not Matchged");
        }

        return valid;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    private void confirmMobile() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_edit_dialog);
        EditText etData = dialog.findViewById(R.id.et_data);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);

        btnConfirm.setText("Confirm");

        btnConfirm.setOnClickListener(v -> {
            String data = etData.getText().toString();
            if (data.isEmpty()) {
                etData.setError("Please Enter");
            } else {
                dialog.dismiss();
                viewModel.apiCallUpdateProfile(data, true);
            }
        });
        dialog.show();
    }
}
