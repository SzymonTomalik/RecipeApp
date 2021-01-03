<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="WEB-INF/head.jspf" %>

<body>
<%@include file="WEB-INF/dashboardHeader.jspf" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="WEB-INF/sideBar.jspf" %>
        <div class="m-4 p-3 width-medium">
            <div class="m-4 p-3 border-dashed view-height">

                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2">
                        <a href="<c:url value="/app"/>" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <c:if test="${block != null}">
                        <div class="center; alert-info"
                             style="position:relative; top:10px; margin-bottom: 40px; text-align: center; color: red">
                            <h4>Użytkownik został zablokowany</h4>
                        </div>
                    </c:if>
                    <c:if test="${unlock != null}">
                        <div class="center; alert-info"
                             style="position:relative; top:10px; margin-bottom: 40px; text-align: center; color: red">
                            <h4>Użytkownik został odblokowany</h4>
                        </div>
                    </c:if>
                    <c:if test="${adminUpdate != null}">
                        <div class="center; alert-info"
                             style="position:relative; top:10px; margin-bottom: 40px; text-align: center; color: red">
                            <h4>Użytkownik otrzymał uprawnienia administratora</h4>
                        </div>
                    </c:if>
                    <c:if test="${notAdmin != null}">
                        <div class="center; alert-info"
                             style="position:relative; top:10px; margin-bottom: 40px; text-align: center; color: red">
                            <h4>Użytkownikowi odebrano uprawnienia administratora</h4>
                        </div>
                    </c:if>
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-3">IMIĘ</th>
                            <th class="col-5">NAZWISKO</th>
                            <th class="col-3">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach var="user" items="${userList}">
                            <tr class="d-flex" style="height: 70px">
                                <th class="col-1" style="padding-top: 23px;">${user.id}</th>
                                <td class="col-3" style="padding-top: 23px;">${user.firstName}</td>
                                <td class="col-3" style="padding-top: 23px;">${user.lastName}</td>
                                <td class="col-1">
                                    <a href="<c:url value="/app/superAdmin/edit/user?userId=${user.id}"/>" class="btn btn-warning
                                rounded-0 text-light m-1">Edytuj</a>
                                </td>
                                <td class="col-2 center" style="padding-left: 25px; padding-right: 25px">
                                    <c:choose>
                                        <c:when test="${user.superadmin==1 && admin.id!=user.id}">
                                            <a href="<c:url value="/app/superAdmin/degradation?userId=${user.id}"/>"
                                               class="btn btn-danger
                                rounded-0 text-dark m-1">Zmień na użytkownika</a> </c:when>
                                        <c:when test="${user.enable==1 && user.superadmin!=1}">
                                            <a href="<c:url value="/app/superAdmin/promotion?userId=${user.id}"/>"
                                               class="btn btn-warning
                                rounded-0 text-dark m-1">Zmień na superadmina</a> </c:when>
                                    </c:choose>
                                </td>
                                <td class="col-2 center">
                                    <c:choose>
                                        <c:when test="${user.enable==1 && user.superadmin!=1}">
                                            <a href="<c:url value="/app/superAdmin/block?userId=${user.id}"/>"
                                               class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                        </c:when>
                                        <c:when test="${user.superadmin!=1}">
                                            <a href="<c:url value="/app/superAdmin/unlock?userId=${user.id}"/>"
                                               class="btn btn-success rounded-0 text-light m-1">Odblokuj</a>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>