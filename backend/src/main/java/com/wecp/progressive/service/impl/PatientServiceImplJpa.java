package com.wecp.progressive.service.impl;
 
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.repository.PatientRepository;
import com.wecp.progressive.service.PatientService;
 
@Service
public class PatientServiceImplJpa implements PatientService {
    
@Autowired
PatientRepository pr;
public PatientServiceImplJpa(PatientRepository pr) {
    this.pr = pr;
}
@Override
public List<Patient> getAllPatients() throws Exception {
  return pr.findAll();
}
 
@Override
public Patient getPatientById(int patientId) {
    try {
        return pr.findById(patientId).orElseThrow();
    } catch (Exception e) {
        return null;
    }
}
 
    @Override
    public Integer addPatient(Patient patient) throws Exception {
      return pr.save(patient).getPatientId();
    }
 
    public void updatePatient(Patient patient) throws Exception {
        Patient p=pr.findById(patient.getPatientId()).orElseThrow();
        p.setAddress(patient.getAddress());
        p.setContactNumber(patient.getContactNumber());
        p.setDateOfBirth(patient.getDateOfBirth());
        p.setEmail(patient.getEmail());
        p.setFullName(patient.getFullName());
        pr.save(p);
   
   }
 
   @Override
   public void deletePatient(int patientId) throws Exception {
   pr.deleteById(patientId);
   }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
            List<Patient> sortedPatients = pr.findAll();
            if (!sortedPatients.isEmpty()) {
                sortedPatients.sort(Comparator.comparing(Patient::getFullName));
            }
            return sortedPatients;
        

}
}