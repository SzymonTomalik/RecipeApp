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

@WebServlet(name = "AppAddPlan", value = "/app/plan/add")
public class AppAddPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        if (planName.equals(" ") || planDescription.equals(" ")) {
            response.sendRedirect("/appNoDataAddPlan.jsp");
            return;
        }
        HttpSession session = request.getSession();
        int loggedUser = (int) session.getAttribute("loggedUser");
        Plan plan = new Plan(planName,planDescription,loggedUser);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/appAddPlan.jsp");
    }
}
