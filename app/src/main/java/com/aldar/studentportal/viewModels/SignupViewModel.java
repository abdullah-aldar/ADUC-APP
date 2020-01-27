package com.aldar.studentportal.viewModels;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.aldar.studentportal.models.LoginResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private boolean valid = false;


    public SignupViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick(View view) {
        if(validate()){
            //apiCallUpdatePassword();
        }
    }

//    private void apiCallUpdatePassword(){
//        progressBar.setValue(0);
//        APIService services = RetroClass.getApiClient().create(APIService.class);
//        Call<LoginResponseModel> allUsers = services.userLogin(studentID.getValue(),studentID.getValue());
//        allUsers.enqueue(new Callback<LoginResponseModel>() {
//            @Override
//            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
//                progressBar.setValue(8);
//                if (response.body() == null) {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        Toast.makeText(getApplication().getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
//                    } catch (Exception e) {
//                        Log.d("", e.getMessage());
//                    }
//
//                } else  {
//                    Toast.makeText(getApplication().getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
//                progressBar.setValue(8);
//                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private boolean validate() {
        valid = true;

        if (studentID.getValue() == null || studentID.getValue().isEmpty()) {
            valid = false;
            errorMessage.setValue("Please enter your username");
        }


        if (studentID.getValue() == null || studentID.getValue().isEmpty()) {
            valid = false;
            errorMessage.setValue("Please confirm your password");
        }

        return valid;
    }
}
