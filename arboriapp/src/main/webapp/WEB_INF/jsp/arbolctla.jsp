<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administrar Árboles CTLA</title>
</head>
<body>
    <h1>Administrar Árboles CTLA</h1>
    
    <!-- Formulario para crear un nuevo Árbol CTLA -->
    <form action="arbolctla" method="post">
        <input type="hidden" name="action" value="create">
        <label for="constanteCTLA">Constante CTLA:</label>
        <input type="text" id="constanteCTLA" name="constanteCTLA" required>
        <button type="submit">Crear Árbol CTLA</button>
    </form>
    
    <!-- Formulario para actualizar un Árbol CTLA -->
    <form action="arbolctla" method="post">
        <input type="hidden" name="action" value="update">
        <label for="id">ID del Árbol:</label>
        <input type="text" id="id" name="id" required>
        <label for="constanteCTLA">Constante CTLA:</label>
        <input type="text" id="constanteCTLA" name="constanteCTLA" required>
        <button type="submit">Actualizar Árbol CTLA</button>
    </form>
    
    <!-- Formulario para eliminar un Árbol CTLA -->
    <form action="arbolctla" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="id">ID del Árbol:</label>
        <input type="text" id="id" name="id" required>
        <button type="submit">Eliminar Árbol CTLA</button>
    </form>
    
    <!-- Listado de todos los Árboles CTLA -->
    <h2>Listado de Árboles CTLA</h2>
    <ul>
        <c:forEach var="arbol" items="${arbolesCTLA}">
            <li>ID: ${arbol.id}, Constante: ${arbol.constanteCTLA}</li>
        </c:forEach>
    </ul>
</body>
</html>
