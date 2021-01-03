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
                <div class="mt-4 ml-4 mr-4">
                    <form action="<c:url value="/appAddRecipe.jsp"/>">
                        <div class="row border-bottom border-3">
                            <div align="center" class="col"><h3 class="color-header text-uppercase">Dodawanie przepisu
                                do planu:</h3>
                            </div>
                        </div>
                        <div class="center; alert-info"
                             style="position:relative; top:35px; text-align: center; color: red">
                            <h4>Niestety nie masz jeszcze żadnych przepisów. Czy chcesz utworzyć?</h4>
                        </div>
                        <div class="center" style="position:relative; top:70px">
                            <button type="submit" class="btn btn-danger rounded-1 pt-1 pb-1 pr-4 pl-4">Utwórz</button>
                            <a href="/app" class="btn btn-color rounded-1 pt-0 pb-0 pr-4 pl-4">Anuluj
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>