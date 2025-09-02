package com.wecp.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Clinic;
import com.wecp.progressive.entity.Doctor;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
public int addDoctor(Doctor  doctor) throws SQLException {
   int generatedId=-1;
    String sql = "INSERT INTO doctor (full_name, specialty,contact_number,email,years_of_experience) VALUES (?,?,?,?,?)";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, doctor.getFullName());
        stmt.setString(2, doctor.getSpecialty());
        stmt.setString(3, doctor.getContactNumber());
        stmt.setString(4, doctor.getEmail());
        stmt.setInt(5, doctor.getYearsOfExperience());
        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                generatedId = rs.getInt(1);
                doctor.setDoctorId(generatedId);
            }
        }
    } catch (SQLException e) {
      System.err.println("Error adding doctor: "+e.getMessage());
         throw e;
    }
    return generatedId;
}

@Override
public Doctor getDoctorById(int doctorId) throws SQLException{
   String sql = "SELECT * FROM doctor WHERE doctor_id = ?";
   try(Connection conn = DatabaseConnectionManager.getConnection();
   PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
     
stmt.setInt(1, doctorId);
try (ResultSet rs = stmt.executeQuery()) {
if (rs.next()) {
    return new Doctor(
        doctorId,
        rs.getString(2),
        rs.getString(3),
        rs.getString(4),
        rs.getString(5),
        rs.getInt(6)
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
public void updateDoctor(Doctor doctor) throws SQLException {
    String sql = "UPDATE doctor SET full_name = ?, specialty = ?, contact_number = ?, email = ?, years_of_experience = ? WHERE doctor_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, doctor.getFullName());
        stmt.setString(2, doctor.getSpecialty());
        stmt.setString(3, doctor.getContactNumber());
        stmt.setString(4, doctor.getEmail());
        stmt.setInt(5, doctor.getYearsOfExperience());
        stmt.setInt(6, doctor.getDoctorId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error updating doctor: " + e.getMessage());
        throw e;
    }
}

@Override
public void deleteDoctor(int doctorId) throws SQLException {
    String sql = "DELETE FROM doctor WHERE doctor_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, doctorId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error deleting doctor: " + e.getMessage());
        throw e;
    }
}

    @Override
public List<Doctor> getAllDoctors() throws SQLException {
    String sql = "SELECT * FROM doctor";
    List<Doctor> doctors = new ArrayList<>();
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            doctors.add(new Doctor(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6)
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error fetching all clinics: " + e.getMessage());
        throw e;
    }
    return doctors;
}



}
