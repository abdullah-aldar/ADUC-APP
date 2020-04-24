package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.letterRequest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
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
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.CustomConfirmLetterrDialogBinding;
import com.aldar.studentportal.databinding.FragmentLetterRequestBinding;
import com.aldar.studentportal.models.letterModels.LetterRequestResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.ui.activities.PaymentActivity;
import com.aldar.studentportal.ui.activities.common.WebActivity;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
            showPdfDialog();
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
                        amountWithVAT = amount + (double) (amount * (5.0f / 100.0f));


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
            if (checkPreview) {
                String paymentLink = "http://5.101.139.187:8080/StudentPortal/Views/PaymentPage/Payment_App.aspx?StudentId=" + studentID + "&ServiceId=" + strLetterID + "&Lang=EN&LetterTo=To%20Whom%20It%20May%20Concern";
                proceedToPayment(paymentLink);
                dialog.dismiss();
            } else {
                Toast.makeText(getActivity(), "please preview the letter first", Toast.LENGTH_SHORT).show();
            }
        });

        dialogBinding.ivClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showPdfDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_pdf_layout);
        PDFView pdfView = dialog.findViewById(R.id.pdf);
        showPDF(pdfView);
        dialog.show();
    }

    private void showPDF(PDFView pdfView) {
        disposables.add(Observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<InputStream>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(InputStream contactList) {
                        pdfView.fromStream(contactList)
                                .pages(0, 2, 1, 3, 3, 3)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .defaultPage(0)
                                .enableAnnotationRendering(false)
                                .scrollHandle(null)
                                .spacing(0)
                                .load();

                    }
                }));
    }

    private void proceedToPayment(String link) {
        Bundle bundle = new Bundle();
        bundle.putString("paymentLink", link);
        startActivity(new Intent(getActivity(), PaymentActivity.class).putExtras(bundle));
    }

    Observable<InputStream> Observable() {
        return Observable.defer((Supplier<Observable<InputStream>>) () -> {
            // Do some long running operation
            InputStream input = null;
            try {
                input = new URL(strPDfLink).openStream();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return Observable.just(input);
        });
    }

    private boolean checkBalance(double amount) {
        boolean amountCheck = false;
        double currentBalance = Double.parseDouble(SharedPreferencesManager.getInstance(getActivity()).getStringValue("balance"));

        if (55 >= amount) {
            amountCheck = true;
        } else {
            amountCheck = false;
        }
        return amountCheck;
    }

}
