package com.wecp.progressive.service;

import com.wecp.progressive.dto.DoctorDTO;
import com.wecp.progressive.entity.Doctor;

import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctors() throws Exception;

    public Integer addDoctor(Doctor doctor) throws Exception;

    public List<Doctor> getDoctorSortedByExperience() throws Exception;

    default void emptyArrayList() {
    }

   
    default public void updateDoctor(Doctor doctor) throws Exception { }

    default public void deleteDoctor(int doctorId) throws Exception { }

    default Doctor getDoctorById(int doctorId) throws Exception { return null; }
    default public void modifyDoctorDetails(DoctorDTO doctorDTO) { }
}
