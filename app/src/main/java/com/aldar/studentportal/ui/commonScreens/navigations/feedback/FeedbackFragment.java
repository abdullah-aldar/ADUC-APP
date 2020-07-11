package com.aldar.studentportal.ui.commonScreens.navigations.feedback;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FeedbackFragmentBinding;
import com.aldar.studentportal.utilities.ServiceId;
import com.aldar.studentportal.utilities.MainObjectClass;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackFragment extends Fragment {
    private FeedbackFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.feedback_fragment,container,false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FeedbackViewModel mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setFeedbackViewModel(mViewModel);

        mViewModel.getfeedBackData().observe(getViewLifecycleOwner(),commonApiResponse -> {
            if(commonApiResponse != null){
                Toast.makeText(getContext(), ""+commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnFeedback.setOnClickListener(v -> {
//            mViewModel.stars.setValue(binding.rattingbar.getRating());
//            mViewModel.onClick();

            ServiceId serviceId = new ServiceId();
            MainObjectClass mainObjectClass;
            JsonArray list = null;

            JsonObject jsonOBJ = new JsonObject();
            try {
                 list = new JsonArray();
                jsonOBJ.addProperty("userId", "5f06ca75ce77e8e2cdd2f320");



                String[] dataArray = {"5eeb4d5f2d4f7288d8959f68","5eeb5b942b567b6a8fee6b00"};
                for( int i=0;i<dataArray.length;i++) {
                    JsonObject internalObject = new JsonObject();
                    internalObject.addProperty("serviceId",dataArray[i]);
                    list.add(internalObject);

                }
                serviceId.setServiceId("5eeb4d5f2d4f7288d8959f68");
                jsonOBJ.add("services", list);


            } catch (Exception e) {
                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


            mViewModel.apiCallArray(jsonOBJ);

        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
