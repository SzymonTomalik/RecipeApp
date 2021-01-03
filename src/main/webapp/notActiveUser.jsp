<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="WEB-INF/head.jspf" %>
<body>
<%@include file="WEB-INF/header.jspf" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <h2 style="text-align: center; color: red" class="alert-info">Konto użytkownika jest zablokowane. </h2>
            <div class="container w-25">
                <form class="padding-small text-center" action="<c:url value="/login"/>" method="post">
                    <h1 class="text-color-darker">Logowanie</h1>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="podaj adres email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="podaj hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>