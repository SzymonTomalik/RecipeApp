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

@WebServlet(name = "AppDeleteRecipe", value = "/app/recipe/safeDelete")
public class AppDeleteRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipeToRemove = recipeDao.read(recipeId);
        HttpSession session = request.getSession();
        int userId = (Integer) (session.getAttribute("loggedUser"));
        if (userId != recipeToRemove.getAdminId()) {
            response.sendRedirect("/appProhibitedRemoval.jsp");
        } else {
            boolean delete = recipeDao.delete(recipeId);
            if (delete) {
                request.setAttribute("delete", 0);
                getServletContext().getRequestDispatcher("/recipeSafeDelete.jsp").forward(request, response);
                return;
            }
            response.sendRedirect("/app/recipe/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipeId", request.getParameter("recipeId"));
        request.setAttribute("recipeName", request.getParameter("recipeName"));
        getServletContext().getRequestDispatcher("/recipeSafeDelete.jsp").forward(request, response);
    }
}
