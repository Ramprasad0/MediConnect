package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

    List<Doctor> findByEmail(String email);
}