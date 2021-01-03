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
                            <div class="center; alert-info" style="position:relative; top:35px; text-align: center; color: red">
                                <h4>Hasło zmienione</h4>
                            </div>
                    </div>
                </div>
            </div>

</section>
<%@include file="WEB-INF/footer.jspf" %>
</body>
</html>