package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Appointment;
import com.wecp.progressive.repository.AppointmentRepository;
import com.wecp.progressive.service.AppointmentService;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepository ar;
    @Override
    public List<Appointment> getAllAppointments() {
     return ar.findAll();
    }

    @Override
    public int createAppointment(Appointment appointment) {
       return ar.save(appointment).getAppointmentId();
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Appointment a=ar.findById(appointment.getAppointmentId()).orElseThrow();
        a.setAppointmentDate(appointment.getAppointmentDate());
        a.setClinic(appointment.getClinic());
        a.setPatient(appointment.getPatient());
        a.setPurpose(appointment.getPurpose());
        a.setStatus(appointment.getStatus());
        ar.save(a);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return ar.findById(appointmentId).orElseThrow();
    }

    @Override
    public List<Appointment> getAppointmentByClinic(int clinicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppointmentByClinic'");
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppointmentByPatient'");
    }

    @Override
    public List<Appointment> getAppointmentByStatus(String status) {
      return ar.findByStatus(status);
    }

}