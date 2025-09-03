package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.PatientService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PatientServiceImplArraylist implements PatientService {

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
        List<Patient> sortedPatients = patientList;
        // sortedPatients.sort(Comparator.comparing(Patient::getFullName));
        Collections.sort(sortedPatients);
        return sortedPatients;
    }

    @Override
    public void emptyArrayList() {
        patientList = new ArrayList<>();
    }
}
