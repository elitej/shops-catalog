<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/head.jsp"/>
<jsp:include page="fragments/navbar.jsp"/>

<h1>Name : ${product.name}</h1><br/>
<h1>Producer : ${product.producer.name}</h1> <br/>

<jsp:include page="fragments/footer.jsp"/>