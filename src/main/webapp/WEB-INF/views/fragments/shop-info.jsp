<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-primary">
    <div class="panel-heading"><b>${shop.name}</b></div>
    <div class="panel-body">
        <p><b>Address : </b> c. ${shop.address.city}, st. ${shop.address.street}</p>
        <p>BlaBlaBla BlaBlaBla BlaBlaBla</p>
        <p class="item-list"><b>Product List :</b></p>
    </div>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Producer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product" varStatus="loop">
            <tr>
                <td scope="row">${loop.index + 1}</td>
                <td><a href="${pageContext.request.contextPath}/product/${product.id}">${product.name}</a></td>
                <td>${product.producer.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
