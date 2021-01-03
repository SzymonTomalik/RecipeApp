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
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form action="<c:url value="/app/recipe/add"/>" method="post">
                    <div class="mt-4 ml-4 mr-4">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                </button>
                            </div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Nazwa Przepisu</th>
                                <td class="col-7">

                                    <input name="name" name="name" id="name" class="w-100 p-1" required  pattern="[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*" value="" >
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Opis przepisu</th>
                                <td class="col-7"><textarea name="description" id="description" class="w-100 p-1"
                                                            rows="5"
                                                            required pattern="[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*" ></textarea></td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                <td class="col-3">
                                    <input class="p-1" name="preparationTime" id="preparationTime" type="number" min="0" step="1"
                                           value=""
                                           required>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row d-flex">
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób
                                przygotowania</h3></div>
                            <div class="col-2"></div>
                            <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                        </div>
                        <div class="row d-flex">
                            <div class="col-5 p-4">
                                <textarea name="preparation" id="preparation" class="w-100 p-1" rows="10"
                                          required  pattern="[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*"  ></textarea>
                            </div>
                            <div class="col-2"></div>

                            <div class="col-5 p-4">
                                <textarea name="ingredients" id="ingredients" class="w-100 p-1" rows="10"
                                          required pattern="[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+.*"  ></textarea>
                            </div>
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