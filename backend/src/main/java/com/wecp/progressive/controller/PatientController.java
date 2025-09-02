package com.wecp.progressive.controller;
 
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.impl.PatientServiceImplArraylist;
import com.wecp.progressive.service.impl.PatientServiceImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.lang.annotation.Repeatable;
import java.util.List;
 
@RestController
@RequestMapping("/patient")
public class PatientController {
 
    @Autowired
    private PatientServiceImplArraylist patientServiceImplArraylist;
    private PatientServiceImplJpa patientServiceImplJpa;
    // public PatientController(PatientServiceImplArraylist patientServiceImplArraylist, PatientServiceImplJpa patientServiceImplJpa) {
    //     this.patientServiceImplArraylist = patientServiceImplArraylist;
    //     this.patientServiceImplJpa = patientServiceImplJpa;
    // }
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        try{
            return ResponseEntity.ok(patientServiceImplJpa.getAllPatients());
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @GetMapping("/{patientID}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) {
        try{
            return ResponseEntity.ok(patientServiceImplJpa.getPatientById(patientId));
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @PostMapping
    public ResponseEntity<Integer> addPatient(@RequestBody Patient patient) {
        try{
            return ResponseEntity.ok(patientServiceImplJpa.addPatient(patient));
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @PutMapping("/{patientID}")
    public ResponseEntity<?> updatePatient(@PathVariable int patientId, @RequestBody Patient patient) {
        try{
            patientServiceImplArraylist.updatePatient(patient);
            return ResponseEntity.ok("Patient updated successfully");
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @DeleteMapping("/{patientID}")
    public ResponseEntity<?> deletePatient(@PathVariable int patientId) {
        try{
            patientServiceImplArraylist.deletePatient(patientId);
            return ResponseEntity.ok("Patient deleted successfully");
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Patient>> getAllPatientFromArrayList() {
        try{
            return ResponseEntity.ok(patientServiceImplArraylist.getAllPatients());
        }
        catch(Exception e){
            return ResponseEntity.status(500).build();
        }
       
    }
    @PostMapping("/toArrayList")
    public ResponseEntity<?> addPatientToArrayList(@RequestBody Patient patient) {
        try{
            patientServiceImplArraylist.addPatient(patient);
            return ResponseEntity.status(201).build();
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
 
    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Patient>> getAllPatientSortedByNameFromArrayList() {
        try{
            return ResponseEntity.ok(patientServiceImplArraylist.getAllPatientSortedByName());
        }
        catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}