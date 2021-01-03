<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="WEB-INF/head.jspf" %>

<body>
<%@include file="WEB-INF/dashboardHeader.jspf" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="WEB-INF/sideBar.jspf" %>
        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="<c:url value="/app/recipe/add"/>">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis  </span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="<c:url value="/app/plan/add"/>">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${numberOfRecipes}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${numberOfPlans}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${nameOfRecentPlan}
                </h2>

                <c:forEach items="${planDetails.keySet()}" var="day">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${day}</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${planDetails.get(day)}" var="details">
                            <tr class="d-flex">
                                <td class="col-2">${details.mealName}</td>
                                <td class="col-8">${details.recipeName}</td>
                                <td class="col-2">
                                    <a href="/app/recipe/details?from=dash&recipeId=${details.id}">
                                        <button type="button" class="btn btn-primary rounded-0">Szczegóły</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>