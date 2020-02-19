package com.aldar.studentportal.ui.fragments.signUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentCreateStudentBinding;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.ui.fragments.login.LoginFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class CreateStudentFragment extends Fragment {
    private String strFcmToken=null;
    private FragmentCreateStudentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_student,container,false);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CreateStudentViewModel createStudentViewModel = new ViewModelProvider(this).get(CreateStudentViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCreateStudentViewModel(createStudentViewModel);

        createStudentViewModel.userName.setValue(GeneralUtilities.getSharedPreferences(getActivity()).getString("student_username",""));
        createStudentViewModel.fcmToken.setValue(getFcmToken());

        createStudentViewModel.createUserLiveData().observe(getViewLifecycleOwner(), new Observer<CommonApiResponse>() {
            @Override
            public void onChanged(CommonApiResponse commonApiResponse) {
                if(Boolean.parseBoolean(commonApiResponse.getSuccess())){
                    GeneralUtilities.connectFragmentWithBack(getContext(), new LoginFragment());
                }
            }
        });
    }

    private String getFcmToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Error", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        strFcmToken = task.getResult().getToken();

                    }
                });

        return strFcmToken;
    }


}
