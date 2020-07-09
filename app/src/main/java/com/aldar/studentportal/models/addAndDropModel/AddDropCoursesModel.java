package com.aldar.studentportal.models.addAndDropModel;

public class AddDropCoursesModel {
    String  courseCode, courseName,sectionId,section ,creditHour,invoice ,semesterID,addOrDrop;

    public AddDropCoursesModel(String courseCode, String courseName, String sectionId, String section, String creditHour, String invoice, String semesterID, String addOrDrop) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.sectionId = sectionId;
        this.section = section;
        this.creditHour = creditHour;
        this.invoice = invoice;
        this.semesterID = semesterID;
        this.addOrDrop = addOrDrop;
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

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(String creditHour) {
        this.creditHour = creditHour;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(String semesterID) {
        this.semesterID = semesterID;
    }

    public String getAddOrDrop() {
        return addOrDrop;
    }

    public void setAddOrDrop(String addOrDrop) {
        this.addOrDrop = addOrDrop;
    }
}
