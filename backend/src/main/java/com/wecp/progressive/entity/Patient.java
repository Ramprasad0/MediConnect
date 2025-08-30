package com.wecp.progressive.entity;

import java.util.Date;

public class Patient {
    private int patient_id;
    private Date date_of_birth;
    private String contact_number;
    private String eamil;
    private String address;
    public Patient() {
    }
    public Patient(int patient_id, Date date_of_birth, String contact_number, String eamil, String address) {
        this.patient_id = patient_id;
        this.date_of_birth = date_of_birth;
        this.contact_number = contact_number;
        this.eamil = eamil;
        this.address = address;
    }
    public int getId() {
        return patient_id;
    }
    public void setId(int patient_id) {
        this.patient_id = patient_id;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public String getEamil() {
        return eamil;
    }
    public void setEamil(String eamil) {
        this.eamil = eamil;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}