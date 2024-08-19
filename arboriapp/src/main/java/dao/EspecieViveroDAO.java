package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EspecieVivero;

public class EspecieViveroDAO {

    private Connection connection;

    public EspecieViveroDAO(Connection connection) {
        this.connection = connection;
    }

    public void createEspecieVivero(EspecieVivero especieVivero) throws SQLException {
        String query = "INSERT INTO EspecieVivero(IDVivero, Especie, Precio, Altura, DAP, AnchoCopa, EdadAnos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, especieVivero.getIDVivero());
            stmt.setString(2, especieVivero.getEspecie());
            stmt.setDouble(3, especieVivero.getPrecio());
            stmt.setDouble(4, especieVivero.getAltura());
            stmt.setDouble(5, especieVivero.getDAP());
            stmt.setDouble(6, especieVivero.getAnchoCopa());
            stmt.setInt(7, especieVivero.getEdadAnos());
            stmt.executeUpdate();
        }
    }

    public EspecieVivero getEspecieViveroById(int IDVivero) throws SQLException {
        String query = "SELECT * FROM EspecieVivero WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, IDVivero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    EspecieVivero especieVivero = new EspecieVivero();
                    especieVivero.setIDVivero(rs.getInt("IDVivero"));
                    especieVivero.setEspecie(rs.getString("Especie"));
                    especieVivero.setPrecio(rs.getDouble("Precio"));
                    especieVivero.setAltura(rs.getDouble("Altura"));
                    especieVivero.setDAP(rs.getDouble("DAP"));
                    especieVivero.setAnchoCopa(rs.getDouble("AnchoCopa"));
                    especieVivero.setEdadAnos(rs.getInt("EdadAnos"));
                    return especieVivero;
                }
            }
        }
        return null; // EspecieVivero no encontrado
    }

    public List<EspecieVivero> getAllEspecieVivero() throws SQLException {
        List<EspecieVivero> especiesVivero = new ArrayList<>();
        String query = "SELECT * FROM EspecieVivero";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                EspecieVivero especieVivero = new EspecieVivero();
                especieVivero.setIDVivero(rs.getInt("IDVivero"));
                especieVivero.setEspecie(rs.getString("Especie"));
                especieVivero.setPrecio(rs.getDouble("Precio"));
                especieVivero.setAltura(rs.getDouble("Altura"));
                especieVivero.setDAP(rs.getDouble("DAP"));
                especieVivero.setAnchoCopa(rs.getDouble("AnchoCopa"));
                especieVivero.setEdadAnos(rs.getInt("EdadAnos"));
                especiesVivero.add(especieVivero);
            }
        }
        return especiesVivero;
    }

    public void updateEspecieVivero(EspecieVivero especieVivero) throws SQLException {
        String query = "UPDATE EspecieVivero SET Especie = ?, Precio = ?, Altura = ?, DAP = ?, AnchoCopa = ?, EdadAnos = ? WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, especieVivero.getEspecie());
            stmt.setDouble(2, especieVivero.getPrecio());
            stmt.setDouble(3, especieVivero.getAltura());
            stmt.setDouble(4, especieVivero.getDAP());
            stmt.setDouble(5, especieVivero.getAnchoCopa());
            stmt.setInt(6, especieVivero.getEdadAnos());
            stmt.setInt(7, especieVivero.getIDVivero());
            stmt.executeUpdate();
        }
    }

    public void deleteEspecieVivero(int IDVivero) throws SQLException {
        String query = "DELETE FROM EspecieVivero WHERE IDVivero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, IDVivero);
            stmt.executeUpdate();
        }
    }
}
