<%--
  Created by IntelliJ IDEA.
  User: Artem Panasyuk
  Date: 19.04.2017
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% String message = (String)request.getAttribute("value");%>
<h1>
    <%=message%>
</h1>
<table>
<c:forEach items="${requestScope.list}" var="item">
    <tr>
        <td><c:out value="${item.id}"/></td>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.age}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
