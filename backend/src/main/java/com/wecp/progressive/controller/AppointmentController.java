package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Appointment;
import com.wecp.progressive.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService as;


    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(as.getAllAppointments(),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> createAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(as.createAppointment(appointment),HttpStatus.CREATED);
    }
    @PutMapping("/{appointmentId}")
    public ResponseEntity<Void> updateAppointment(@PathVariable int appointmentId, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(appointmentId);
        as.updateAppointment(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Appointment> getAppointmentById(int appointmentId) {
        return null;
    }

    public ResponseEntity<List<Appointment>> getAppointmentByClinic(int clinicId) {
        return null;
    }

    public ResponseEntity<List<Appointment>> getAppointmentByPatient(int patientId) {
        return null;
    }


    @GetMapping("/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentByStatus(@PathVariable String status) {
        return new ResponseEntity<>(as.getAppointmentByStatus(status),HttpStatus.OK);   
    }
}
