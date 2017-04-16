<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-primary">
    <div class="panel-heading">${product.name}</div>
    <div class="panel-body">
        <p><b>Producer :</b> ${product.producer.name}</p>
        <p>BlaBlaBla BlaBlaBla BlaBlaBla</p>
        <p class="item-list"><b>Shop list :</b></p>
    </div>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${shops}" var="shop" varStatus="loop">
            <tr>
                <td scope="row">${loop.index + 1}</td>
                <td><a href="${pageContext.request.contextPath}/shop/${shop.id}">${shop.name}</a></td>
                <td>c. ${shop.address.city}, st. ${shop.address.street}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
