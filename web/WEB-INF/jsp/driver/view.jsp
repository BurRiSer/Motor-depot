<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="driver.view.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="driver.list.table.name"/></th>
            <td class="content">${driver.name}</td>
        </tr>
        <tr>
            <th><fmt:message key="driver.list.table.surname"/></th>
            <td class="content">${driver.surname}</td>
        </tr>
        <tr>
            <th><fmt:message key="driver.list.table.birth"/></th>
            <td class="content"><fmt:formatDate pattern="dd-MM-yyyy" value="${driver.birth}"/></td>
        </tr>
    </table>
    <h3><fmt:message key="driver.view.vehicle"/></h3>
    <table>
        <tr>
            <th>ID</th>
            <td class="content">${vehicle.id}</td>
        </tr>
        <tr>
            <th><fmt:message key="vehicle.list.table.model"/></th>
            <td class="content">${vehicle.model}</td>
        </tr>
        <tr>
            <th><fmt:message key="vehicle.list.table.type"/></th>
            <td class="content"><fmt:message key="${vehicle.type.name}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="vehicle.list.table.serviceability"/></th>
            <td class="content"><fmt:message key="${vehicle.serviceability}"/></td>
        </tr>
    </table>
    <c:url var="urlVehicleEdit" value="/vehicle/serviceability.html">
        <c:param name="id" value="${vehicle.id}"/>
    </c:url>
    <a href="${urlVehicleEdit}" class="edit-button"><fmt:message key="user.list.button.edit"/></a>
</u:html>