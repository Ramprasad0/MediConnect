package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Appointment;
import com.wecp.progressive.entity.Clinic;
import com.wecp.progressive.entity.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{
    List<Appointment> findByStatus(String status);
    // List<Appointment> findByEmail(String email);
    // List<Appointment> findByClinic(Clinic clinic);
}
