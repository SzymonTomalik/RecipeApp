<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="WEB-INF/head.jspf" %>
<body id="page-top">
<%@include file="WEB-INF/header.jspf" %>
<section class="dashboard-section">
    <div class="container d-flex pt-4 pb-4 justify-content-center align-items-center">
        <div class="border-dashed view-height">
            <div class="col-4 mr-4" style="position: relative; margin: 25px; top: 10px">
                <div class="div-img">
                    <img src="<c:url value="/images/krolik.png"/>" style="width: 315px; height: 315px" alt="Tu powinien być królik"/>
                </div>
            </div>
            <div  class="col-7 ml-4"; style="margin: 30px;">
                <h1 class="pb-1" style="width: 300px">Kontakt z nami</h1>
                <p class="color-header text-uppercase>">${dataInfo.companyName}
                </p>
                <p class="color-header text-uppercase">${dataInfo.companyAdress}
                </p>
                <p class="color-header text-uppercase>">${dataInfo.companyEmail}
                </p>
                <p class="color-header text-uppercase>">${dataInfo.companyPhone}
                </p>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>
