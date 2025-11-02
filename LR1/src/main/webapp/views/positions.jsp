<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Магазин электроники</h2>

<a href="${pageContext.request.contextPath}/favorites?userId=${userId}">Перейти в избранное</a>

<ul>
    <c:forEach var="pos" items="${positions}">
        <li>
                ${pos.positionName} - ${pos.positionDesc} (${pos.positionType})
            <form action="${pageContext.request.contextPath}/positions" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="${userId}" />
                <input type="hidden" name="positionId" value="${pos.positionId}" />

                <c:set var="isLiked" value="false" />
                <c:forEach var="id" items="${likedIds}">
                    <c:if test="${id eq pos.positionId}">
                        <c:set var="isLiked" value="true" />
                    </c:if>
                </c:forEach>

                <c:choose>
                    <c:when test="${isLiked}">
                        <button type="submit" name="action" value="unlike">Убрать из избранного</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="action" value="like">Добавить в избранное</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </li>
    </c:forEach>
</ul>
