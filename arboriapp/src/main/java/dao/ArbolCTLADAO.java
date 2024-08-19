package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ArbolCTLA;

public class ArbolCTLADAO {

    private Connection connection;

    public ArbolCTLADAO(Connection connection) {
        this.connection = connection;
    }

    public void createArbolCTLA(ArbolCTLA arbolCTLA) throws SQLException {
        String query = "INSERT INTO ArbolCTLA(ConstanteCTLA) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, arbolCTLA.getConstanteCTLA());
            stmt.executeUpdate();
        }
    }

    public ArbolCTLA getArbolCTLById(int ID) throws SQLException {
        String query = "SELECT * FROM ArbolCTLA WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ArbolCTLA arbolCTLA = new ArbolCTLA(ID, query, ID, ID, ID, ID, ID);
                    arbolCTLA.setConstanteCTLA(rs.getDouble("ConstanteCTLA"));
                    return arbolCTLA;
                }
            }
        }
        return null; // ArbolCTLA no encontrado
    }

    public List<ArbolCTLA> getAllArbolCTLA() throws SQLException {
        List<ArbolCTLA> arbolesCTLA = new ArrayList<>();
        String query = "SELECT * FROM ArbolCTLA";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ArbolCTLA arbolCTLA = new ArbolCTLA(0, query, 0, 0, 0, 0, 0);
                arbolCTLA.setConstanteCTLA(rs.getDouble("ConstanteCTLA"));
                arbolesCTLA.add(arbolCTLA);
            }
        }
        return arbolesCTLA;
    }

    public void updateArbolCTLA(ArbolCTLA arbolCTLA) throws SQLException {
        String query = "UPDATE ArbolCTLA SET ConstanteCTLA = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, arbolCTLA.getConstanteCTLA());
            stmt.setInt(2, arbolCTLA.getID()); // Suponiendo que ArbolCTLA tenga un ID para identificarlo
            stmt.executeUpdate();
        }
    }

    public void deleteArbolCTLA(int ID) throws SQLException {
        String query = "DELETE FROM ArbolCTLA WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        }
    }
}

