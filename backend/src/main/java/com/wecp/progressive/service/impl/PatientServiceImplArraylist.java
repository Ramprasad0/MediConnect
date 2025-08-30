package com.wecp.progressive.service.impl;

import java.util.List;

import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.PatientService;

public class PatientServiceImplArraylist implements PatientService{

    @Override
    public List<Patient> getAllPatients() {
        return List.of();
    }

    @Override
    public Integer addPatient(Patient patient) {
       return -1;
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
       return List.of();
    }

    @Override
    public void emptyArrayList(){
        
    }

}