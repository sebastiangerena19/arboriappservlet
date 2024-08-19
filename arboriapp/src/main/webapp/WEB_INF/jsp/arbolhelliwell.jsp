<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administrar Árboles Helliwell</title>
</head>
<body>
    <h1>Administrar Árboles Helliwell</h1>
    
    <!-- Formulario para crear un nuevo Árbol Helliwell -->
    <form action="arbolhelliwell" method="post">
        <input type="hidden" name="action" value="create">
        <label for="constanteHelliwell">Constante Helliwell:</label>
        <input type="text" id="constanteHelliwell" name="constanteHelliwell" required>
        <label for="valorHelliwell">Valor Helliwell:</label>
        <input type="text" id="valorHelliwell" name="valorHelliwell" required>
        <button type="submit">Crear Árbol Helliwell</button>
    </form>
    
    <!-- Formulario para actualizar un Árbol Helliwell -->
    <form action="arbolhelliwell" method="post">
        <input type="hidden" name="action" value="update">
        <label for="id">ID del Árbol:</label>
        <input type="text" id="id" name="id" required>
        <label for="constanteHelliwell">Constante Helliwell:</label>
        <input type="text" id="constanteHelliwell" name="constanteHelliwell" required>
        <label for="valorHelliwell">Valor Helliwell:</label>
        <input type="text" id="valorHelliwell" name="valorHelliwell" required>
        <button type="submit">Actualizar Árbol Helliwell</button>
    </form>
    
    <!-- Formulario para eliminar un Árbol Helliwell -->
    <form action="arbolhelliwell" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="id">ID del Árbol:</label>
        <input type="text" id="id" name="id" required>
        <button type="submit">Eliminar Árbol Helliwell</button>
    </form>
    
    <!-- Listado de todos los Árboles Helliwell -->
    <h2>Listado de Árboles Helliwell</h2>
    <ul>
        <c:forEach var="arbol" items="${arbolesHelliwell}">
            <li>ID: ${arbol.id}, Constante: ${arbol.constanteHelliwell}, Valor: ${arbol.valorHelliwell}</li>
        </c:forEach>
    </ul>
</body>
</html>
