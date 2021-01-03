package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AppPlanDetails", value = "/app/plan/details")
public class AppPlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("planId"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);
//        System.out.println(planId);
        request.setAttribute("planId", planId);
        Map<String, List<PlanDetails>> stringListMap = planDao.detailsOfPlan(planId);
        request.setAttribute("planDetails", stringListMap);
        getServletContext().getRequestDispatcher("/appPlanDetails.jsp").forward(request, response);
    }
}
