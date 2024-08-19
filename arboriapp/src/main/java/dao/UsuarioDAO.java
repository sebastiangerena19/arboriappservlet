package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void createUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO Usuario(IDdelUsuario, Contrasena) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getIDdelUsuario());
            stmt.setString(2, usuario.getContrasena());
            stmt.executeUpdate();
        }
    }

    public Usuario getUsuarioById(String IDdelUsuario) throws SQLException {
        String query = "SELECT * FROM Usuario WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, IDdelUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIDdelUsuario(rs.getString("IDdelUsuario"));
                    usuario.setContrasena(rs.getString("Contrasena"));
                    return usuario;
                }
            }
        }
        return null; // Usuario no encontrado
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIDdelUsuario(rs.getString("IDdelUsuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE Usuario SET Contrasena = ? WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getContrasena());
            stmt.setString(2, usuario.getIDdelUsuario());
            stmt.executeUpdate();
        }
    }

    public void deleteUsuario(String IDdelUsuario) throws SQLException {
        String query = "DELETE FROM Usuario WHERE IDdelUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, IDdelUsuario);
            stmt.executeUpdate();
        }
    }
}
