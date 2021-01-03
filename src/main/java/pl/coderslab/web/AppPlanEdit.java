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

@WebServlet(name = "appPlanEdit", value = "/app/plan/edit")
public class AppPlanEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);
        HttpSession session = request.getSession();
        int userId = (Integer) (session.getAttribute("loggedUser"));
        if (userId != plan.getAdminId()) {
            response.sendRedirect("/appProhibitedRemoval.jsp");
        } else {
            plan.setName(planName);
            plan.setDescription(planDescription);
            planDao.update(plan);
            response.sendRedirect("/app/plan/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao planDao = new PlanDao();
        request.setAttribute("plan", planDao.read(planId));
        request.getServletContext().getRequestDispatcher("/appEditPlan.jsp").forward(request, response);

    }
}
