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
    <p>
        Правила форума
        Общие положения.
        1. Незнание правил не освобождает от необходимости их выполнения.
        2. Отправка любого сообщения на форум автоматически означает Ваше согласие с настоящими правилами и с необходимостью их выполнения.
        3. Все имеющиеся на сервере сведения имеют исключительно информационное назначение. Все сообщения отражают мнения их авторов, и администрация форума не несет за них никакой ответственности
        4. Язык общения на форуме - русский. Исключения составляют названия торговых марок, моделей устройств, программных продуктов и аббревиатура устройств.
        5. Не обращайте внимания на хулиганов. Не отвечайте им, даже если Вы считаете, что Вас оскорбили, просто сообщите администратору. Остальное — забота администрации форума.

        Условия создания новых тем и ответов на существующие темы
        1. Любой посетитель форума имеет право создать тему, которая соответствует профилю форума и не является повторной по данному вопросу.
        2. В заголовке обязательно указывайте название предмета, которого касается вопрос. Более подробно о правилах создания тем прочитайте в Правилах форумов.
        3. Если вопрос касается тем нескольких форумов, достаточно задать свой вопрос только в одном из них.
        4. Если вы задаете вопрос о расширении (плагине/модуле/компоненте) не входящем в дистрибутив Joomla! добавляйте ссылку на страницу расширения.
        5. При создании новой темы руководствуйтесь правилом: один вопрос — одна тема. Если есть несколько вопросов — создайте соответствующее количество тем.
        6. Прежде, чем создать тему с вопросом, попытайтесь правильно сформулировать для себя свой вопрос. Пользуйтесь поиском и фильтром в форумах для нахождения ответов на вопросы. Если такая тема создана уже кем-то другим, задайте вопрос в ней.
        7. Для выделения или постановки ударения на слово или фразу, используйте жирный шрифт. Не используйте ЗАГЛАВНЫЕ буквы.

    </p>
</details>
</body>
</html>
