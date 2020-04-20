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

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.ChequesAdapter;
import com.aldar.studentportal.adapters.DigitalLibraryAdapter;
import com.aldar.studentportal.databinding.ActivityFeeBinding;
import com.aldar.studentportal.interfaces.CallBackAmount;
import com.aldar.studentportal.ui.activities.PaymentActivity;

public class FeeActivity extends AppCompatActivity {
    private ActivityFeeBinding binding;
    private PaymentViewModel viewModel;
    private String strTranscatonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fee);
        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setPaymentViewModel(viewModel);

        binding.btnGo.setOnClickListener(v -> {
            viewModel.apiCalStudentInfo();
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
            loadPaymentPage();
        });
    }

    private void checkPaymentMethod(int checkedId) {
        switch (checkedId) {
            case R.id.radio_cheques:
                viewModel.apiCalGetCheques();
                binding.chequeLayout.setVisibility(View.VISIBLE);

                break;
            case R.id.radio_others:
                strTranscatonID = "";
                viewModel.amount.setValue("");
                binding.chequeLayout.setVisibility(View.GONE);
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

    private CallBackAmount callBackAmount = (amount, transcatonID) -> {
        viewModel.amount.setValue(String.valueOf(amount));
        strTranscatonID = transcatonID;
    };
}
