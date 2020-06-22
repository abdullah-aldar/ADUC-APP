package com.aldar.studentportal.remote;

import com.aldar.studentportal.models.announcementModel.AnnouncementReponseModel;
import com.aldar.studentportal.models.chequesModels.ChequeResponseModel;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.financeModel.FinanceResponseModel;
import com.aldar.studentportal.models.forgotPasswordModels.ForgotPasswordResponseModel;
import com.aldar.studentportal.models.forgotPasswordModels.UpdatePasswordResponseModel;
import com.aldar.studentportal.models.inboxModels.StudentInboxResponseModel;
import com.aldar.studentportal.models.letterModels.LetterRequestResponseModel;
import com.aldar.studentportal.models.libraryModels.DigitalLibraryResponseModel;
import com.aldar.studentportal.models.libraryModels.LibraryResponseModel;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.models.mymarksmodels.MarksResponseModel;
import com.aldar.studentportal.models.newDataModels.NewsResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.models.registerationModels.RegisterResponseModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.models.studentInfoModels.StudentResponseModel;
import com.aldar.studentportal.models.studentProfileModel.ProfileResponseModel;
import com.aldar.studentportal.models.studyplan.StudyPlanResponseModel;
import com.aldar.studentportal.models.updateProfileModel.UpdateProfileModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
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
                                       @Field("Password") String password,
                                       @Field("token_id") String fcmToken);


    @GET("aduc/StudentCourseScheduleSemester")
    Call<SemesterResponseModel> getSemesterSchedule();

    @GET("aduc/servicerequest")
    Call<LetterRequestResponseModel> getLetterTypes();

    @FormUrlEncoded
    @POST("aduc/ServiceRequest")
    Call<CommonApiResponse> letterRequest(@Field("serviceId") String studendID,
                                          @Field("studentId") String letterID,
                                          @Field("letterTo") String studendNote,
                                          @Field("note") String letterTo);

    @FormUrlEncoded
    @POST("aduc/StudentRegisteredData")
    Call<CourseScheduleResponseModel> getCourseSchedule(@Field("GivenstudentId") String studendID,
                                                        @Field("semId") String semesterID);

    @FormUrlEncoded
    @POST("aduc/StudentProfile")
    Call<ProfileResponseModel> getStudentProfile(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/UpdateStudent")
    Call<UpdateProfileModel> updateProfile(@Field("StudentId") int studendID,
                                           @Field("Type") String type,
                                           @Field("TypeText") String typerText,
                                           @Field("IsOTPVerified") boolean otp);

    @FormUrlEncoded
    @POST("aduc/StudentMarks")
    Call<MarksResponseModel> getStudentMarks(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/Studyplan")
    Call<StudyPlanResponseModel> getStudyPlan(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/StudentFinance")
    Call<FinanceResponseModel> getStudentFinance(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/StudentCourseAdvise")
    Call<CourseAdviceResponseModel> getCourseAdvice(@Field("StudentId") int studendID,
                                                    @Field("SemId") int semesterID);

    @FormUrlEncoded
    @POST("aduc/ForgotPassword")
    Call<ForgotPasswordResponseModel> forgotPassword(@Field("UserName") String username);

    @FormUrlEncoded
    @POST("aduc/UpdatePassword")
    Call<UpdatePasswordResponseModel> updatePassword(@Field("UserName") String username,
                                                     @Field("Password") String password);

    @FormUrlEncoded
    @POST("aduc/getStudentInternalMessage")
    Call<StudentInboxResponseModel> getStudentInbox(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/StudentContacts")
    Call<CommonApiResponse> sendContactsToServer(@Field("token_id") String studendID,
                                                 @Field("ContactName") String contactName,
                                                 @Field("ContactMobile") String contactMobile);


    @FormUrlEncoded
    @POST("aduc/Feedback")
    Call<CommonApiResponse> feedBack(@Field("EmailId") String emailID,
                                     @Field("Message") String message,
                                     @Field("Points") String points);

    @FormUrlEncoded
    @POST("aduc/UserEnquiry")
    Call<CommonApiResponse> userEnquiry(@Field("name") String name,
                                        @Field("email") String email,
                                        @Field("Mobile") String mobile,
                                        @Field("message") String message);

    @FormUrlEncoded
    @POST("aduc/SaveStudentCourseAdvise")
    Call<CommonApiResponse> saveCourseAdvice(@Field("StudentId") String studentID,
                                             @Field("SemId") String semesterID,
                                             @Field("Sections") String sections);

    @FormUrlEncoded
    @POST("aduc/ServiceRequest")
    Call<CommonApiResponse> sendMessage(@Field("StudentId") String studendID,
                                        @Field("SemId") String semID,
                                        @Field("SectionId") String sectionID,
                                        @Field("Subject") String subject,
                                        @Field("Message") String message);

    @FormUrlEncoded
    @POST("aduc/LibraryBooks")
    Call<LibraryResponseModel> getLibraryBooks(@Field("SearchBookBy") String username,
                                               @Field("SearchBookDescription") String password);

    @FormUrlEncoded
    @POST("aduc/Announcement")
    Call<AnnouncementReponseModel> getAnnoucement(@Field("StudentID") String studendID);

    @FormUrlEncoded
    @POST("aduc/StudentData")
    Call<StudentResponseModel> getStudentInfo(@Field("GivenStudentId") String studendID);

    @FormUrlEncoded
    @POST("aduc/StudentCheque")
    Call<ChequeResponseModel> getCheques(@Field("GivenStudentId") String studendID);

    @GET("aduc/DigitalLib")
    Call<DigitalLibraryResponseModel> getDigitalLibrary();

    @GET("aduc/News")
    Call<NewsResponseModel> getNews();


}
