package com.example.fablab_19.Objects;

import com.google.firebase.database.FirebaseDatabase;

public class Request {
    private String name;
    private String firstName;
    private String email;


    private String companyName;
    private String companyPostalCode;
    private String companyCity;
    private String date;
    private String requestResume;

    public String getStatus() {
        return status;
    }

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequestResume() {
        return requestResume;
    }

    public void setRequestResume(String requestResume) {
        this.requestResume = requestResume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Request(){

    }


    private void sendRequest(){


    }
}
