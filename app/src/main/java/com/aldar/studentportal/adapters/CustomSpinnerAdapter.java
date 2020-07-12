package com.aldar.studentportal.adapters;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;

import org.jetbrains.annotations.NotNull;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    Context context;
    String[] list;
    String firstElement;
    boolean isFirstTime;

    public CustomSpinnerAdapter(Context context, int textViewResourceId, String[] list, String defaultText) {
        super(context, textViewResourceId, list);
        this.context = context;
        this.list = list;
         this.isFirstTime = true;
        setDefaultText(defaultText);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
        if (isFirstTime) {
            list[0] = firstElement;
            isFirstTime = false;
        }
        return getCustomView(position, convertView, parent);
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        notifyDataSetChanged();
        return getCustomView(position, convertView, parent);
    }

    public void setDefaultText(String defaultText) {
        this.firstElement = list[0];
        //list[0] = defaultText;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.spinner_layout, parent, false);
        TextView textView = convertView.findViewById(R.id.spinner_text);
        textView.setText(list[position]);

        if(isFirstTime){

        }
        return convertView;
    }

}