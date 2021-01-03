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

@WebServlet(name = "AppUpdateRecipe", value = "/app/recipe/edit")
public class AppUpdateRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        int id = Integer.parseInt(request.getParameter("recipeId"));
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("loggedUser");
//        System.out.println(id);
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipeToUpdate = recipeDao.read(id);
        if (userId != recipeToUpdate.getAdminId()) {
            response.sendRedirect("/appProhibitedRemoval.jsp");
        } else {
            Recipe recipe = new Recipe(name, ingredients, description, preparationTime, preparation, userId);
            recipe.setID(id);
            recipeDao.update(recipe);
            response.sendRedirect("/app/recipe/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");
        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipe", recipeDao.read(Integer.parseInt(recipeId)));
        getServletContext().getRequestDispatcher("/appEditRecipe.jsp").forward(request, response);
    }
}
