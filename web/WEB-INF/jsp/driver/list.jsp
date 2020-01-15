
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="driver.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="driver.list.table.name"/></th>
            <th><fmt:message key="driver.list.table.surname"/></th>
            <th><fmt:message key="driver.list.table.birth"/></th>
        </tr>
        <c:forEach var="driver" items="${drivers}">
            <tr>
                <td class="content">${driver.name}</td>
                <td class="content">${driver.surname}</td>
                <td class="content"><fmt:formatDate pattern="dd-MM-yyyy" value="${driver.birth}"/></td>
            </tr>
        </c:forEach>
    </table>
</u:html>