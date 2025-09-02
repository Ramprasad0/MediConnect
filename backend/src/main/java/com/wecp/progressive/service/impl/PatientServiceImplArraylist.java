package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.propertyeditors.PathEditor;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.PatientService;

@Service
public class PatientServiceImplArraylist implements PatientService{

    private static List<Patient> patientList = new ArrayList<>();

    @Override
    public List<Patient> getAllPatients() {
        return patientList;
    }

    @Override
    public Integer addPatient(Patient patient) {
       patientList.add(patient);
       return patientList.size();
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
        List<Patient> sortedList = patientList;
       sortedList.sort(Comparator.comparing(Patient::getFullName));
       return sortedList;
    }

    @Override
    public void emptyArrayList(){
        patientList = new ArrayList<>();
    }

}