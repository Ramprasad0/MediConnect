package com.wecp.progressive.service.impl;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.wecp.progressive.dao.PatientDAO;
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.PatientService;

public class PatientServiceImplJdbc implements PatientService {

    private PatientDAO patientDAO;

    public PatientServiceImplJdbc (PatientDAO patientDAO){
        this.patientDAO=patientDAO;
    }

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
    public void updatePatient(Patient patient){

    }

    @Override
    public void deletePatient(int patientId){

    }

    @Override
    public Patient getPatientById(int patientId){
        return null;
    }

}