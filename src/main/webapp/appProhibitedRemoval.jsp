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
                    <form method="post">
                        <div class="row border-bottom border-3">
                            <div align="center" class="col"><h3 class="color-header text-uppercase">Niedozwolona operacja</h3>
                            </div>
                        </div>
                                                        <div class="center; alert-info"
                                     style="position:relative; top:35px; text-align: center; color: red">
                                    <h4>Zawartość, którą chcesz zmienić nie należy do Twojego profilu.</h4>
                                    <h4>Operacja nie może zostać wykonana.</h4>
                                </div>
                                <div class="center" style="position:relative; top:70px">
                                    <a href="<c:url value="/app"/>"
                                       class="btn btn-color rounded-1 pt-0 pb-0 pr-4 pl-4">Powrót do głównego menu
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