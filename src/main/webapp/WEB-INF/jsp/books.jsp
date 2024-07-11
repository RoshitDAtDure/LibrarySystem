<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books List</title>
</head>
<body>
    <h1>Books List</h1>
    <%-- <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Genre ID</th>
                <th>Name</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.genreId}</td>
                    <td>${book.name}</td>
                    <td>${book.status ? 'Active' : 'Inactive'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table> --%>
</body>
</html>

