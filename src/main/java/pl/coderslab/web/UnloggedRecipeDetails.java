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

@WebServlet(name = "UnloggedRecipeDetails", value = "/recipe/details")
public class UnloggedRecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        String ingredients = recipe.getIngredients();
        String[] splitIngredients = ingredients.split(", ");
        request.setAttribute("splitIngredients", splitIngredients);
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedUser")!=null) {
            int logged = (Integer) session.getAttribute("loggedUser");
            System.out.println(logged);
            request.setAttribute("logged", logged);
        }
        getServletContext().getRequestDispatcher("/unloggedRecipeDetails.jsp").forward(request, response);
    }
}

