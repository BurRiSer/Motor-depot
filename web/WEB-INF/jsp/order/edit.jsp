<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty order}"><jsp:useBean id="order" class="domain.Order"/></c:if>
<c:choose>
    <c:when test="${not empty order.id}"><fmt:message var="title" key="order.edit.title.edit"/></c:when>
    <c:otherwise><fmt:message var="title" key="order.edit.title.add"/></c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:if test="${not empty param.message}">
        <p class="error"><fmt:message key="${param.message}"/></p>
    </c:if>
    <c:url var="urlRequests" value="/order/requests.html"/>
    <c:url var="urlOrderSave" value="/order/save.html"/>
    <c:url var="urlOrderDelete" value="/order/delete.html"/>

    <form action="${urlOrderSave}" method="post">
        <c:if test="${not empty order.id}"><input name="id" value="${order.id}" type="hidden"></c:if>
        <label for="date"><fmt:message key="order.list.table.date"/>:</label>
        <input type=date id="date" name="date" value="2019-12-23" min="2019-12-23" max="2020-06-06" required>
        <label for="departure"><fmt:message key="order.list.table.departure"/>:</label>
        <input id="departure" name="departure" value="${order.departurePoint}" required>
        <label for="arrival"><fmt:message key="order.list.table.arrival"/>:</label>
        <input id="arrival" name="arrival" value="${order.arrivalPoint}" required>
        <label for="type"><fmt:message key="vehicle.list.table.type"/>:</label>
        <select id="type" name="type">
            <c:forEach var="type" items="${types}">
                <option value="${type.id}" ${selected}><fmt:message key="${type.name}"/></option>
            </c:forEach>
        </select>
        <input name="user_id" value="${currentUser.id}" type="hidden">
        <button class="save"><fmt:message key="user.edit.button.save"/></button>
        <c:if test="${not empty order.id}">
            <button class="delete" formaction="${urlOrderDelete}" formmethod="post"><fmt:message key="user.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="user.edit.button.reset"/></button>
        <a class="back" href="${urlRequests}"><fmt:message key="user.edit.button.cancel"/></a>
    </form>
</u:html>
