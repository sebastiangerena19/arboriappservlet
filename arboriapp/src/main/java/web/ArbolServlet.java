package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArbolDAO;
import model.Arbol;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/arbol")
public class ArbolServlet extends HttpServlet {

    private ArbolDAO arbolDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        arbolDAO = new ArbolDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            if (id != null) {
                Arbol arbol = arbolDAO.getArbolById(Integer.parseInt(id));
                request.setAttribute("arbol", arbol);
                request.getRequestDispatcher("/verArbol.jsp").forward(request, response);
            } else {
                request.setAttribute("arboles", arbolDAO.getAllArboles());
                request.getRequestDispatcher("/listarArboles.jsp").forward(request, response);
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
                double altura = Double.parseDouble(request.getParameter("altura"));
                double dap = Double.parseDouble(request.getParameter("dap"));
                double precioEspecie = Double.parseDouble(request.getParameter("precioEspecie"));
                double valorEconomico = Double.parseDouble(request.getParameter("valorEconomico"));
                
                Arbol arbol = new Arbol();
                arbol.setEspecie(especie);
                arbol.setAltura(altura);
                arbol.setDAP(dap);
                arbol.setPrecioEspecie(precioEspecie);
                arbol.setValorEconomico(valorEconomico);
                
                arbolDAO.createArbol(arbol);
                response.sendRedirect("arbol");
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Arbol arbol = arbolDAO.getArbolById(id);
                if (arbol != null) {
                    arbol.setEspecie(request.getParameter("especie"));
                    arbol.setAltura(Double.parseDouble(request.getParameter("altura")));
                    arbol.setDAP(Double.parseDouble(request.getParameter("dap")));
                    arbol.setPrecioEspecie(Double.parseDouble(request.getParameter("precioEspecie")));
                    arbol.setValorEconomico(Double.parseDouble(request.getParameter("valorEconomico")));
                    
                    arbolDAO.updateArbol(arbol);
                }
                response.sendRedirect("arbol");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                arbolDAO.deleteArbol(id);
                response.sendRedirect("arbol");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}
