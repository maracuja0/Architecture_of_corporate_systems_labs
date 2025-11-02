<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Выберите пользователя</h2>
<form action="${pageContext.request.contextPath}/positions" method="get">
    <label for="userId">Пользователь:</label>
    <select id="userId" name="userId">
        <c:forEach var="u" items="${users}">
            <option value="${u.userId}">${u.userFirstName} ${u.userLastName}</option>
        </c:forEach>
    </select>
    <button type="submit">Выбрать</button>
</form>