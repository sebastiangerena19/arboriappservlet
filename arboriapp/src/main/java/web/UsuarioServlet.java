package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        usuarioDAO = new UsuarioDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            if (id != null) {
                Usuario usuario = usuarioDAO.getUsuarioById(id);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/verUsuario.jsp").forward(request, response);
            } else {
                request.setAttribute("usuarios", usuarioDAO.getAllUsuarios());
                request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
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
                String idUsuario = request.getParameter("idUsuario");
                String nombreUsuario = request.getParameter("nombreUsuario");
                String contrasena = request.getParameter("contrasena");
                
                Usuario usuario = new Usuario();
                usuario.setIDdelUsuario(idUsuario);
                usuario.setIDdelUsuario(nombreUsuario);
                usuario.setContrasena(contrasena);
                
                usuarioDAO.createUsuario(usuario);
                response.sendRedirect("usuario");
            } else if ("update".equals(action)) {
                String id = request.getParameter("id");
                Usuario usuario = usuarioDAO.getUsuarioById(id);
                if (usuario != null) {
                    usuario.setIDdelUsuario(request.getParameter("nombreUsuario"));
                    usuario.setContrasena(request.getParameter("contrasena"));
                    
                    usuarioDAO.updateUsuario(usuario);
                }
                response.sendRedirect("usuario");
            } else if ("delete".equals(action)) {
                String id = request.getParameter("id");
                usuarioDAO.deleteUsuario(id);
                response.sendRedirect("usuario");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}

