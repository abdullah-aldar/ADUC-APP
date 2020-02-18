package com.aldar.studentportal.ui.fragments.forgotPassword;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.utilities.GeneralUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyCodeFragment extends Fragment implements View.OnClickListener {
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
    private String strOTP,strUserEnterOTP;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkOTP()) {
                            GeneralUtilities.connectFragmentWithBack(getContext(), new SetNewFragment());
                        }
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

        strOTP = GeneralUtilities.getSharedPreferences(getActivity()).getString("otp", "");
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
