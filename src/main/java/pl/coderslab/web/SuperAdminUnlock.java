package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SuperAdminUnlock", value = "/app/superAdmin/unlock")
public class SuperAdminUnlock extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AdminsDao adminsDao = new AdminsDao();
        Admins user = adminsDao.read(userId);
        user.setEnable(1);
        adminsDao.update(user);
        request.setAttribute("unlock", 0);
        getServletContext().getRequestDispatcher("/app/superAdmin/users").forward(request,response);
    }
}
