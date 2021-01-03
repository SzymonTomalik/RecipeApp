package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;

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

@WebServlet(name = "listRecipe", value = "/app/recipe/list")
public class AppRecipes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        AdminsDao adminsDao=new AdminsDao();
        HttpSession session= request.getSession();
        Admins user = adminsDao.read((Integer) session.getAttribute("loggedUser"));
        List<Recipe> recipes = recipeDao.findAll();
        List<Recipe> recipeList = new ArrayList<>();

        for (Recipe recipe: recipes)  {
            if (user.getId() == recipe.getAdminId()) {
                recipeList.add(recipe);
            }

        }
        recipeList.sort(Comparator.comparing(Recipe::getCreated).reversed());
        request.setAttribute("recipeList", recipeList);
        getServletContext().getRequestDispatcher("/appRecipeList.jsp").forward(request, response);

    }
}
