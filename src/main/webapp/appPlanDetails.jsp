<%@ page import="pl.coderslab.dao.RecipeDao" %>
<%@ page import="pl.coderslab.model.Recipe" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="WEB-INF/head.jspf" %>

<body>
<%@include file="WEB-INF/dashboardHeader.jspf" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="WEB-INF/sideBar.jspf" %>
        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="<c:url value="/app/plan/list"/>" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.name}
                                </p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.description}
                                </p>
                            </div>
                        </div>
                    </div>


                    <c:forEach items="${planDetails.keySet()}" var="day">
                        <table class="table">
                            <thead>
                                <tr class="d-flex">
                                    <th class="col-2">${day}</th>
                                    <th class="col-7"></th>
                                    <th class="col-1"></th>
                                    <th class="col-2"></th>
                                </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${planDetails.get(day)}" var="details">
                                <tr class="col-10 d-flex">
                                    <td class="col-2">${details.mealName}</td>
                                    <td class="col-7">${details.recipeName}</td>
                                    <td class="col-2 center">
                                        <a href="/app/plan/delete/dish?recipePlanId=${details.recipePlanId}&planId=${planId}&recipeId=${details.id}&mealName=${details.mealName}" class="btn btn-warning rounded-0 text-light m-1">Usuń posiłek</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="/app/plan/delete/recipe?planId=${planId}&recipeId=${details.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń przepis z planu</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="/app/recipe/details?planId=${planId}&from=plan&recipeId=${details.id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>
