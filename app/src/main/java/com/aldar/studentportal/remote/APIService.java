package com.aldar.studentportal.remote;
import com.aldar.studentportal.models.LoginResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Abdullah Khan on 01/27/2020.
 */


public interface APIService {

    @FormUrlEncoded
    @POST("api/Login")
    Call<LoginResponseModel> userLogin(@Field("UserName") String email,
                                       @Field("Password") String password);

//    @FormUrlEncoded
//    @POST("signup/register")
//    Call<SignupResponseModel> userSignup(@Field("first_name") String firstname,
//                                         @Field("last_name") String lastname,
//                                         @Field("email") String email,
//                                         @Field("password") String pass,
//                                         @Field("interest_id") String interestID,
//                                         @Field("style") String style,
//                                         @Field("goal") String goal,
//                                         @Field("dob") String dob,
//                                         @Field("terms") String terms,
//                                         @Field("device") String device,
//                                         @Field("device_id") String device_id);
//
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
