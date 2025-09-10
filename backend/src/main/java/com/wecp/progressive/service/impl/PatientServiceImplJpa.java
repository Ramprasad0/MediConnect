package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Clinic;
import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.exception.PatientAlreadyExistsException;
import com.wecp.progressive.exception.PatientNotFoundException;
import com.wecp.progressive.repository.PatientRepository;
import com.wecp.progressive.service.PatientService;
@Service
public class PatientServiceImplJpa  implements PatientService{
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
    public Integer addPatient(Patient patient) throws Exception {
      List<Patient> res=pr.findByEmail(patient.getEmail());
      if(res.size()>0){
        throw new PatientAlreadyExistsException();
      }
      return pr.save(patient).getPatientId();
      
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
            List<Patient> sortedPatients = pr.findAll();
            if (!sortedPatients.isEmpty()) {
                sortedPatients.sort(Comparator.comparing(Patient::getFullName));
            }
            return sortedPatients;
        

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
    public Patient getPatientById(int patientId) throws Exception {
  //  try {
  //   return pr.findById(patientId).orElseThrow();
  //  } catch (Exception e) {
  //   return null;
  //  }
  
    return pr.findById(patientId).orElseThrow(()->new PatientNotFoundException());
  
 
    }

    @Override
    public void deletePatient(int patientId) throws Exception {
    pr.deleteById(patientId);
    }
}