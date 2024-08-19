package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Arbol;

public class ArbolDAO {

    private Connection connection;

    public ArbolDAO(Connection connection) {
        this.connection = connection;
    }

    public void createArbol(Arbol arbol) throws SQLException {
        String query = "INSERT INTO Arbol(ID, Especie, Altura, DAP, PrecioEspecie, ValorEconomico) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, arbol.getID());
            stmt.setString(2, arbol.getEspecie());
            stmt.setDouble(3, arbol.getAltura());
            stmt.setDouble(4, arbol.getDAP());
            stmt.setDouble(5, arbol.getPrecioEspecie());
            stmt.setDouble(6, arbol.getValorEconomico());
            stmt.executeUpdate();
        }
    }

    public Arbol getArbolById(int ID) throws SQLException {
        String query = "SELECT * FROM Arbol WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Arbol arbol = new Arbol();
                    arbol.setID(rs.getInt("ID"));
                    arbol.setEspecie(rs.getString("Especie"));
                    arbol.setAltura(rs.getDouble("Altura"));
                    arbol.setDAP(rs.getDouble("DAP"));
                    arbol.setPrecioEspecie(rs.getDouble("PrecioEspecie"));
                    arbol.setValorEconomico(rs.getDouble("ValorEconomico"));
                    return arbol;
                }
            }
        }
        return null; // Arbol no encontrado
    }

    public List<Arbol> getAllArboles() throws SQLException {
        List<Arbol> arboles = new ArrayList<>();
        String query = "SELECT * FROM Arbol";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Arbol arbol = new Arbol();
                arbol.setID(rs.getInt("ID"));
                arbol.setEspecie(rs.getString("Especie"));
                arbol.setAltura(rs.getDouble("Altura"));
                arbol.setDAP(rs.getDouble("DAP"));
                arbol.setPrecioEspecie(rs.getDouble("PrecioEspecie"));
                arbol.setValorEconomico(rs.getDouble("ValorEconomico"));
                arboles.add(arbol);
            }
        }
        return arboles;
    }

    public void updateArbol(Arbol arbol) throws SQLException {
        String query = "UPDATE Arbol SET Especie = ?, Altura = ?, DAP = ?, PrecioEspecie = ?, ValorEconomico = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, arbol.getEspecie());
            stmt.setDouble(2, arbol.getAltura());
            stmt.setDouble(3, arbol.getDAP());
            stmt.setDouble(4, arbol.getPrecioEspecie());
            stmt.setDouble(5, arbol.getValorEconomico());
            stmt.setInt(6, arbol.getID());
            stmt.executeUpdate();
        }
    }

    public void deleteArbol(int ID) throws SQLException {
        String query = "DELETE FROM Arbol WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        }
    }
}

