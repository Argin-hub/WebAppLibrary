<%--
  Created by IntelliJ IDEA.
  User: 77770
  Date: 10.06.2020
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="tittle" var = "tittle22"/>
    <fmt:message key="genre" var = "genre22"/>
    <fmt:message key="author" var = "author22"/>
    <fmt:message key="description" var = "description22"/>
    <fmt:message key="amount" var = "amount22"/>
    <fmt:message key="submit" var = "submit"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<html>

<body>

<table border=1 class="task" align=center>
    <thead>
    <tr>
        <th>${tittle22}</th>
        <th>${author22}</th>
        <th>${description22}</th>
        <th>${genre22}</th>
    </tr>
    </thead>
    <c:forEach items="${findBooks}" var="book">
        <tr>
            <td>${book.name}</td>
            <td>${book.author.lastName} ${book.author.firstName} ${book.author.middleName}</td>
            <td>${book.description}</td>
            <td>${book.genre.name}</td>
        </tr>
    </c:forEach>

    <c:forEach items="${books}" var="book">

        <tr>
            <td>${book.name}</td>
            <td>${book.author.lastName} ${book.author.firstName} ${book.author.middleName}</td>
            <td>${book.description}</td>
            <td>${book.genre.name}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
