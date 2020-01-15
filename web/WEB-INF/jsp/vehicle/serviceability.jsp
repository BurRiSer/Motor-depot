<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="vehicle.edit.title.edit"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlDriverView" value="/driver/view.html"/>
    <c:url var="urlVehicleSave" value="/vehicle/save.html"/>
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
    <%--форма с возможностью изменения техсостояния авто--%>
    <form action="${urlVehicleSave}" method="post">
        <input name="id" value="${vehicle.id}" type="hidden">
        <input name="model" value="${vehicle.model}" type="hidden">
        <input name="type" value="${vehicle.type}" type="hidden">
        <h4><fmt:message key="vehicle.list.table.serviceability"/>:</h4>
        <label for="serviceability_1"><fmt:message key="true"/>:</label>
        <input type="radio" name="serviceability" id="serviceability_1" value="true">
        <label for="serviceability_2"><fmt:message key="false"/>:</label>
        <input type="radio" name="serviceability" id="serviceability_2" value="false">
        <button class="save"><fmt:message key="user.edit.button.save"/></button>
        <a class="back" href="${urlDriverView}"><fmt:message key="user.edit.button.cancel"/></a>
    </form>
</u:html>

