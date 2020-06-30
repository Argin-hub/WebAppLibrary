<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="register_url" value="/app/register"/>
<c:url var="home_url" value="/app/welcome"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="register.title" var = "title"/>
    <fmt:message key="register.birthday" var = "birthday"/>
    <fmt:message key="register.firstname" var = "firstname"/>
    <fmt:message key="register.lastname" var = "lastname"/>
    <fmt:message key="register.middlename" var = "middlename"/>
    <fmt:message key="register.phone" var = "phone"/>
    <fmt:message key="register.password" var = "password"/>
    <fmt:message key="register.email" var = "email"/>
    <fmt:message key="register.confirm.password" var = "confirm_password"/>
    <fmt:message key="register.pholder.firstname" var = "pholder_firstname"/>
    <fmt:message key="register.pholder.lastname" var = "pholder_lastname"/>
    <fmt:message key="register.pholder.middlename" var = "pholder_middlename"/>
    <fmt:message key="register.pholder.phone" var = "pholder_phone"/>
    <fmt:message key="register.pholder.email" var = "pholder_email"/>
    <fmt:message key="register.pholder.birthday" var = "pholder_birthday"/>
    <fmt:message key="register.button.register" var = "button_register"/>
    <fmt:message key="register.button.home" var = "home"/>
    <fmt:message key="register.passwordlen" var = "passwordlen"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content">

    <form action="${register_url}" method="POST">
        <fieldset>
            <legend id="header">${title}</legend>
            <div>
                <label>${firstname}</label>
                <input name="first_name" type="text" placeholder="${pholder_firstname}">
            </div>
            <div>
                <label>${lastname}</label>
                <input name="last_name" type="text" placeholder="${pholder_lastname}">
            </div>
            <div>
                <label>${middlename}</label>
                <input name="middle_name" type="text" placeholder="${pholder_middlename}">
            </div>
            <div>
                <label>${birthday}</label>
                <input name="birthday" type="text" placeholder="${pholder_birthday}">
            </div>
            <div>
                <label>${phone}</label>
                <input name="phone" type="text" placeholder="${pholder_phone}" >
            </div>
            <div>
                <label>${email}</label>
                <input type="text" name="email" placeholder="${pholder_email}" >
            </div>
            <div>
                <label>${password}</label>
                <input type="password" name="password">
                ${passwordlen}
            </div>
            <div>
                <label>${confirm_password}</label>
                <input type="password" name="password_confirm">
            </div>
            <div>
                <button id="submit" name="submit">${button_register}</button>
            </div>
            <div>
                <a href="${home_url}" role="button">${home}</a>
            </div>
        </fieldset>
    </form>
</div>
</BODY>
