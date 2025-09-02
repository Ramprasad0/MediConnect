package com.wecp.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Clinic;
import com.wecp.progressive.repository.ClinicRepository;
import com.wecp.progressive.service.ClinicService;

@Service
public class ClinicServiceImplJpa implements ClinicService {

    @Override
    public List<Clinic> getAllClinics() throws Exception {
       return List.of();
    }

    @Override
    public Clinic getClinicById(int clinicId) throws Exception {
       return null;
    }

    @Override
    public Integer addClinic(Clinic clinic) throws Exception {
      return -1;
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception {
       
    }

    @Override
    public void deleteClinic(int clinicId) throws Exception {
       
    }


}