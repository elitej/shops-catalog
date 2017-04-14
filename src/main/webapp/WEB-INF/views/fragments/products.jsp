<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Producer</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${shop.products}" var="product" varStatus="loop">
            <tr>
                <td scope="row">${loop.index + 1}</td>
                <td><a href="${pageContext.request.contextPath}/product/${product.id}">${product.name}</a></td>
                <td>${product.producer.name}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>