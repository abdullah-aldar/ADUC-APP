package com.aldar.studentportal.ui.studentPortal.letterRequest;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.CustomConfirmLetterrDialogBinding;
import com.aldar.studentportal.databinding.FragmentLetterRequestBinding;
import com.aldar.studentportal.models.letterModels.LetterRequestResponseModel;
import com.aldar.studentportal.ui.activities.payment.PaymentActivity;
import com.aldar.studentportal.utilities.SharedPreferencesManager;


import java.text.DecimalFormat;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LetterRequestFragment extends Fragment {
    private FragmentLetterRequestBinding binding;
    private String strPDfLink, strLetterID, strLetterTo;
    private boolean checkEnglisArabic = false;
    private boolean checkPreview = false;
    private double amount, amountWithVAT;

    private LetterRequestViewModel viewModel;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private int studentID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_letter_request, container, false);

        studentID = SharedPreferencesManager.getInstance(getActivity()).getIntValue("student_id");

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LetterRequestViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setLetterViewModel(viewModel);

        getletterTypeData(viewModel.getAllLetterResponseData());

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_english:
                    checkEnglisArabic = false;
                    strLetterTo = "To Whom it May Concern";
                    binding.etWhomConcern.setText("To Whom it May Concern");
                    break;
                case R.id.radio_arabic:
                    checkEnglisArabic = true;
                    strLetterTo = "الى من يهمه الامر";
                    binding.etWhomConcern.setText("الى من يهمه الامر");
                    break;
            }
        });

        binding.btnContinue.setOnClickListener(v -> {
            showConfirmDialog();
        });

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.tvPreviewLetter.setOnClickListener(v -> {
            checkPreview = true;
        });
    }

    private void getletterTypeData(MutableLiveData<LetterRequestResponseModel> allLetterResponseData) {
        allLetterResponseData.observe(getViewLifecycleOwner(), letterRequestResponseModel -> {
            if (letterRequestResponseModel != null && letterRequestResponseModel.getData().size() > 0) {
                //converting arraylist to string array
                String[] namesArr = new String[letterRequestResponseModel.getData().size()];
                for (int i = 0; i < letterRequestResponseModel.getData().size(); i++) {
                    namesArr[i] = String.valueOf(letterRequestResponseModel.getData().get(i).getServiceName());
                }

                binding.letterSpinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, namesArr, "Select"));
                binding.letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        strLetterID = String.valueOf(letterRequestResponseModel.getData().get(position).getServiceId());
                        amount = letterRequestResponseModel.getData().get(position).getRetailPrice();
                        amountWithVAT = amount + (amount * (5.0f / 100.0f));


                        if (checkEnglisArabic) {
                            strPDfLink = letterRequestResponseModel.getData().get(position).getPathEN();
                        } else {
                            strPDfLink = letterRequestResponseModel.getData().get(position).getPathAR();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }


    private void showConfirmDialog() {
        Dialog dialog = new Dialog(getActivity());
        CustomConfirmLetterrDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.custom_confirm_letterr_dialog, null, false);
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvLetterRate.setText(new DecimalFormat("##.##").format(amountWithVAT) + " DHS Will be Charged From Your Account For This Service(Inclusive of VAT)" +
                "\n سوف يتم خصم مبلغ" + new DecimalFormat("##.##").format(amountWithVAT) + "درهم من حسابك نظير هذه الخدمة ");


        if (!checkBalance(amountWithVAT)) {
            dialogBinding.btnConfirm.setVisibility(View.GONE);
        } else {
            dialogBinding.btnConfirm.setVisibility(View.VISIBLE);
        }

        dialogBinding.btnConfirm.setOnClickListener(v -> {
            viewModel.apiCallRequestLetter(strLetterTo, strLetterID);
        });

        dialogBinding.btnPayOnline.setOnClickListener(v -> {
            String paymentLink = "http://5.101.139.187:8080/StudentPortal/Views/PaymentPage/Payment_App.aspx?StudentId=" + studentID + "&ServiceId=" + strLetterID + "&Lang=EN&LetterTo=To%20Whom%20It%20May%20Concern";
            proceedToPayment(paymentLink);
            dialog.dismiss();
        });

        dialogBinding.ivClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void proceedToPayment(String link) {
        Bundle bundle = new Bundle();
        bundle.putString("paymentLink", link);
        startActivity(new Intent(getActivity(), PaymentActivity.class).putExtras(bundle));
    }

    private boolean checkBalance(double amount) {
        boolean amountCheck = false;
        double currentBalance = Double.parseDouble(SharedPreferencesManager.getInstance(getActivity()).getStringValue("balance"));

        amountCheck = 55 >= amount;
        return amountCheck;
    }

}
