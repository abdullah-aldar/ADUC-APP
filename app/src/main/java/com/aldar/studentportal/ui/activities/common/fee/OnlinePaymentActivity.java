package com.aldar.studentportal.ui.activities.common.fee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.ChequesAdapter;
import com.aldar.studentportal.adapters.DigitalLibraryAdapter;
import com.aldar.studentportal.databinding.ActivityFeeBinding;
import com.aldar.studentportal.interfaces.CallBackAmount;
import com.aldar.studentportal.ui.activities.PaymentActivity;

public class OnlinePaymentActivity extends AppCompatActivity {
    private ActivityFeeBinding binding;
    private PaymentViewModel viewModel;
    private String strTranscatonID;
    private boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fee);
        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setPaymentViewModel(viewModel);

        binding.btnGo.setOnClickListener(v -> {
            if (validateUserID()) {
                viewModel.apiCalStudentInfo();
            }
        });

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            checkPaymentMethod(checkedId);
        });

        viewModel.getCheques().observe(this, chequeResponseModel -> {
            if (chequeResponseModel.getData() != null) {
                binding.rvCheques.setLayoutManager(new LinearLayoutManager(this));
                ChequesAdapter adapter = new ChequesAdapter(chequeResponseModel.getData(), callBackAmount);
                binding.rvCheques.setAdapter(adapter);
            }
        });

        binding.btnPay.setOnClickListener(v -> {
            if (validate()) {
                loadPaymentPage();
            }
        });
    }

    private void checkPaymentMethod(int checkedId) {
        switch (checkedId) {
            case R.id.radio_cheques:
                viewModel.apiCalGetCheques();
                binding.chequeLayout.setVisibility(View.VISIBLE);
                viewModel.note.setValue("");
                viewModel.amount.setValue("");
                binding.etAmount.setFocusable(false);

                break;
            case R.id.radio_others:
                strTranscatonID = "";
                viewModel.amount.setValue("");
                binding.chequeLayout.setVisibility(View.GONE);
                binding.etAmount.setFocusable(true);
                binding.etNote.setFocusable(true);
                break;
        }
    }

    private void loadPaymentPage() {
        String studentId = viewModel.studentID.getValue();
        String amount = viewModel.amount.getValue();
        String note = viewModel.note.getValue();
        String paymentUrl = "http://5.101.139.187:8080/StudentPortal/Views/PaymentPage/Payment_App1.aspx?StudentId="
                + studentId + "&Amount=" + amount + "&Note=" + note + "&TID=" + strTranscatonID;

        Bundle bundle = new Bundle();
        bundle.putString("paymentLink", paymentUrl);
        startActivity(new Intent(this, PaymentActivity.class).putExtras(bundle));
    }

    private boolean validate() {
        valid = true;

        if (viewModel.studentID.getValue() == null || viewModel.studentID.getValue().isEmpty()) {
            valid = false;
            binding.etStudentId.setError("Enter your ID");
        }

        if (viewModel.amount.getValue() == null || viewModel.amount.getValue().isEmpty()) {
            valid = false;
            binding.etAmount.setError("Enter your amount");
        }

        if (viewModel.note.getValue() == null || viewModel.note.getValue().isEmpty()) {
            valid = false;
            binding.etNote.setError("Note Should not be empty");
        }

        if (!binding.checkTermCondition.isChecked()) {
            valid = false;
            Toast.makeText(this, "Please agree with term & conditions", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    private boolean validateUserID() {
        boolean validId = true;
        if (viewModel.studentID.getValue() == null || viewModel.studentID.getValue().isEmpty()) {
            validId = false;
            binding.etStudentId.setError("Enter your ID");
        }
        return validId;
    }

    private CallBackAmount callBackAmount = (amount, transcatonID, chequeNUmber, chequeDate) -> {
        String note = "Transcation ID = " + transcatonID + " Cheque No =" + chequeNUmber + "\n"
                + "Amount = " + amount + "Date = " + chequeDate;

        viewModel.amount.setValue(String.valueOf(amount));
        viewModel.note.setValue(note);
        strTranscatonID = transcatonID;
    };
}
