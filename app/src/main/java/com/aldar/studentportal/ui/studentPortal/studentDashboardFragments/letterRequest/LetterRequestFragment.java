package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.letterRequest;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.CustomConfirmLetterrDialogBinding;
import com.aldar.studentportal.databinding.FragmentLetterRequestBinding;
import com.aldar.studentportal.models.letterModels.LetterRequestResponseModel;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseSchedule.CourseScheduleViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LetterRequestFragment extends Fragment {
    private FragmentLetterRequestBinding binding;
    private String amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_letter_request, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LetterRequestViewModel viewModel =  new ViewModelProvider(this).get(LetterRequestViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setLetterViewModel(viewModel);

        getletterTypeData(viewModel.getletterTypeData());



        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                switch (checkedId){
                    case R.id.radio_english:
                        binding.etWhomConcern.setText("To Whom it May Concern");
                        break;
                    case R.id.radio_arabic:
                        binding.etWhomConcern.setText("الى من يهمه الامر");
                        break;
                }
        });

        binding.btnContinue.setOnClickListener(v->{
            showConfirmDialog();
        });

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void getletterTypeData(MutableLiveData<LetterRequestResponseModel> getletterTypeData) {
        getletterTypeData.observe(getViewLifecycleOwner(), letterRequestResponseModel -> {
            if(letterRequestResponseModel != null && letterRequestResponseModel.getData().size()>0){
                //converting arraylist to string array
                String[] namesArr = new String[letterRequestResponseModel.getData().size()];
                for (int i = 0; i < letterRequestResponseModel.getData().size(); i++) {
                    namesArr[i] = String.valueOf(letterRequestResponseModel.getData().get(i).getServiceName());
                }

                binding.letterSpinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, namesArr, "Select"));
                binding.letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)    {
                         amount = String.valueOf(letterRequestResponseModel.getData().get(position).getRetailPrice());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    private void showConfirmDialog(){
        Dialog dialog = new Dialog(getActivity());
        CustomConfirmLetterrDialogBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout. custom_confirm_letterr_dialog, null, false);
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvLetterRate.setText(amount +" DHS Will be Deducted From Your Account For This Service(Inclusive of VAT)" +
                "\n سوف يتم خصم مبلغ"+ amount +"درهم من حسابك نظير هذه الخدمة ");


        dialog.show();
    }

    private void generateNoteOnSD(String sBody) {
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(50, 50, 30, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(sBody, 80, 50, paint);

        document.finishPage(page);

        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/ADUC/aldar.pdf";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"aldar.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(getActivity(), "Pdf Document Created", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(getActivity(), "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();


    }
}
