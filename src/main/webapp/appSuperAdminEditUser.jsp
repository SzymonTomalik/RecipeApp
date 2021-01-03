<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%@include file="WEB-INF/head.jspf" %>

<body>
<%@include file="WEB-INF/dashboardHeader.jspf" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="WEB-INF/sideBar.jspf" %>
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">

                <form action="/app/superAdmin/edit/user" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <div class="mt-4 ml-4 mr-4">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Edytuj dane</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                </button>
                            </div>
                        </div>

                        <div style="position: relative; margin-top: 20px">
                            <c:if test="${noData != null}">
                                <div class="center; alert-info" style="position:relative; top:20px; margin-bottom: 40px; text-align: center; color: red">
                                    <h4>Białe znaki niedozwolone!</h4>
                                </div>
                            </c:if>
                            <table class="table borderless">
                                <tbody>
                                <tr class="d-flex">
                                    <th scope="row" class="col-2"><h4>Imię</h4></th>
                                    <td class="col-7">
                                        <input class="w-100 p-1" type="text" name="userFirstName" id="userFirstName" pattern="[A-Za-z]+.*"  value="${user.firstName}" required>
                                    </td>
                                </tr>
                                <tr class="d-flex">
                                    <th scope="row" class="col-2"><h4>Nazwisko</h4></th>
                                    <td class="col-7">
                                        <input class="w-100 p-1" type="text" name="userLastName" id="userLastName" pattern="[A-Za-z]+.*"  value="${user.lastName}" required>
                                    </td>
                                </tr>
                                <tr class="d-flex">
                                    <th scope="row" class="col-2"><h4>Email</h4></th>
                                    <td class="col-3">
                                        <input class="p-1 w-100" type="email" name="userEmail" id="userEmail" value="${user.email}" required>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>
