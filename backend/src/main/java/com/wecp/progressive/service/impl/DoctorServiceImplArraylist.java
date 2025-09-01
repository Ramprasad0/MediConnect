package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.print.Doc;

import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.service.DoctorService;

public class DoctorServiceImplArraylist implements DoctorService  {

    private static List<Doctor> doctorList = new ArrayList<>();

    @Override
    public void emptyArrayList(){
        doctorList = new ArrayList<>();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }

    @Override
    public Integer addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        return doctorList.size();
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
       List<Doctor> sortedList = doctorList;
       sortedList.sort(Comparator.comparing(Doctor::getYearsOfExperience));
       return sortedList;
    }

}