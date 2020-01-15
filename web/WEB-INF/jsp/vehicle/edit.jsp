<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty vehicle}"><jsp:useBean id="vehicle" class="domain.Vehicle"/></c:if>
<c:choose>
    <c:when test="${not empty vehicle.id}"><fmt:message var="title" key="vehicle.edit.title.edit"/></c:when>
    <c:otherwise><fmt:message var="title" key="vehicle.edit.title.add"/></c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlVehicleList" value="/vehicle/list.html"/>
    <c:url var="urlVehicleSave" value="/vehicle/save.html"/>
    <form action="${urlVehicleSave}" method="post">
        <c:if test="${not empty vehicle.id}"><input name="id" value="${vehicle.id}" type="hidden"></c:if>
        <label for="model"><fmt:message key="vehicle.list.table.model"/>:</label>
        <input id="model" name="model" value="${vehicle.model}">
        <label for="serviceability"><fmt:message key="vehicle.list.table.serviceability"/>:</label>
        <input id="serviceability" name="serviceability" value="${vehicle.serviceability}">
        <label for="type"><fmt:message key="vehicle.list.table.type"/>:</label>
        <input id="type" name="type" value="${vehicle.type}">
        <button class="save"><fmt:message key="user.edit.button.save"/></button>
        <button class="reset" type="reset"><fmt:message key="user.edit.button.reset"/></button>
        <a class="back" href="${urlVehicleList}"><fmt:message key="user.edit.button.cancel"/></a>
    </form>
</u:html>

