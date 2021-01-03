package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AppSuperAdminPromotion",value = "/app/superAdmin/promotion")
public class AppSuperAdminPromotion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AdminsDao adminsDao = new AdminsDao();
        Admins user = adminsDao.read(userId);
        user.setSuperadmin(1);
        adminsDao.update(user);
        request.setAttribute("adminUpdate",1);
        getServletContext().getRequestDispatcher("/app/superAdmin/users").forward(request, response);
    }
}
