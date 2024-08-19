package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EspecieViveroDAO;
import model.EspecieVivero;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/especievivero")
public class EspecieViveroServlet extends HttpServlet {

    private EspecieViveroDAO especieViveroDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        especieViveroDAO = new EspecieViveroDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            if (id != null) {
                EspecieVivero especieVivero = especieViveroDAO.getEspecieViveroById(Integer.parseInt(id));
                request.setAttribute("especieVivero", especieVivero);
                request.getRequestDispatcher("/verEspecieVivero.jsp").forward(request, response);
            } else {
                request.setAttribute("especiesVivero", especieViveroDAO.getAllEspecieVivero());
                request.getRequestDispatcher("/listarEspeciesVivero.jsp").forward(request, response);
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
                String especie = request.getParameter("especie");
                double precio = Double.parseDouble(request.getParameter("precio"));
                double altura = Double.parseDouble(request.getParameter("altura"));
                double dap = Double.parseDouble(request.getParameter("dap"));
                double anchodecopa = Double.parseDouble(request.getParameter("anchodecopa"));
                int edadAnnos = Integer.parseInt(request.getParameter("edadAnnos"));
                
                EspecieVivero especieVivero = new EspecieVivero();
                especieVivero.setEspecie(especie);
                especieVivero.setPrecio(precio);
                especieVivero.setAltura(altura);
                especieVivero.setDAP(dap);
                especieVivero.setAnchoCopa(anchodecopa);
                especieVivero.setEdadAnos(edadAnnos);
                
                especieViveroDAO.createEspecieVivero(especieVivero);
                response.sendRedirect("especievivero");
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                EspecieVivero especieVivero = especieViveroDAO.getEspecieViveroById(id);
                if (especieVivero != null) {
                    especieVivero.setEspecie(request.getParameter("especie"));
                    especieVivero.setPrecio(Double.parseDouble(request.getParameter("precio")));
                    especieVivero.setAltura(Double.parseDouble(request.getParameter("altura")));
                    especieVivero.setDAP(Double.parseDouble(request.getParameter("dap")));
                    especieVivero.setAnchoCopa(Double.parseDouble(request.getParameter("anchodecopa")));
                    especieVivero.setEdadAnos(Integer.parseInt(request.getParameter("edadAnnos")));
                    
                    especieViveroDAO.updateEspecieVivero(especieVivero);
                }
                response.sendRedirect("especievivero");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                especieViveroDAO.deleteEspecieVivero(id);
                response.sendRedirect("especievivero");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}

