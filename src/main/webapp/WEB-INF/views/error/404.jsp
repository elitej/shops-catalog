<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../fragments/head.jsp"/>
<jsp:include page="../fragments/navbar.jsp"/>

<div class="container">
    <div class="row">
        <div class="span12">
            <div class="jumbotron center">
                <%--<h1>Page Not Found <small>Error 404</small></h1>--%>
                <%--<br />--%>
                <p><b>${message}</b></p>
                <a href="/" class="btn btn-large btn-info"><i class="icon-home icon-white"></i>Home Page</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
