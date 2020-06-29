package com.aldar.studentportal.ui.fragments.navigations;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aldar.studentportal.R;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllumniFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allumni, container, false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void GetFiles() {
//        ArrayList contactList = new ArrayList<>();
//
//        ArrayList<String> MyFiles = new ArrayList<String>();
//        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ADUC/abdullah.vcf");
//
//        f.mkdirs();
//        File[] files = f.listFiles();
//
//        if (files == null || files.length < 0) {
//            Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
//        } else {
//            try {
//                for (int i = 0; i < files.length; i++) {
//                    ContactDataModel number = new ContactDataModel();
//                    number.setNameContact(files[i].getName());
//                    number.setNumContact(files[i].getName());
//                    contactList.add(number);
//                }
//
//            } catch (Exception e) {
//                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    private void generateVCFFile(List<ContactDataModel> modelList) {
        try {
            File directory = new File(
                    Environment.getExternalStorageDirectory() + "/ADUC");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File vcfFile = new File(directory, "abdullah.vcf");

            FileWriter vcfFileWrite = null;
            vcfFileWrite = new FileWriter(vcfFile);

            for (int i = 0; i < modelList.size(); i++) {
                String name = modelList.get(i).getNameContact();
                String contactNo = modelList.get(i).getNumContact();

                vcfFileWrite.write("BEGIN:VCARD\r\n");
                vcfFileWrite.write("VERSION:3.0\r\n");
                vcfFileWrite.write("FN:" + name + "\r\n");
                vcfFileWrite.write("TEL;TYPE=WORK,VOICE:" + contactNo + "\r\n");
                vcfFileWrite.write("END:VCARD\r\n");
            }

            Toast.makeText(getActivity(), "Created!", Toast.LENGTH_SHORT).show();
            vcfFileWrite.close();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}
