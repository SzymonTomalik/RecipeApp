package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppDeleteDishFromPlan", value = "/app/plan/delete/dish")
public class AppDeleteDishFromPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        RecipePlanDao rpd = new RecipePlanDao();
        RecipePlan dishToRemove = rpd.read(recipePlanId);
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(dishToRemove.getRecipeId());
        HttpSession session = request.getSession();
        int userId = (Integer) (session.getAttribute("loggedUser"));
        if (userId != recipe.getAdminId()) {
            response.sendRedirect("/appProhibitedRemoval.jsp");
        } else {
            rpd.deleteDish(recipePlanId);
            response.sendRedirect("/app/plan/details?planId=" + (planId));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipePlanId = Integer.parseInt(request.getParameter("recipePlanId"));
        request.setAttribute("recipePlanId", recipePlanId);
        String mealName = request.getParameter("mealName");
        request.setAttribute("mealName", mealName);
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        request.setAttribute("recipeId", recipeId);
        request.setAttribute("planId", planId);
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/appDeleteDishFromPlan.jsp").forward(request, response);
    }
}
