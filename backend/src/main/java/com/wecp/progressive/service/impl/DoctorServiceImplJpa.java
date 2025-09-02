package com.wecp.progressive.service.impl;

import java.util.List;

import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.service.DoctorService;

public class DoctorServiceImplJpa implements DoctorService {

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
       return List.of();
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        return -1;
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
      return List.of();
    }

}