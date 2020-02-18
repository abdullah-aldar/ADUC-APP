package com.aldar.studentportal.remote;

import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.forgotPasswordModels.ForgotPasswordResponseModel;
import com.aldar.studentportal.models.forgotPasswordModels.UpdatePasswordResponseModel;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.models.registerationModels.RegisterResponseModel;

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
    @POST("aduc/RegisterUser")
    Call<RegisterResponseModel> registerUser(@Field("UserName") String username);

    @FormUrlEncoded
    @POST("aduc/CreateUser")
    Call<CommonApiResponse> createUser(@Field("UserName") String username,
                                       @Field("Password") String password,
                                       @Field("Token_Id") String fcmToken);

    @FormUrlEncoded
    @POST("aduc/Login")
    Call<LoginResponseModel> userLogin(@Field("UserName") String email,
                                       @Field("Password") String password);

    @FormUrlEncoded
    @POST("aduc/StudentRegisteredData")
    Call<CourseScheduleResponseModel> getCourseSchedule(@Field("GivenstudentId") String studendID,
                                                        @Field("semId") String semesterID);

    @FormUrlEncoded
    @POST("aduc/ForgotPassword")
    Call<ForgotPasswordResponseModel> forgotPassword(@Field("UserName") String username);

    @FormUrlEncoded
    @POST("aduc/UpdatePassword")
    Call<UpdatePasswordResponseModel> updatePassword(@Field("UserName") String username,
                                                     @Field("Password") String password);


}
