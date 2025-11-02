<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Избранное пользователя</h2>

<a href="${pageContext.request.contextPath}/positions?userId=${userId}">Вернуться в магазин</a>

<ul>
    <c:forEach var="pos" items="${likedPositions}">
        <li>
                ${pos.positionName} - ${pos.positionDesc} (${pos.positionType})
                <form action="${pageContext.request.contextPath}/favorites" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${userId}" />
                    <input type="hidden" name="positionId" value="${pos.positionId}" />
                    <button type="submit">Убрать из избранного</button>
                </form>

        </li>
    </c:forEach>
</ul>
