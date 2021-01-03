package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AppSuperAdminEditUser", value = "/app/superAdmin/edit/user")
public class AppSuperAdminEditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminsDao adminsDao=new AdminsDao();
        Admins user = adminsDao.read(Integer.parseInt(request.getParameter("userId")));
        String userFirstName = request.getParameter("userFirstName");
        String userLastName = request.getParameter("userLastName");
        String userEmail = request.getParameter("userEmail");
        if (!userFirstName.matches("[A-Za-z]+") || !userLastName.matches("[A-Za-z]+")) {
            request.setAttribute("noData", 0);
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/appEditUserData.jsp").forward(request,response);
            return;
        }
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);
        user.setEmail(userEmail);
        adminsDao.update(user);
        response.sendRedirect("/app/superAdmin/users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminsDao adminsDao = new AdminsDao();
        int userId=Integer.parseInt(request.getParameter("userId"));
        Admins user = adminsDao.read(userId);
        request.setAttribute("user", user);
        request.setAttribute("userId",userId);
        getServletContext().getRequestDispatcher("/appSuperAdminEditUser.jsp").forward(request,response);
    }
}
