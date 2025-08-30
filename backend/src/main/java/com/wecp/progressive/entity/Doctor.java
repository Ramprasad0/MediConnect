package com.wecp.progressive.entity;

public class Doctor {
    private int doctor_id; 
    private String full_name;
    private String speciality;
    private String contact_number;
    private String eamil;
    private int years_of_experience;
    public Doctor() {
    }
  
    public Doctor(int doctor_id, String full_name, String speciality, String contact_number, String eamil,
            int years_of_experience) {
        this.doctor_id = doctor_id;
        this.full_name = full_name;
        this.speciality = speciality;
        this.contact_number = contact_number;
        this.eamil = eamil;
        this.years_of_experience = years_of_experience;
    }

    public String getFull_name() {
        return full_name;
    }
    public String getSpeciality() {
        return speciality;
    }
    public String getContact_number() {
        return contact_number;
    }
    public String getEamil() {
        return eamil;
    }
    public int getYears_of_experience() {
        return years_of_experience;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public void setEamil(String eamil) {
        this.eamil = eamil;
    }
    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    

}