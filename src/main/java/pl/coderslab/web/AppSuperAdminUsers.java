package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SuperAdminUsers", value = "/app/superAdmin/users")
public class AppSuperAdminUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminsDao adminsDao = new AdminsDao();
        List<Admins> userList = adminsDao.findAll();
        request.setAttribute("userList", userList);
        getServletContext().getRequestDispatcher("/appSuperAdminUsers.jsp").forward(request, response);
    }
}
