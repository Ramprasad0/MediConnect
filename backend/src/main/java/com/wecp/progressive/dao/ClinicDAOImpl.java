package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Clinic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicDAOImpl implements ClinicDAO {

    @Override
    public int addClinic(Clinic clinic) throws SQLException {
        int generatedID = -1;
        String sql = "INSERT INTO clinic (clinic_name, location, doctor_id, contact_number, established_year) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, clinic.getClinicName());
            statement.setString(2, clinic.getLocation());
            statement.setInt(3, clinic.getDoctorId());
            statement.setString(4, clinic.getContactNumber());
            statement.setInt(5, clinic.getEstablishedYear());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedID = resultSet.getInt(1);
                    clinic.setClinicId(generatedID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding clinic: " + e.getMessage());
            throw e;
        }
        return generatedID;
    }

    @Override
    public Clinic getClinicById(int clinicId) throws SQLException {
        String sql = "SELECT * FROM clinic WHERE clinic_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clinicId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Clinic(
                        clinicId,
                        resultSet.getString("clinic_name"),
                        resultSet.getString("location"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("contact_number"),
                        resultSet.getInt("established_year")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching clinic by ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {
        String sql = "UPDATE clinic SET clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? WHERE clinic_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clinic.getClinicName());
            statement.setString(2, clinic.getLocation());
            statement.setInt(3, clinic.getDoctorId());
            statement.setString(4, clinic.getContactNumber());
            statement.setInt(5, clinic.getEstablishedYear());
            statement.setInt(6, clinic.getClinicId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating clinic: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        String sql = "DELETE FROM clinic WHERE clinic_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clinicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting clinic: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException {
        List<Clinic> clinicList = new ArrayList<>();
        String sql = "SELECT * FROM clinic";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                clinicList.add(new Clinic(
                    resultSet.getInt("clinic_id"),
                    resultSet.getString("clinic_name"),
                    resultSet.getString("location"),
                    resultSet.getInt("doctor_id"),
                    resultSet.getString("contact_number"),
                    resultSet.getInt("established_year")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all clinics: " + e.getMessage());
            throw e;
        }
        return clinicList;
    }
}
