<%@tag language="java" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="urlCss" value="/style.css"/>
    <link rel="stylesheet" href="${urlCss}">
</head>
<body>
<header>
    <h1><fmt:message key="application.title"/></h1>
</header>
<%--изменение доступности кнопок меню в зависимости от роли  --%>
<p class="menu">
    <c:url var="urlHome" value="/index.html"/>
    <c:url var="urlRequests" value="/order/requests.html"/>
    <c:url var="urlFlights" value="/order/flights.html"/>
    <c:url var="urlView" value="/driver/view.html"/>
    <c:url var="urlVehicles" value="/vehicle/list.html"/>
    <c:url var="urlDrivers" value="/driver/list.html"/>
    <c:url var="urlUsers" value="/user/list.html"/>
    <a class="menu-button" href="${urlHome}"><fmt:message key="application.button.home"/></a>
    <a class="menu-button" href="${urlRequests}"><fmt:message key="application.button.requests"/></a>
    <c:if test="${currentUser.role == 'DRIVER'}">
        <a class="menu-button" href="${urlFlights}"><fmt:message key="application.button.flights"/></a>
        <a class="menu-button" href="${urlView}"><fmt:message key="application.button.driver"/></a>
    </c:if>
    <c:if test="${currentUser.role != null && currentUser.role != 'USER'}">
        <a class="menu-button" href="${urlVehicles}"><fmt:message key="application.button.vehicles"/></a>
        <a class="menu-button" href="${urlDrivers}"><fmt:message key="application.button.drivers"/></a>
    </c:if>
    <c:if test="${currentUser.role == 'ADMINISTRATOR'}">
        <a class="menu-button" href="${urlUsers}"><fmt:message key="application.button.users"/></a>
    </c:if>
</p>
<c:if test="${not empty currentUser}">
    <c:url var="urlLogout" value="/logout.html"/>
    <c:url var="urlPasswordEdit" value="/password/edit.html"/>
    <p class="login-state">
    <fmt:message key="application.welcome"/>: ${currentUser.login}
    (<fmt:message key="${currentUser.role.name}"/>)
    <a class="login-state" href="${urlPasswordEdit}"><fmt:message key="application.button.password.change"/></a>
    <a class="login-state" href="${urlLogout}"><fmt:message key="application.button.logout"/></a>
    </p>
</c:if>
<article>
    <jsp:doBody/>
</article>
<footer>
    <p><fmt:message key="application.footer.message"/></p>
    <a class="mail" href="mailto:burik.razor@gmail.com">
        sergey.bury@gmail.com
    </a>
</footer>
</body>
</html>