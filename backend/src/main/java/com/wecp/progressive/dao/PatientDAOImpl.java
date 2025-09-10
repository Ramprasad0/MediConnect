package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public int addPatient(Patient patient) throws SQLException {
        int generatedID = -1;
        String sql = "INSERT INTO patient (full_name, date_of_birth, contact_number, email, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, patient.getFullName());
            statement.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            statement.setString(3, patient.getContactNumber());
            statement.setString(4, patient.getEmail());
            statement.setString(5, patient.getAddress());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedID = resultSet.getInt(1);
                    patient.setPatientId(generatedID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
            throw e;
        }
        return generatedID;
    }

    @Override
    public Patient getPatientById(int patientId) throws SQLException {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Patient(
                        patientId,
                        resultSet.getString("full_name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("email"),
                        resultSet.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching patient by ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address = ? WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getFullName());
            statement.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            statement.setString(3, patient.getContactNumber());
            statement.setString(4, patient.getEmail());
            statement.setString(5, patient.getAddress());
            statement.setInt(6, patient.getPatientId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patient WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                patientList.add(new Patient(
                    resultSet.getInt("patient_id"),
                    resultSet.getString("full_name"),
                    resultSet.getDate("date_of_birth"),
                    resultSet.getString("contact_number"),
                    resultSet.getString("email"),
                    resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all patients: " + e.getMessage());
            throw e;
        }
        return patientList;
    }
}
