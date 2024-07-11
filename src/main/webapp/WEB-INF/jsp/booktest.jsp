<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List of books</h1>
	<table>
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
                   <%--  <td>${book.status}</td> --%>
                </tr>
            </c:forEach>
        </tbody>
	</table>
</body>
</html>