<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="application.button.flights" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:choose>
        <c:when test="${not empty orders}">
            <table>
                <tr>
                    <th><fmt:message key="order.list.table.date"/></th>
                    <th><fmt:message key="order.list.table.departure"/></th>
                    <th><fmt:message key="order.list.table.arrival"/></th>
                    <th><fmt:message key="vehicle.list.table.type"/></th>
                    <th><fmt:message key="order.list.table.status"/></th>
                    <td>&nbsp;</td>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td class="content"><fmt:formatDate pattern="dd-MM-yyyy" value="${order.date}"/></td>
                        <td class="content">${order.departurePoint}</td>
                        <td class="content">${order.arrivalPoint}</td>
                        <td class="content"><fmt:message key="${order.vehicleType.name}"/></td>
                        <td class="content"><fmt:message key="${order.status.name}"/></td>
                        <td class="empty">
                            <c:url var="urlOrderComplete" value="/order/complete.html">
                                <c:param name="id" value="${order.id}"/>
                            </c:url>
                            <a href="${urlOrderComplete}" class="order-button"><fmt:message
                                    key="order.list.button.finish"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="order.list.table.empty"/></p>
        </c:otherwise>
    </c:choose>
</u:html>
