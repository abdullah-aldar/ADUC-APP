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
    @POST("api/StudentRegisteredData")
    Call<CourseScheduleResponseModel> getCourseSchedule(@Field("GivenstudentId") String studendID,
                                       @Field("semId") String semesterID);




}
