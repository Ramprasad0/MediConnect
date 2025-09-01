package com.wecp.progressive.entity;

import java.util.Comparator;

public class Doctor implements Comparable<Doctor>{
private int doctorId;
private String fullName;
private String speciality;
private String contactNumber;
private String email;
private int yearsOfExperience;
public Doctor(int doctorId, String fullName, String speciality, String contactNumber, String email,
        int yearsOfExperience) {
    this.doctorId = doctorId;
    this.fullName = fullName;
    this.speciality = speciality;
    this.contactNumber = contactNumber;
    this.email = email;
    this.yearsOfExperience = yearsOfExperience;
}

public Doctor() {
}

public int getDoctorId() {
    return doctorId;
}
public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
}
public String getFullName() {
    return fullName;
}
public void setFullName(String fullName) {
    this.fullName = fullName;
}
public String getSpecialty() {
    return speciality;
}
public void setSpecialty(String speciality) {
    this.speciality = speciality;
}
public String getContactNumber() {
    return contactNumber;
}
public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public int getYearsOfExperience() {
    return yearsOfExperience;
}
public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
}

@Override
public int compareTo(Doctor o) {
  return Integer.compare(this.yearsOfExperience, o.yearsOfExperience);
}
}