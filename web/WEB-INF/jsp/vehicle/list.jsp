
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="vehicle.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="vehicle.list.table.model"/></th>
            <th><fmt:message key="vehicle.list.table.type"/></th>
            <th><fmt:message key="vehicle.list.table.serviceability"/></th>
            <c:if test="${currentUser.role == 'ADMINISTRATOR'}">
            <td>&nbsp;</td>
            </c:if>
        </tr>
        <c:forEach var="vehicle" items="${vehicles}">
            <tr>
                <td class="content">${vehicle.model}</td>
                <td class="content"><fmt:message key="${vehicle.type.name}"/></td>
                <td class="content"><fmt:message key="${vehicle.serviceability}"/></td>
                <c:if test="${currentUser.role == 'ADMINISTRATOR'}">
                <td class="empty">
                    <c:url var="urlVehicleEdit" value="/vehicle/edit.html">
                        <c:param name="id" value="${vehicle.id}"/>
                    </c:url>
                    <a href="${urlVehicleEdit}" class="edit"><fmt:message key="user.list.button.edit"/></a>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</u:html>