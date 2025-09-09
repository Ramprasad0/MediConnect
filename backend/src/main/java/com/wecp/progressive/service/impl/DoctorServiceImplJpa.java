package com.wecp.progressive.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.exception.DoctorAlreadyExistsException;
import com.wecp.progressive.exception.PatientAlreadyExistsException;
import com.wecp.progressive.repository.DoctorRepository;
import com.wecp.progressive.repository.PatientRepository;
import com.wecp.progressive.service.DoctorService;
import com.wecp.progressive.service.PatientService;
@Service
public class DoctorServiceImplJpa  implements DoctorService{
    @Autowired
    DoctorRepository dr;
    
    
    public DoctorServiceImplJpa(DoctorRepository dr) {
        this.dr = dr;
    }
    public DoctorServiceImplJpa() {
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
       return dr.findAll();
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
            List<Doctor> res=dr.findByEmail(doctor.getEmail());
      if(res.size()>0){
        throw new DoctorAlreadyExistsException();
      }
       return dr.save(doctor).getDoctorId();
       
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
       List<Doctor> sortedDoctors = dr.findAll();
            if (!sortedDoctors.isEmpty()) {
                sortedDoctors.sort(Comparator.comparing(Doctor::getYearsOfExperience));
            }
            return sortedDoctors;
    }
    @Override
    public void updateDoctor(Doctor doctor) throws Exception {
      Doctor d=dr.findById(doctor.getDoctorId()).orElseThrow();
        d.setContactNumber(doctor.getContactNumber());
        d.setEmail(doctor.getEmail());
        d.setFullName(doctor.getFullName());
        d.setSpecialty(doctor.getSpecialty());
        d.setYearsOfExperience(doctor.getYearsOfExperience());
         dr.save(d);
    }
    @Override
    public void deleteDoctor(int doctorId) throws Exception {
      dr.deleteById(doctorId);
    }
    @Override
    public Doctor getDoctorById(int doctorId) throws Exception {
        try {
            return dr.findById(doctorId).orElseThrow();
           } catch (Exception e) {
            return null;
           }
    }

}