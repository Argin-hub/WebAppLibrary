<%--
  Created by IntelliJ IDEA.
  User: 77770
  Date: 13.06.2020
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="add_tittle_forum" value="/app/tittleForum"/>

<fmt:bundle basename="i18n">
    <fmt:message key="topic" var = "topic"/>
    <fmt:message key="topic.topic" var = "topic"/>
    <fmt:message key="create.theme" var = "create"/>
    <fmt:message key="name.topic" var = "name_topic"/>
    <fmt:message key="write.comment" var = "write.comment"/>
    <fmt:message key="comment" var = "comment"/>
    <fmt:message key="forum.rules" var = "rules"/>
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="forum.rules.user" var = "rul"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/styleforum.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>

<html>

<body>
<table>
    <caption>
        ${topic}</caption>
    <c:forEach items="${tittles}" var="tittle">

        <tr>
            <td>${tittle.id}</td>
            <td><a href="add_comment?tittle_id=${tittle.id}">${tittle.name}</a></td>
        </tr>

    </c:forEach>
</table>
<c:if test="${role.equals('admin')}">

<form method="post" action="${add_tittle_forum}">
    <p>${create}</p>
    <b>${name_topic}</b>  <input type="text" name="tittle">
    <input type="submit" value="submit">
</form>
</c:if>
<details>
    <summary>${rules} </summary>
    <p> ${rul} </p>
</details>
</body>
</html>
