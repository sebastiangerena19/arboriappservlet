package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ViveroDAO;
import model.Vivero;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/vivero")
public class ViveroServlet extends HttpServlet {

    private ViveroDAO viveroDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        viveroDAO = new ViveroDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            if (id != null) {
                Vivero vivero = viveroDAO.getViveroById(Integer.parseInt(id));
                request.setAttribute("vivero", vivero);
                request.getRequestDispatcher("/verVivero.jsp").forward(request, response);
            } else {
                request.setAttribute("viveros", viveroDAO.getAllViveros());
                request.getRequestDispatcher("/listarViveros.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                String nombre = request.getParameter("nombre");
                String ubicacion = request.getParameter("ubicacion");
                
                Vivero vivero = new Vivero();
                vivero.setNombre(nombre);
                vivero.setUbicacionVivero(ubicacion);
                
                viveroDAO.createVivero(vivero);
                response.sendRedirect("vivero");
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vivero vivero = viveroDAO.getViveroById(id);
                if (vivero != null) {
                    vivero.setNombre(request.getParameter("nombre"));
                    vivero.setUbicacionVivero(request.getParameter("ubicacion"));
                    
                    viveroDAO.updateVivero(vivero);
                }
                response.sendRedirect("vivero");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                viveroDAO.deleteVivero(id);
                response.sendRedirect("vivero");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}

