package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Billing;
import com.wecp.progressive.entity.Patient;
import com.wecp.progressive.service.BillingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService bs;

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBills() {
        return new ResponseEntity<>(bs.getAllBills(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createBill(@RequestBody Billing billing) {
        return new ResponseEntity<>(bs.createBill(billing), HttpStatus.CREATED);
    }

    @DeleteMapping("/{billingId}")
    public ResponseEntity<Integer> deleteBill(@PathVariable int billingId) {
        bs.deleteBill(billingId);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{billingId}")
    public ResponseEntity<Billing> getBillsByBillingID(@PathVariable int billingId) {
        return ResponseEntity.ok(bs.getBillById(billingId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Billing>> getBillsByPatient(@PathVariable int patientId) {
        return ResponseEntity.ok(bs.getBillsByPatientId(patientId));
    }
}