<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="WEB-INF/head.jspf" %>
<body id="page-top">
<%@include file="WEB-INF/header.jspf" %>
<section class="padding-small details bg-light">
    <div class="container">
        <h1 align="center">O aplikacji</h1>
        <div class="row">
            <div class="col text-center">
                <i class="fas fa-check icon-details"></i>
                <h1>Cel</h1>
                <p>Pomoc w planowaniu diety.</p>
            </div>
            <div class="col text-center pr-4 pl-4 mr-4 ml-4">
                <i class="far fa-clock icon-details"></i>
                <h1>Historia</h1>
                <p>Największa baza przepisów w Polsce.</p>
            </div>
            <div class="col text-center">
                <i class="fas fa-list icon-details"></i>
                <h1>Twórcy</h1>
                <p class="text-uppercase color-header">${dataInfo.authors}</p>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>
