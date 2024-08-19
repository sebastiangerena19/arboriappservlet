package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vivero;

public class ViveroDAO {

    private Connection connection;

    public ViveroDAO(Connection connection) {
        this.connection = connection;
    }

    public void createVivero(Vivero vivero) throws SQLException {
        String query = "INSERT INTO Vivero(IDVivero, UbicacionVivero, Nombre) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vivero.getIDVivero());
            stmt.setString(2, vivero.getUbicacionVivero());
            stmt.setString(3, vivero.getNombre());
            stmt.executeUpdate();
        }
    }

    public Vivero getViveroById(int IDVivero) throws SQLException {
        String query = "SELECT * FROM Vivero WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, IDVivero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vivero vivero = new Vivero();
                    vivero.setIDVivero(rs.getInt("IDVivero"));
                    vivero.setUbicacionVivero(rs.getString("UbicacionVivero"));
                    vivero.setNombre(rs.getString("Nombre"));
                    return vivero;
                }
            }
        }
        return null; // Vivero no encontrado
    }

    public List<Vivero> getAllViveros() throws SQLException {
        List<Vivero> viveros = new ArrayList<>();
        String query = "SELECT * FROM Vivero";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vivero vivero = new Vivero();
                vivero.setIDVivero(rs.getInt("IDVivero"));
                vivero.setUbicacionVivero(rs.getString("UbicacionVivero"));
                vivero.setNombre(rs.getString("Nombre"));
                viveros.add(vivero);
            }
        }
        return viveros;
    }

    public void updateVivero(Vivero vivero) throws SQLException {
        String query = "UPDATE Vivero SET UbicacionVivero = ?, Nombre = ? WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vivero.getUbicacionVivero());
            stmt.setString(2, vivero.getNombre());
            stmt.setInt(3, vivero.getIDVivero());
            stmt.executeUpdate();
        }
    }

    public void deleteVivero(int IDVivero) throws SQLException {
        String query = "DELETE FROM Vivero WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, IDVivero);
            stmt.executeUpdate();
        }
    }
}
       

