<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuentas Corrientes</title>
    </head>
    
    <body>
        
        <c:if test="${requestScope.errores!=null}">
            <c:forEach items="${requestScope.lstErrores}" var="error">
                <p><strong>${error}</strong></p>
            </c:forEach>
        </c:if>
        
        <form method="post" action="ServletNuevaCuenta">
            <table>
                <tr>
                    <th colspan="2">NUEVA CUENTA</th>
                </tr>

                <tr>
                    <td>Titular </td>
                    <td><input  type="text" name="titular"></td>
                </tr>

                <tr>
                    <td>Saldo Inicial </td>
                    <td><input  type="number" name="saldo"></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submitCrearCuenta" value="Crear Cuenta Corriente"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
