package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AppSuperAdminSetAdmin", value = "/app/superAdmin/degradation")
public class AppSuperAdminDegradation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AdminsDao adminsDao = new AdminsDao();
        Admins user = adminsDao.read(userId);
        user.setSuperadmin(0);
        adminsDao.update(user);
        request.setAttribute("notAdmin",1);
        getServletContext().getRequestDispatcher("/app/superAdmin/users").forward(request, response);

    }
}
