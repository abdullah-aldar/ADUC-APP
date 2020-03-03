package com.aldar.studentportal.ui.fragments.navigations;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.ContactAdapter;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import com.aldar.studentportal.utilities.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

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

        if (PermissionUtil.isContactPermissionGranted(getActivity())) {
            getContactsIntoArrayList();
        }
        return root;
    }

    private void getContactsIntoArrayList() {
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
            adapter = new ContactAdapter(getActivity(),modelList);
            recyclerViewContact.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        cursor.close();

    }

}
