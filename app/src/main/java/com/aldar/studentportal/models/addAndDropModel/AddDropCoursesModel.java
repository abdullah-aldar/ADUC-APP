package com.aldar.studentportal.models.addAndDropModel;

public class AddDropCoursesModel {
    String sectionId,sectionCode , courseCode, courseName,schedule;

    public AddDropCoursesModel(String sectionId, String sectionCode, String courseCode, String courseName, String schedule) {
        this.sectionId = sectionId;
        this.sectionCode = sectionCode;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.schedule = schedule;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


}
