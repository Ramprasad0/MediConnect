package com.wecp.progressive.entity;

public class Clinic {

    private int clinic_id;
    private String clinic_name;
    private String location;
    private int doctor_id;
    private String contact_number;
    private int established_year;
    public Clinic() {
    }
    public Clinic(int clinic_id, String clinic_name, String location, int doctor_id, String contact_number,
            int established_year) {
        this.clinic_id = clinic_id;
        this.clinic_name = clinic_name;
        this.location = location;
        this.doctor_id = doctor_id;
        this.contact_number = contact_number;
        this.established_year = established_year;
    }
    public int getClinic_id() {
        return clinic_id;
    }
    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
    }
    public String getClinic_name() {
        return clinic_name;
    }
    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public int getEstablished_year() {
        return established_year;
    }
    public void setEstablished_year(int established_year) {
        this.established_year = established_year;
    }


    
}