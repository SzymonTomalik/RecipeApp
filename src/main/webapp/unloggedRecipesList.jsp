<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="/WEB-INF/head.jspf" %>

<body>
<%@include file="/WEB-INF/header.jspf" %>


<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>
<%----%>
<section>
    <div class="border-dashed border-5 p-2 m-2 width-medium">
        <form method="post">
        <div style="text-align:left; margin:7px 20px 0px 0px;" class="d-inline-block">
            <h3 class="color-header text-uppercase">Wyszukaj przepis:</h3>
        </div>
        <div style="text-align:center; margin:0px 10px 10px 0px;" class="d-inline-block">
            <input type="text" name="searchRecipe" id="search recipe" placeholder="Nazwa przepisu" size="50px">
        </div>
        <div style="text-align:right; margin:0px auto 0px auto;" class="d-inline-block" >
            <button type="submit" class="btn btn-danger rounded-1 pt-1 pb-1 pr-4 pl-4">Wyszukaj
            </button>
        </div>
        </form>
    </div>
</section>
<%----%>
<section class="mr-4 ml-4">
    <c:if test="${emptyList != null}">
        <div class="center; alert-info" style="position:relative; top:20px; margin-bottom:50px; text-align: center; color: red">
            <h3>Niestety nie ma takiego przepisu w naszej bazie.</h3>
        </div>
    </c:if>
    <c:if test="${emptyList == null}">
        <table class="table">
            <thead>
            <tr class="d-flex text-color-darker">
                <th scope="col" class="col-1">ID</th>
                <th scope="col" class="col-5">NAZWA</th>
                <th scope="col" class="col-5">OPIS</th>
                <th scope="col" class="col-1">AKCJE</th>
            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach var="recipe" items="${recipes}">
                <tr class="d-flex">
                    <th scope="row" class="col-1">${recipe.ID}</th>
                    <td class="col-5">${recipe.name}</td>
                    <td class="col-5">${recipe.description}</td>
                    <td class="col-1"><a href="<c:url value="/recipe/details?recipeId=${recipe.ID}"/>" class=" btn btn-info
                                     rounded-0 text-light">Szczegóły</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</section>

<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>