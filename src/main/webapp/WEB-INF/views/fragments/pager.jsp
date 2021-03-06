<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="prevUrl" value="${param.path}${currentIndex - 1}" />
<c:url var="nextUrl" value="${param.path}${currentIndex + 1}" />

<nav aria-label="Page navigation">
    <ul class="pager">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li><a href="">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${prevUrl}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li><a href="" class="next-aligning">Next</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}" class="next-aligning">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>