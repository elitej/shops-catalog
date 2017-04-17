<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="shop-list.jsp"/>
<c:if test="${totalPages gt 1}">
    <jsp:include page="pager.jsp">
        <jsp:param name="path" value="/search/?shop_name=${shop_name}&num="/>
    </jsp:include>
</c:if>

