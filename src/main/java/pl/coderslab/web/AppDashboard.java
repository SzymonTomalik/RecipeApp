package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.PlanDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Dashboard", value = "/app")
public class AppDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = request.getSession();
        int loggedUser = (int) session.getAttribute("loggedUser");
        Map<String, List<PlanDetails>> stringListMap = planDao.detailsOfRecentPlan(loggedUser);
        request.setAttribute("numberOfRecipes", recipeDao.amountOfRecipes(loggedUser));
        request.setAttribute("numberOfPlans", planDao.amountOfPlans(loggedUser));
        request.setAttribute("nameOfRecentPlan", planDao.getNameOfRecentPlan(loggedUser));
        request.setAttribute("planDetails", stringListMap);
        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);
    }
}

