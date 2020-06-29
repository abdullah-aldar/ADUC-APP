package com.aldar.studentportal.ui.fragments.navigations.feedback;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> comment = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<Float> stars = new MutableLiveData<>();
    public MutableLiveData<String> errorMessageComment = new MutableLiveData<>();
    public MutableLiveData<String> errorMessageEmail = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> feedBackData = new MutableLiveData<>();
    private boolean valid = false;



    public FeedbackViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick() {
        if (validate()) {
            apiCallFeedback();
        }
    }

    private void apiCallFeedback(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.feedBack(email.getValue(),comment.getValue(),String.valueOf(stars.getValue()));
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    feedBackData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<CommonApiResponse> getfeedBackData(){
        return feedBackData;
    }


    private boolean validate() {
        valid = true;

        if (comment.getValue() == null || comment.getValue().isEmpty()) {
            valid = false;
            errorMessageComment.setValue("Please enter your comment");
        }

        if(email.getValue() == null || email.getValue().isEmpty()){
           // email.setValue("");
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches()) {
            valid = false;
            errorMessageEmail.setValue("Please enter a valid email");
        }

        if (stars.getValue() == null || stars.getValue() == 0) {
           showToast(getApplication().getApplicationContext(),"Please give us stars");
            valid = false;
        }

        return valid;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

}
