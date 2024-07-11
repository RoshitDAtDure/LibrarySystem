<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Genre List</title>
</head>
<body>
    <h1>Genre List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Published</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="genre" items="${genres}">
                <tr>
                    <td>${genre.id}</td>
                    <td>${genre.type}</td>
                    <td>${genre.published ? 'Yes' : 'No'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
