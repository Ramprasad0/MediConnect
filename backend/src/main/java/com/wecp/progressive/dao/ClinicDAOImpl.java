package com.wecp.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Clinic;

public class ClinicDAOImpl implements ClinicDAO{

  

    @Override
public int addClinic(Clinic clinic) throws SQLException {
   int generatedId=-1;
    String sql = "INSERT INTO clinic (clinic_name, location,doctor_id,contact_number,established_year) VALUES (?,?,?,?,?)";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, clinic.getClinicName());
        stmt.setString(2, clinic.getLocation());
        stmt.setInt(3, clinic.getDoctorId());
        stmt.setString(4, clinic.getContactNumber());
        stmt.setInt(5, clinic.getEstablishedYear());
        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                generatedId = rs.getInt(1);
                clinic.setClinicId(generatedId);
            }
        }
    } catch (SQLException e) {
      System.err.println("Error adding clinic: "+e.getMessage());
         throw e;
    }
    return generatedId;
}


    @Override
    public Clinic getClinicById(int clinicId) throws SQLException{
       String sql = "SELECT * FROM clinic WHERE clinic_id = ?";
       try(Connection conn = DatabaseConnectionManager.getConnection();
       PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
         
stmt.setInt(1, clinicId);
try (ResultSet rs = stmt.executeQuery()) {
    if (rs.next()) {
        return new Clinic(
            clinicId,
            rs.getString("clinic_name"),
            rs.getString("location"),
            rs.getInt("doctor_id"),
            rs.getString("contact_number"),
            rs.getInt("established_year")
        );
    }
}

       }catch(SQLException e){
        System.err.println("Error fetching clinic by ID: " + e.getMessage());
        throw e;
       }
       return null;
    }

    @Override
public void updateClinic(Clinic clinic) throws SQLException {
    String sql = "UPDATE clinic SET clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? WHERE clinic_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, clinic.getClinicName());
        stmt.setString(2, clinic.getLocation());
        stmt.setInt(3, clinic.getDoctorId());
        stmt.setString(4, clinic.getContactNumber());
        stmt.setInt(5, clinic.getEstablishedYear());
        stmt.setInt(6, clinic.getClinicId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error updating clinic: " + e.getMessage());
        throw e;
    }
}

@Override
public void deleteClinic(int clinicId) throws SQLException {
    String sql = "DELETE FROM clinic WHERE clinic_id = ?";
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, clinicId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error deleting clinic: " + e.getMessage());
        throw e;
    }
}

@Override
public List<Clinic> getAllClinics() throws SQLException {
    String sql = "SELECT * FROM clinic";
    List<Clinic> clinics = new ArrayList<>();
    try (Connection conn = DatabaseConnectionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            clinics.add(new Clinic(
                rs.getInt("clinic_id"),
                rs.getString("clinic_name"),
                rs.getString("location"),
                rs.getInt("doctor_id"),
                rs.getString("contact_number"),
                rs.getInt("established_year")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error fetching all clinics: " + e.getMessage());
        throw e;
    }
    return clinics;
}

}
