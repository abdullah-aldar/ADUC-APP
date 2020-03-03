package com.aldar.studentportal.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.contactsModel.ContactDataModel;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private FragmentActivity contact;
    private List<ContactDataModel> modelList;
    private List<ContactDataModel> modelListss;


    public static ArrayList<Integer> integersArrayListSelectedContactPosition = new ArrayList<>();
    public static ArrayList<String> stringArrayListContactName = new ArrayList<>();
    public static ArrayList<String> stringArrayListContactNumber = new ArrayList<>();


    public ContactAdapter(FragmentActivity contact, List<ContactDataModel> modelList) {
        this.contact = contact;
        this.modelListss = modelList;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contact);
        View view = layoutInflater.inflate(R.layout.custom_contact_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContactDataModel contactDataModel = modelList.get(position);

        holder.nameContact.setText(contactDataModel.getNameContact());
        holder.numberContact.setText(contactDataModel.getNumContact());
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (stringArrayListContactNumber.contains(contactDataModel.getNumContact())) {
//                    stringArrayListContactName.remove(contactDataModel.getNameContact());
//                    stringArrayListContactNumber.remove(contactDataModel.getNumContact());
//
//                    Log.d("contact", "un checked    " + stringArrayListContactNumber);
//
//                } else {
//                    stringArrayListContactName.add(contactDataModel.getNameContact());
//                    stringArrayListContactNumber.add(contactDataModel.getNumContact());
//                    Log.d("contact", "checked   " + stringArrayListContactNumber);
//                }
//            }
//        });
//        if (stringArrayListContactNumber.contains(contactDataModel.getNumContact())) {
//
//        } else {
//
//        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contact, ""+modelList.get(position).getNameContact(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

     public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameContact;
        TextView numberContact;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numberContact = itemView.findViewById(R.id.contactNum);
            nameContact = itemView.findViewById(R.id.contactName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
