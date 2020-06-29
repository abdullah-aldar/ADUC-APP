package com.aldar.studentportal.models.libraryModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryDataModel {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("callNumber")
    @Expose
    private String callNumber;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalBooks")
    @Expose
    private Integer totalBooks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Integer totalBooks) {
        this.totalBooks = totalBooks;
    }
}
