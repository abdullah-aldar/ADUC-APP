package com.aldar.studentportal.ui.signUp;

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
import com.aldar.studentportal.databinding.FragmentVerifyUserBinding;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class VerifyUserFragment extends Fragment implements View.OnClickListener {
    private FragmentVerifyUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_user, container, false);


        binding.btnVerifyCode.setOnClickListener(this);
        binding.etCode1.addTextChangedListener(genraltextWatcher);
        binding.etCode2.addTextChangedListener(genraltextWatcher);
        binding.etCode3.addTextChangedListener(genraltextWatcher);
        binding.etCode4.addTextChangedListener(genraltextWatcher);

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

            if (binding.etCode1.length() == 1) {
                binding.etCode2.requestFocus();
            }
            if (binding.etCode2.length() == 1) {
                binding.etCode3.requestFocus();
            }
            if (binding.etCode3.length() == 1) {
                binding.etCode4.requestFocus();
            }
        }

    };

    private boolean checkCode() {
        boolean valid = true;


        String strOTP1 = binding.etCode1.getText().toString().trim();
        String strOTP2 = binding.etCode2.getText().toString().trim();
        String strOTP3 = binding.etCode3.getText().toString().trim();
        String strOTP4 = binding.etCode4.getText().toString().trim();

        Bundle bundle = this.getArguments();
        String strOTP = "";
        if(bundle != null){
            strOTP = bundle.getString("verification_code");
        }


        String strUserEnterOTP = strOTP1 + strOTP2 + strOTP3 + strOTP4;

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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_verify_code:
                binding.btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkCode()) {
                            GeneralUtilities.connectFragmentWithBack(getContext(), new CreateStudentFragment());
                        }
                    }
                });
                break;
        }
    }
}
