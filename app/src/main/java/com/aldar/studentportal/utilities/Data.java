package com.aldar.studentportal.utilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
     @SerializedName("phone")
     @Expose
     private String phone;
     @SerializedName("email")
     @Expose
     private String email;
     @SerializedName("name")
     @Expose
     private String name;
     @SerializedName("username")
     @Expose
     private String username;
     @SerializedName("userType")
     @Expose
     private String userType;

     @SerializedName("profileImage")
     @Expose
     private String profileImage;
     @SerializedName("rating")
     @Expose
     private Integer rating;
     @SerializedName("isVerified")
     @Expose
     private Boolean isVerified;
     @SerializedName("_id")
     @Expose
     private String id;
     @SerializedName("_cls")
     @Expose
     private String cls;

     public String getPhone() {
         return phone;
     }

     public void setPhone(String phone) {
         this.phone = phone;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getUserType() {
         return userType;
     }

     public void setUserType(String userType) {
         this.userType = userType;
     }


     public String getProfileImage() {
         return profileImage;
     }

     public void setProfileImage(String profileImage) {
         this.profileImage = profileImage;
     }

     public Integer getRating() {
         return rating;
     }

     public void setRating(Integer rating) {
         this.rating = rating;
     }

     public Boolean getIsVerified() {
         return isVerified;
     }

     public void setIsVerified(Boolean isVerified) {
         this.isVerified = isVerified;
     }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getCls() {
         return cls;
     }

     public void setCls(String cls) {
         this.cls = cls;
     }

 }
