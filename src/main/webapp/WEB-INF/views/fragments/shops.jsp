<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row catalog">
    <c:forEach items="${shops}" var="shop">
        <div class="col-xs-6 col-md-4">
            <a href="${pageContext.request.contextPath}/shop/${shop.id}" class="thumbnail">
                ${shop.name}<br/>
                ${shop.address.city}<br/>
                ${shop.address.street}
            </a>
        </div>
    </c:forEach>
</div>

<jsp:include page="pager.jsp"/>