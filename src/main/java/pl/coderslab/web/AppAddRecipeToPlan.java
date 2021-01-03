package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "AppAddRecipeToPlan", value = "/app/recipe/plan/add")
public class AppAddRecipeToPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;UTF-8");
        RecipePlanDao rcd = new RecipePlanDao();
        RecipePlan recipePlan = new RecipePlan();
        recipePlan.setPlanId(Integer.parseInt(request.getParameter("choosePlan")));
        recipePlan.setRecipeId(Integer.parseInt(request.getParameter("recipe")));
        recipePlan.setMealName(request.getParameter("name"));
        recipePlan.setDisplayOrder(Integer.parseInt(request.getParameter("displayOrder")));
        recipePlan.setDayNameId(Integer.parseInt(request.getParameter("day")));
        rcd.create(recipePlan);

//        int recipePlanId=rcd.readId(recipePlan.getRecipeId(), recipePlan.getMealName(), recipePlan.getDisplayOrder(), recipePlan.getDayNameId(), recipePlan.getPlanId());
//        System.out.println(recipePlanId);
        response.sendRedirect("/appAddNextRecipeToPlan.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminsDao adminsDao = new AdminsDao();
        DayNameDao nameDao = new DayNameDao();
        RecipeDao recipeDao = new RecipeDao();
        PlanDao planDao = new PlanDao();
        Admins user = adminsDao.read((Integer) session.getAttribute("loggedUser"));
        //wysyłamy liste dni
        List<DayName> dayNameList = nameDao.findAll();
        request.setAttribute("dayList", dayNameList);
        //wysyłamy posortowana liste przepisów użytkownika
        List<Recipe> recipes = recipeDao.findAll();
        List<Recipe> recipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (user.getId() == recipe.getAdminId()) {
                recipeList.add(recipe);
            }
        }
        if (recipeList.isEmpty()) {
            getServletContext().getRequestDispatcher("/appEmptyRecipes.jsp").forward(request, response);
        } else {
            recipeList.sort(Comparator.comparing(Recipe::getCreated).reversed());
            request.setAttribute("recipeList", recipeList);
        }

        //wysyłamy posortowana liste planów użytkownika
        List<Plan> plans = planDao.findAll();
        List<Plan> planList = new ArrayList<>();
        for (
                Plan plan : plans) {
            if (user.getId() == plan.getAdminId()) {
                planList.add(plan);
            }
        }
        if (planList.isEmpty()) {
            getServletContext().getRequestDispatcher("/appEmptyPlans.jsp").forward(request, response);
        } else {
            planList.sort(Comparator.comparing(Plan::getCreated).reversed());
            request.setAttribute("planList", planList);
        }
        getServletContext().getRequestDispatcher("/appAddRecipeToPlan.jsp").forward(request, response);
    }
}
