package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipeDetails", value = "/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String from = request.getParameter("from");
        switch (from) {
            case "dash":
                request.setAttribute("dash", "something");
                break;
            case "plan":
                request.setAttribute("plan", "alsoSomething");
                break;
            case "recipes":
                request.setAttribute("recipes", "somethingElse");
                break;
        }
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        String ingredients = recipe.getIngredients();
        String[] splitIngredients = ingredients.split(", ");
        request.setAttribute("recipe", recipe);
        request.setAttribute("splitIngredients", splitIngredients);
        request.setAttribute("planId", request.getParameter("planId"));
        getServletContext().getRequestDispatcher("/appRecipeDetails.jsp").forward(request,response);
    }
}
