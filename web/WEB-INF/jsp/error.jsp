<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="error.page.title"/>
<u:html title="${title}">
    <c:choose>
        <c:when test="${not empty message}">
            <p class="error">${message}</p>
        </c:when>
        <c:when test="${not empty pageContext.exception}">
            <p class="error"><fmt:message key="error.page.exception"/></p>
        </c:when>
        <c:when test="${not empty pageContext.errorData.requestURI}">
            <p class="error"><fmt:message key="error.page.uri"/> ${pageContext.errorData.requestURI} <fmt:message key="error.page.notfound"/></p>
        </c:when>
        <c:otherwise>
            <p class="error"><fmt:message key="error.page.message"/></p>
        </c:otherwise>
    </c:choose>
</u:html>
