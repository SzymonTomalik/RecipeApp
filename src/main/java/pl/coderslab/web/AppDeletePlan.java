package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "appDeletePlan", value = "/app/plan/delete")
public class AppDeletePlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao planDao = new PlanDao();
        Plan planToRemove = planDao.read(planId);
        HttpSession session = request.getSession();
        int userId = (Integer) (session.getAttribute("loggedUser"));
        if (userId != planToRemove.getAdminId()) {
            response.sendRedirect("/appProhibitedRemoval.jsp");
        } else {
            planDao.deleteRecipesFromPlan(planId);
            planDao.delete(planId);

            response.sendRedirect("/app/plan/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("planId", request.getParameter("planId"));
        request.setAttribute("planName", request.getParameter("planName"));
        getServletContext().getRequestDispatcher("/planSafeDelete.jsp").forward(request, response);
    }
}
