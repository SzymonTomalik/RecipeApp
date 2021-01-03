<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="WEB-INF/head.jspf" %>

<body>
<%@include file="WEB-INF/dashboardHeader.jspf" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="WEB-INF/sideBar.jspf"%>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <div class="row border-bottom border-3">
                        <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                        <div class="col d-flex justify-content-end mb-2">
                            <a href="<c:if test="${dash != null}"><c:url value="/app"/></c:if><c:if test="${recipes != null}"><c:url value="/app/recipe/list"/></c:if><c:if test="${plan != null}"><c:url value="/app/plan/details?planId=${planId}"/></c:if>" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>
                    </div>

                    <table class="table borderless">
                        <tbody>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Nazwa Przepisu</th>
                            <td class="col-7">
                                ${recipe.name}
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Opis przepisu</th>
                            <td class="col-7">${recipe.description}</td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                            <td class="col-7">
                                ${recipe.preparationTime}
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <p>${recipe.preparation}</p>
                        </div>
                        <div class="col-2"></div>
                        <ul class="col-5 p-4 list-unstyled">
                            <c:forEach items="${splitIngredients}" var="ingredient">
                                <li>${ingredient}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>
