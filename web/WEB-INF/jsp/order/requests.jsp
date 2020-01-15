<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="order.requests.title" var="title"/>
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

                        <%--в зависимости от роли позываем соответствующую кнопку--%>
                        <td class="empty">
                            <c:choose>
                                <c:when test="${currentUser.role == 'DISPATCHER'}">
                                    <%--подтверждение заявки--%>
                                    <c:url var="urlOrderConfirm" value="/order/confirm.html">
                                        <c:param name="id" value="${order.id}"/>
                                    </c:url>
                                    <a href="${urlOrderConfirm}" class="order-button"><fmt:message
                                            key="order.list.button.confirm"/></a>
                                </c:when>
                                <c:when test="${currentUser.role == 'DRIVER'}">
                                    <%--старт рейса (изменение состояния заявки)--%>
                                    <c:url var="urlOrderStart" value="/order/start.html">
                                        <c:param name="id" value="${order.id}"/>
                                    </c:url>
                                    <a href="${urlOrderStart}" class="order-button"><fmt:message
                                            key="order.list.button.start"/></a>
                                </c:when>
                                <c:otherwise>
                                    <%--возможность редактирования для пользователя, если заявка еще не подтверждена--%>
                                    <c:if test="${order.status == 'NOT_SERVED'}">
                                    <c:url var="urlOrderEdit" value="/order/edit.html">
                                        <c:param name="id" value="${order.id}"/>
                                    </c:url>
                                    <a href="${urlOrderEdit}" class="edit"><fmt:message
                                            key="user.list.button.edit"/></a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="order.list.table.empty"/></p>
        </c:otherwise>
    </c:choose>
    <c:if test="${currentUser.role == 'USER'}">
        <c:url var="urlRequestCreate" value="/order/edit.html"/>
        <a href="${urlRequestCreate}" class="add-button"><fmt:message key="order.list.button.add"/></a>
    </c:if>
</u:html>
