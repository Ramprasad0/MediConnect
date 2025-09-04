package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int addDoctor(Doctor doctor) throws SQLException {
        int generatedID = -1;
        String sql = "INSERT INTO doctor (full_name, specialty, contact_number, email, years_of_experience) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, doctor.getFullName());
            statement.setString(2, doctor.getSpecialty());
            statement.setString(3, doctor.getContactNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getYearsOfExperience());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedID = resultSet.getInt(1);
                    doctor.setDoctorId(generatedID);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding doctor: " + e.getMessage());
            throw e;
        }
        return generatedID;
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws SQLException {
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Doctor(
                        doctorId,
                        resultSet.getString("full_name"),
                        resultSet.getString("specialty"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("email"),
                        resultSet.getInt("years_of_experience")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching doctor by ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctor SET full_name = ?, specialty = ?, contact_number = ?, email = ?, years_of_experience = ? WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getFullName());
            statement.setString(2, doctor.getSpecialty());
            statement.setString(3, doctor.getContactNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getYearsOfExperience());
            statement.setInt(6, doctor.getDoctorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating doctor: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteDoctor(int doctorId) throws SQLException {
        String sql = "DELETE FROM doctor WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting doctor: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctor";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                doctorList.add(new Doctor(
                    resultSet.getInt("doctor_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("specialty"),
                    resultSet.getString("contact_number"),
                    resultSet.getString("email"),
                    resultSet.getInt("years_of_experience")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all doctors: " + e.getMessage());
            throw e;
        }
        return doctorList;
    }
}