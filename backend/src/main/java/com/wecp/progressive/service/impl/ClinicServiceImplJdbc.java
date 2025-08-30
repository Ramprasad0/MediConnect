package com.wecp.progressive.service.impl;

import java.util.List;

import com.wecp.progressive.dao.ClinicDAO;
import com.wecp.progressive.entity.Clinic;
import com.wecp.progressive.service.ClinicService;

public class ClinicServiceImplJdbc implements ClinicService{

    private  ClinicDAO clinicDAO;

    public ClinicServiceImplJdbc(ClinicDAO clinicDAO){
        this.clinicDAO=clinicDAO;
    }


    @Override
    public List<Clinic> getAllClinics() {
       return List.of();
    }

    @Override
    public Clinic getClinicById(int clinicId) {
        return null;
    }

    @Override
    public Integer addClinic(Clinic clinic) {
        return -1;
    }

    @Override
    public void updateClinic(Clinic clinic) {
       
    }

    @Override
    public void deleteClinic(int clinicId) {
       
    }

}