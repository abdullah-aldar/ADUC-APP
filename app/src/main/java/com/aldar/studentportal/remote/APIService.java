package com.aldar.studentportal.remote;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Abdullah Khan on 01/27/2020.
 */


public interface APIService {

    @FormUrlEncoded
    @POST("api/Login")
    Call<LoginResponseModel> userLogin(@Field("UserName") String email,
                                       @Field("Password") String password);


    @FormUrlEncoded
    @GET
    Call<CourseScheduleResponseModel> getCourseSchedule();

//    @FormUrlEncoded
//    @POST("signup/forgot")
//    Call<ForgotModel> sentEmail(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("signup/checkcode")
//    Call<VerifyCodeModel> verifyCode(@Field("email") String email,
//                                     @Field("code") String code);
//
//    @FormUrlEncoded
//    @POST("signup/resetpassword")
//    Call<ChangePasswordModel> changePassword(@Field("code") String email,
//                                             @Field("password") String code);


}
