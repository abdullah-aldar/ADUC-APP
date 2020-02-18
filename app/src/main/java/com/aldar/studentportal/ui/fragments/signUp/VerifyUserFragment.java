package com.aldar.studentportal.ui.fragments.signUp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.containerActivities.LoginSignUpActivity;
import com.aldar.studentportal.utilities.GeneralUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerifyUserFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.et_code1)
    EditText etCode1;
    @BindView(R.id.et_code2)
    EditText etCode2;
    @BindView(R.id.et_code3)
    EditText etCode3;
    @BindView(R.id.et_code4)
    EditText etCode4;
    @BindView(R.id.btn_verify_user)
    Button btnVerifyUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_user, container, false);
        ButterKnife.bind(this, view);

        btnVerifyUser.setOnClickListener(this);
        etCode1.addTextChangedListener(genraltextWatcher);
        etCode2.addTextChangedListener(genraltextWatcher);
        etCode3.addTextChangedListener(genraltextWatcher);
        etCode4.addTextChangedListener(genraltextWatcher);

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

            if (etCode1.length() == 1) {
                etCode2.requestFocus();
            }
            if (etCode2.length() == 1) {
                etCode3.requestFocus();
            }
            if (etCode3.length() == 1) {
                etCode4.requestFocus();
            }
        }

    };

    private boolean checkCode() {
        boolean valid = true;


        String strOTP1 = etCode1.getText().toString().trim();
        String strOTP2 = etCode2.getText().toString().trim();
        String strOTP3 = etCode3.getText().toString().trim();
        String strOTP4 = etCode4.getText().toString().trim();

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
            case R.id.btn_verify_user:
                btnVerifyUser.setOnClickListener(new View.OnClickListener() {
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
