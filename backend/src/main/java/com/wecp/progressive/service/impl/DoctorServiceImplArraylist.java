package com.wecp.progressive.service.impl;

import java.util.List;

import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.service.DoctorService;

public class DoctorServiceImplArraylist implements DoctorService  {

    @Override
    public void emptyArrayList(){

    }

    @Override
    public List<Doctor> getAllDoctors() {
        return List.of();
    }

    @Override
    public Integer addDoctor(Doctor doctor) {
        return -1;
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
       return List.of();
    }

}