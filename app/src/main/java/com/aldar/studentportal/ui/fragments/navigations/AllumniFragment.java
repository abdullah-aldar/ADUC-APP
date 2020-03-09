package com.aldar.studentportal.ui.fragments.navigations;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Environment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.ContactAdapter;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import com.aldar.studentportal.ui.fragments.navigations.home.HomeViewModel;
import com.aldar.studentportal.utilities.PermissionUtil;
import com.aldar.studentportal.worker.MyWorker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class AllumniFragment extends Fragment {
    private List<ContactDataModel> modelList;
    private String name, phoneNumber;
    private  ContactAdapter adapter;
    private RecyclerView recyclerViewContact;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_allumni, container, false);
        recyclerViewContact = root.findViewById(R.id.recyclerViewContact);
        PermissionUtil.rxtra(getActivity());
        if (PermissionUtil.rxtra(getActivity())) {
            getContactsIntoArrayList();
        }

        final PeriodicWorkRequest periodicWorkRequest
                = new PeriodicWorkRequest.Builder(MyWorker.class, 5, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(getActivity()).enqueue(periodicWorkRequest);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getContactsIntoArrayList() {
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        modelList = new ArrayList<>();
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContact.setHasFixedSize(true);

       Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactDataModel number = new ContactDataModel();
            number.setNameContact(name);
            number.setNumContact(phoneNumber);
            modelList.add(number);
//            adapter = new ContactAdapter(getActivity(),modelList);
//            recyclerViewContact.setAdapter(adapter);
//            adapter.notifyDataSetChanged();

        }
        generateVCFFile(modelList);
        cursor.close();

    }

    public void GetFiles() {
        modelList = new ArrayList<>();
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContact.setHasFixedSize(true);

        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/sample.vcf");

        f.mkdirs();
        File[] files = f.listFiles();

        if (files == null || files.length < 0){
            Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
        }
        else {
           try {
               for (int i = 0; i < files.length; i++){
                   ContactDataModel number = new ContactDataModel();
                   number.setNameContact(files[i].getName());
                   number.setNumContact(files[i].getName());
                   modelList.add(number);
                   adapter = new ContactAdapter(getActivity(), modelList);
                   recyclerViewContact.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
               }

           }
           catch (Exception e){
               Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
           }

        }
    }

    private void generateVCFFile(List<ContactDataModel> modelList){
        try {
            File directory = new File(
                    Environment.getExternalStorageDirectory() + "/ADUC");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File vcfFile = new File(directory, "abdullah.vcf");

            FileWriter vcfFileWrite = null;
            vcfFileWrite = new FileWriter(vcfFile);

            for(int i =0;i<modelList.size();i++){
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
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
