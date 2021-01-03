<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="/WEB-INF/head.jspf" %>

<body>
<%@include file="/WEB-INF/header.jspf" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" action="<c:url value="/register"/>" method="post">
                    <h1 class="text-color-darker">Rejestracja</h1>
                    <div class="form-group">
                        <label for="name"></label>
                        <input type="text" class="form-control" id="name" name="name" required ="required" pattern="[A-Za-z]+.*"
                                                         placeholder="podaj imię">
                    </div>
                    <div class="form-group">
                        <label for="surname"></label>
                        <input type="text" class="form-control" id="surname" name="surname" required ="required" pattern="[A-Za-z]+.*"
                                                            placeholder="podaj nazwisko">
                    </div>
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="email" class="form-control" id="email" name="email"
                                                          placeholder="podaj email">
                    </div>
                    <div class="form-group">
                        <label for="password">

                        </label><input type="password" class="form-control" id="password"  required
                                                             name="password" placeholder="podaj hasło">
                    </div>
                    <div class="form-group">
                        <label for="repassword">

                        </label><input type="password" class="form-control" id="repassword" required
                                                               name="repassword" placeholder="powtórz hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                </form>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/footer.jspf" %>
</body>
</html>
