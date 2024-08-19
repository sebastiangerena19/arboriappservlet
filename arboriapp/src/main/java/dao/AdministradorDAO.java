package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Administrador;

public class AdministradorDAO {

    private Connection connection;

    public AdministradorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAdministrador(Administrador administrador) throws SQLException {
        String query = "INSERT INTO Administrador(IDdelUsuario) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, administrador.getIDdelUsuario());
            stmt.executeUpdate();
        }
    }

    public Administrador getAdministradorById(String IDdelUsuario) throws SQLException {
        String query = "SELECT * FROM Administrador WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, IDdelUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Administrador administrador = new Administrador();
                    administrador.setIDdelUsuario(rs.getString("IDdelUsuario"));
                    return administrador;
                }
            }
        }
        return null; // Administrador no encontrado
    }

    public List<Administrador> getAllAdministradores() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
        String query = "SELECT * FROM Administrador";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setIDdelUsuario(rs.getString("IDdelUsuario"));
                administradores.add(administrador);
            }
        }
        return administradores;
    }

    public void updateAdministrador(Administrador administrador) throws SQLException {
        
        String query = "UPDATE Administrador SET IDdelUsuario = ? WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, administrador.getIDdelUsuario());
            stmt.setString(2, administrador.getIDdelUsuario());
            stmt.executeUpdate();
        }
    }

    public void deleteAdministrador(String IDdelUsuario) throws SQLException {
        String query = "DELETE FROM Administrador WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, IDdelUsuario);
            stmt.executeUpdate();
        }
    }
}
