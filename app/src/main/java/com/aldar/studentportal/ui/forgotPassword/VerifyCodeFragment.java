package com.aldar.studentportal.ui.forgotPassword;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentVerifyCodeBinding;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

public class VerifyCodeFragment extends Fragment implements View.OnClickListener {
    private FragmentVerifyCodeBinding binding;
    private boolean valid = false;
    private String strOTP,strUserEnterOTP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_code, container, false);

        
        binding.verify.setOnClickListener(this);
        binding.etOtp1.addTextChangedListener(genraltextWatcher);
        binding.etOtp2.addTextChangedListener(genraltextWatcher);
        binding.etOtp3.addTextChangedListener(genraltextWatcher);
        binding.etOtp4.addTextChangedListener(genraltextWatcher);

        return binding.getRoot();
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

            if (binding.etOtp1.length() == 1) {

                binding.etOtp2.requestFocus();

            }
            if (binding.etOtp2.length() == 1) {

                binding.etOtp3.requestFocus();

            }
            if (binding.etOtp3.length() == 1) {

                binding.etOtp4.requestFocus();

            }
        }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                binding.verify.setOnClickListener(v1 -> {
                    if (checkOTP()) {
                        GeneralUtilities.connectFragmentWithBack(getContext(), new SetNewFragment());
                    }
                });
                break;
        }
    }


    private boolean checkOTP() {
        valid = true;

        String strOTP1 = binding.etOtp1.getText().toString().trim();
        String strOTP2 = binding.etOtp2.getText().toString().trim();
        String strOTP3 = binding.etOtp3.getText().toString().trim();
        String strOTP4 = binding.etOtp4.getText().toString().trim();

        strOTP =  SharedPreferencesManager.getInstance(getContext()).getStringValue("otp");
        strUserEnterOTP = strOTP1 + strOTP2 + strOTP3 + strOTP4;

        if (strUserEnterOTP.isEmpty()) {
            valid = false;
            showToast(getActivity(), "Please Enter your OTP");
        }

        if (strOTP.equals(strUserEnterOTP)) {
            valid = true;
            showToast(getActivity(), "OTP matched");
        }
        else {
            valid = false;
            showToast(getActivity(),"OTP Not Matchged");
        }

        return valid;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
