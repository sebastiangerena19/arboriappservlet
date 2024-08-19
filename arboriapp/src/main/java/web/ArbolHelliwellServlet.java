package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArbolHelliwellDAO;
import model.ArbolHelliwell;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/arbolhelliwell")
public class ArbolHelliwellServlet extends HttpServlet {

    private ArbolHelliwellDAO arbolHelliwellDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        arbolHelliwellDAO = new ArbolHelliwellDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            if (id != null) {
                ArbolHelliwell arbolHelliwell = arbolHelliwellDAO.getArbolHelliwellById(Integer.parseInt(id));
                request.setAttribute("arbolHelliwell", arbolHelliwell);
                request.getRequestDispatcher("/verArbolHelliwell.jsp").forward(request, response);
            } else {
                request.setAttribute("arbolesHelliwell", arbolHelliwellDAO.getAllArbolHelliwell());
                request.getRequestDispatcher("/listarArbolesHelliwell.jsp").forward(request, response);
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
                double constanteHelliwell = Double.parseDouble(request.getParameter("constanteHelliwell"));
                double valorHelliwell = Double.parseDouble(request.getParameter("valorHelliwell"));
                ArbolHelliwell arbolHelliwell = new ArbolHelliwell(0, action, valorHelliwell, valorHelliwell, valorHelliwell, valorHelliwell, valorHelliwell, valorHelliwell);
                arbolHelliwell.setConstanteHelliwell(constanteHelliwell);
                arbolHelliwell.setValorHelliwell(valorHelliwell);
                arbolHelliwellDAO.createArbolHelliwell(arbolHelliwell);
                response.sendRedirect("arbolhelliwell");
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                ArbolHelliwell arbolHelliwell = arbolHelliwellDAO.getArbolHelliwellById(id);
                if (arbolHelliwell != null) {
                    arbolHelliwell.setConstanteHelliwell(Double.parseDouble(request.getParameter("constanteHelliwell")));
                    arbolHelliwell.setValorHelliwell(Double.parseDouble(request.getParameter("valorHelliwell")));
                    arbolHelliwellDAO.updateArbolHelliwell(arbolHelliwell);
                }
                response.sendRedirect("arbolhelliwell");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                arbolHelliwellDAO.deleteArbolHelliwell(id);
                response.sendRedirect("arbolhelliwell");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}

