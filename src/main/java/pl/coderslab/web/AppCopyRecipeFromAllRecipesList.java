package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddRecipeFromAllRecipesList", value = "/app/recipe/add/copy")
public class AppCopyRecipeFromAllRecipesList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("loggedUser");
        Recipe recipe = new Recipe(name,ingredients,description,preparationTime,preparation,userId);
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe>allRecipes=recipeDao.findAll();
        List<Recipe>userRecipes=new ArrayList<>();
        for (Recipe recipeFromBase:allRecipes) {
            if (recipeFromBase.getAdminId() == userId) {
                userRecipes.add(recipeFromBase);
            }
        }
        if (!userRecipes.contains(recipe)) {
            recipeDao.create(recipe);
            response.sendRedirect("/app/recipe/list");

        } else {
            response.sendRedirect("/appDoubleRecipe.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao recipeDao=new RecipeDao();
        Recipe recipe=recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/appCopyRecipeFromAllRecipesList.jsp").forward(request, response);

    }
}
