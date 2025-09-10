package com.wecp.progressive.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Billing;
import com.wecp.progressive.entity.Patient;

import java.util.List;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>{
    List<Billing> findByPatient_PatientId(int patientId);

    // @Modifying
    // @Transactional
    // @Query("delete from billing b where b.patient.patientId = :patientid")
    // void deleteByPatientId(@Param("patientId") int patientid);
}