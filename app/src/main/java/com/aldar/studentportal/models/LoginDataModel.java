package com.aldar.studentportal.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("goal")
    @Expose
    private String goal;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("terms_acceptance")
    @Expose
    private String termsAcceptance;
    @SerializedName("target_weight")
    @Expose
    private String targetWeight;
    @SerializedName("target_description")
    @Expose
    private String targetDescription;
    @SerializedName("relation_status")
    @Expose
    private String relationStatus;
    @SerializedName("no_childs")
    @Expose
    private String noChilds;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("hours_per_week")
    @Expose
    private String hoursPerWeek;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("current_living")
    @Expose
    private String currentLiving;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("health_parents")
    @Expose
    private String healthParents;
    @SerializedName("sleep")
    @Expose
    private String sleep;
    @SerializedName("allergies")
    @Expose
    private String allergies;
    @SerializedName("serious_illness")
    @Expose
    private String seriousIllness;
    @SerializedName("major_addiction")
    @Expose
    private String majorAddiction;
    @SerializedName("medications")
    @Expose
    private String medications;
    @SerializedName("therapies")
    @Expose
    private String therapies;
    @SerializedName("interests")
    @Expose
    private String interests;
    @SerializedName("preferred_style")
    @Expose
    private String preferredStyle;
    @SerializedName("email_token")
    @Expose
    private String emailToken;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("user_role")
    @Expose
    private String userRole;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("device")
    @Expose
    private String device;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTermsAcceptance() {
        return termsAcceptance;
    }

    public void setTermsAcceptance(String termsAcceptance) {
        this.termsAcceptance = termsAcceptance;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
    }

    public String getNoChilds() {
        return noChilds;
    }

    public void setNoChilds(String noChilds) {
        this.noChilds = noChilds;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(String hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrentLiving() {
        return currentLiving;
    }

    public void setCurrentLiving(String currentLiving) {
        this.currentLiving = currentLiving;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHealthParents() {
        return healthParents;
    }

    public void setHealthParents(String healthParents) {
        this.healthParents = healthParents;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getSeriousIllness() {
        return seriousIllness;
    }

    public void setSeriousIllness(String seriousIllness) {
        this.seriousIllness = seriousIllness;
    }

    public String getMajorAddiction() {
        return majorAddiction;
    }

    public void setMajorAddiction(String majorAddiction) {
        this.majorAddiction = majorAddiction;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getTherapies() {
        return therapies;
    }

    public void setTherapies(String therapies) {
        this.therapies = therapies;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getPreferredStyle() {
        return preferredStyle;
    }

    public void setPreferredStyle(String preferredStyle) {
        this.preferredStyle = preferredStyle;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}

