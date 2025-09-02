package com.wecp.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Doctor;
import com.wecp.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO {

    @Override
public int addPatient(Patient  patient) throws SQLException {
   int generatedId=-1;
    String sql = "INSERT INTO patient (full_name, date_of_birth,contact_number,email,address) VALUES (?, ?,?,?,?)";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, patient.getFullName());
        stmt.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
        stmt.setString(3, patient.getContactNumber());
        stmt.setString(4, patient.getEmail());
        stmt.setString(5, patient.getAddress());
        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                generatedId = rs.getInt(1);
                patient.setPatientId(generatedId);
            }
        }
    } catch (SQLException e) {
      System.err.println("Error adding doctor: "+e.getMessage());
         throw e;
    }
    return generatedId;
}

@Override
public Patient getPatientById(int patientId) throws SQLException{
   String sql = "SELECT * FROM patient WHERE patient_id = ?";
   try(Connection conn = DatabaseConnectionManager.getConnection();
   PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
     
stmt.setInt(1, patientId);
try (ResultSet rs = stmt.executeQuery()) {
if (rs.next()) {
    return new Patient(
        patientId,
        rs.getString("full_name"),
        rs.getDate("date_of_birth"),
        rs.getString("contact_number"),
        rs.getString("email"),
        rs.getString("address")
    );
}
}

   }catch(SQLException e){
    System.err.println("Error fetching doctor by ID: " + e.getMessage());
    throw e;
   }
   return null;
}

@Override
public void updatePatient(Patient patient) throws SQLException {
    String sql = "UPDATE patient SET full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address = ? WHERE patient_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, patient.getFullName());
        stmt.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
        stmt.setString(3, patient.getContactNumber());
        stmt.setString(4, patient.getEmail());
        stmt.setString(5, patient.getAddress());
        stmt.setInt(6, patient.getPatientId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error updating doctor: " + e.getMessage());
        throw e;
    }
}

@Override
public void deletePatient(int patientId) throws SQLException {
    String sql = "DELETE FROM patient WHERE patient_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, patientId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error deleting doctor: " + e.getMessage());
        throw e;
    }
}

  @Override
public List<Patient> getAllPatients() throws SQLException {
    String sql = "SELECT * FROM patient";
    List<Patient> patients = new ArrayList<>();
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            patients.add(new Patient(
                rs.getInt("patient_id"),
                rs.getString("full_name"),
                rs.getDate("date_of_birth"),
                rs.getString("contact_number"),
                rs.getString("email"),
                rs.getString("address")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error fetching all clinics: " + e.getMessage());
        throw e;
    }
    return patients;
}
}
