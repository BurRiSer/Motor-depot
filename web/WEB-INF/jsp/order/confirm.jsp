<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="order.edit.title.confirm" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th>ID</th>
            <td class="content">${order.id}</td>
        </tr>
        <tr>
            <th><fmt:message key="order.list.table.date"/></th>
            <td class="content"><fmt:formatDate pattern="dd-MM-yyyy" value="${order.date}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="order.list.table.departure"/></th>
            <td class="content">${order.departurePoint}</td>
        </tr>
        <tr>
            <th><fmt:message key="order.list.table.arrival"/></th>
            <td class="content">${order.arrivalPoint}</td>
        </tr>
        <tr>
            <th><fmt:message key="vehicle.list.table.type"/></th>
            <td class="content"><fmt:message key="${order.vehicleType.name}"/></td>
        </tr>
    </table>
    <h3><fmt:message key="vehicle.list.title"/></h3>
    <c:choose>
        <c:when test="${not empty vehicles}">
            <table>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="vehicle.list.table.model"/></th>
                    <th><fmt:message key="vehicle.list.table.type"/></th>
                    <th><fmt:message key="vehicle.list.table.serviceability"/></th>
                    <td>&nbsp;</td>
                </tr>
                <c:forEach var="vehicle" items="${vehicles}">
                    <tr>
                        <td class="content">${vehicle.id}</td>
                        <td class="content">${vehicle.model}</td>
                        <td class="content"><fmt:message key="${vehicle.type.name}"/></td>
                        <td class="content"><fmt:message key="${vehicle.serviceability}"/></td>
                        <td class="empty">
                            <c:url var="urlOrderAccept" value="/order/request/save.html">
                                <c:param name="id" value="${order.id}"/>
                                <c:param name="vehicle_id" value="${vehicle.id}"/>
                            </c:url>
                            <a href="${urlOrderAccept}" class="order-button"><fmt:message key="user.list.button.add"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="vehicle.list.table.empty"/></p>
        </c:otherwise>
    </c:choose>
</u:html>
