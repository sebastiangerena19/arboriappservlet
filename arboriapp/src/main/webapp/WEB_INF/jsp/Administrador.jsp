<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administrar Administradores</title>
</head>
<body>
    <h1>Administrar Administradores</h1>
    
    <!-- Formulario para crear un nuevo Administrador -->
    <form action="administrador" method="post">
        <input type="hidden" name="action" value="create">
        <label for="id">ID del Administrador:</label>
        <input type="text" id="id" name="id" required>
        <button type="submit">Crear Administrador</button>
    </form>
    
    <!-- Formulario para actualizar un Administrador -->
    <form action="administrador" method="post">
        <input type="hidden" name="action" value="update">
        <label for="id">ID del Administrador:</label>
        <input type="text" id="id" name="id" required>
        <button type="submit">Actualizar Administrador</button>
    </form>
    
    <!-- Formulario para eliminar un Administrador -->
    <form action="administrador" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="id">ID del Administrador:</label>
        <input type="text" id="id" name="id" required>
        <button type="submit">Eliminar Administrador</button>
    </form>
    
    <!-- Listado de todos los Administradores -->
    <h2>Listado de Administradores</h2>
    <ul>
        <c:forEach var="admin" items="${administradores}">
            <li>${admin.IDdelUsuario}</li>
        </c:forEach>
    </ul>
</body>
</html>
