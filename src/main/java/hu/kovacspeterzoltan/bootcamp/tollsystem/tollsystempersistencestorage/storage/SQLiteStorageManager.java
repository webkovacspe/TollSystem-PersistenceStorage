package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.storage;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.dto.MotorwayVignetteRecordDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.parser.StorageParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteStorageManager {
    private Connection connection;
    private final StorageParser parser;

    public SQLiteStorageManager() {
        parser = new StorageParser();
        createTable();
    }

    public List<MotorwayVignetteRecordDTO> findByRegistrationNumber(String registrationNumber) {
        List<MotorwayVignetteRecordDTO> motorwayVignetteRecordDTOs = new ArrayList<>();
        connectToDatabase();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM motorwayVignette WHERE registrationNumber = ?");
            stmt.setString(1, registrationNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                motorwayVignetteRecordDTOs.add(parser.resultSetToMotorwayVignetteDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(stmt);
            closeConnection();
        }
        return motorwayVignetteRecordDTOs;
    }

    private static void closePreparedStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTable() {
        connectToDatabase();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = """
                    CREATE TABLE IF NOT EXISTS motorwayVignette (
                          registrationNumber VARCHAR(255) NOT NULL
                        , vehicleCategory VARCHAR(5)
                        , motorwayVignetteType VARCHAR(255)
                        , price FLOAT
                        , validFrom DATETIME
                        , validTo DATETIME
                        , dateOfPurchase DATETIME
                    );
                    """;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:motorwayvignette.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
