<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="tittle" var = "tittle22"/>
    <fmt:message key="genre" var = "genre22"/>
    <fmt:message key="author" var = "author22"/>
    <fmt:message key="basket.empty" var = "basket_null"/>
    <fmt:message key="amount" var = "amount22"/>
    <fmt:message key="basket.book.available" var = "avail"/>
    <fmt:message key="basket.createOrder" var = "create_ord"/>
    <fmt:message key="order.invalid" var = "order_invalid"/>
    <fmt:message key="basket.books" var = "basket_books"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>

<body>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<c:if test="${role.equals('user')}">
    <div id="content">
        <c:if test="${not empty basket_empty}">
            ${basket_null}
        </c:if>

        <table border=1 class="task" align=center>
            <caption>${basket_books}</caption>
            <thead>
            <tr>
                <th>${tittle22}</th>
                <th>${genre22}</th>
                <th>${author22}</th>
                <th>${avail}</th>
            </tr>
            </thead>
            <c:forEach items="${basket_books_list}" var="bookInfo">
                <tr>
                    <td>${bookInfo.book.name}</td>
                    <td>${bookInfo.book.genre.name}</td>
                    <td>${bookInfo.book.author.lastName} ${bookInfo.book.author.firstName} ${bookInfo.book.author.middleName}</td>
                    <td>
                        <c:if test="${bookInfo.amount > 0}">
                        Да
                        </c:if>
                        <c:if test="${bookInfo.amount <= 0}">
                        Нет
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <form action="createOrder" method="post">
            <input type="submit" value="${create_ord}" <c:if test="${not empty book_not_available}">${order_invalid}</c:if>>
        </form>
    </div>
    </body>
</c:if>

