package com.aldar.studentportal.ui.fragments.navigations;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.ui.fragments.navigations.home.HomeViewModel;
import com.aldar.studentportal.utilities.PermissionUtil;
import com.aldar.studentportal.worker.MyWorker;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllumniFragment extends Fragment {
    private HomeViewModel viewModel;

    private  ArrayList<ContactDataModel> contactList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_allumni, container, false);

//        final PeriodicWorkRequest periodicWorkRequest
//                = new PeriodicWorkRequest.Builder(MyWorker.class, 5, TimeUnit.SECONDS)
//                .build();
//        WorkManager.getInstance(getActivity()).enqueue(periodicWorkRequest);

        return root;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        if (PermissionUtil.isContactPermissionGranted(getActivity())) {
            //getContactsIntoArrayList();
        }

        sendContact();

        viewModel.getContact().observe(getViewLifecycleOwner(), new Observer<CommonApiResponse>() {
            @Override
            public void onChanged(CommonApiResponse commonApiResponse) {
                if (commonApiResponse != null) {
                    Toast.makeText(getActivity(), "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void sendContact() {
        disposables.add(Observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<ContactDataModel>>() {
                    @Override
                    public void onComplete() { }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ArrayList<ContactDataModel> contactList) {
                        String strName=null, strNumber=null;
                        if(contactList.size()>0){
                            for(int k=0;k<contactList.size();k++){
                                if (k == 0) {
                                    strName += contactList.get(k).getNameContact();
                                    strNumber += contactList.get(k).getNumContact();
                                } else {
                                    strName += "," + contactList.get(k).getNameContact();
                                    strNumber += "," + contactList.get(k).getNumContact();
                                }
                            }
                           // viewModel.sendContactToServer(contactList.get(0).getNameContact(),"03459055");
                        }
                    }
                }));
    }

    Observable<ArrayList<ContactDataModel>> Observable() {
        return Observable.defer((Supplier<Observable<ArrayList<ContactDataModel>>>) () -> {
            // Do some long running operation
            return Observable.just(getContactsIntoArrayList());
        });
    }

    private ArrayList<ContactDataModel> getContactsIntoArrayList() {
        contactList = new ArrayList<>();
        String name,phoneNumber;

        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactDataModel contactModel = new ContactDataModel();
            contactModel.setNameContact(name);
            contactModel.setNumContact(phoneNumber);

            contactList.add(contactModel);

        }
        cursor.close();
        return contactList;
    }

    public void GetFiles() {
        contactList = new ArrayList<>();

        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ADUC/abdullah.vcf");

        f.mkdirs();
        File[] files = f.listFiles();

        if (files == null || files.length < 0) {
            Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                for (int i = 0; i < files.length; i++) {
                    ContactDataModel number = new ContactDataModel();
                    number.setNameContact(files[i].getName());
                    number.setNumContact(files[i].getName());
                    contactList.add(number);
                }

            } catch (Exception e) {
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
